package com.minicdesign.catalog.api.libraries.repositories;

import java.util.Optional;
import java.util.stream.Collectors;

import com.minicdesign.catalog.api.exceptions.ItemNotFoundException;
import com.minicdesign.catalog.api.libraries.domain.LibraryDomain;
import com.minicdesign.catalog.api.libraries.repositories.db.LibraryDao;
import com.minicdesign.catalog.api.libraries.repositories.db.LibraryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LibraryRepositoryAdapter {

  private final LibraryJpaRepository jpaRepository;

  public LibraryDomain createLibrary(LibraryDomain domain) {

    if (domain == null) {
      throw new IllegalArgumentException("LibraryDomain must not be null when creating a Library.");
    }

    LibraryDao libraryDao = jpaRepository.save(LibraryDaoMapper.domainToDao(domain));
    return LibraryDaoMapper.daoToDomain(libraryDao);
  }

  public Page<LibraryDomain> getLibrariesForPage(int page, int size) {
    Page<LibraryDao> daoPage = jpaRepository.findAll(PageRequest.of(page, size));

    return new PageImpl<>(
        daoPage.getContent().stream()
            .map(LibraryDomain::from)
            .collect(Collectors.toList()),
        daoPage.getPageable(),
        daoPage.getTotalElements());
  }

  public LibraryDomain getLibrary(long id) {
    Optional<LibraryDao> dao = jpaRepository.findById(id);

    if (dao.isEmpty()) {
      throw new ItemNotFoundException(String.format("Library with id %d could not be found.", id));
    }

    return LibraryDaoMapper.daoToDomain(dao.get());
  }

  public LibraryDomain updateLibrary(LibraryDomain domain) {
    if (domain == null) {
      throw new IllegalArgumentException("LibraryDomain must not be null when updating a Library.");
    }

    int daoCount = jpaRepository.countById(domain.getId());

    if (daoCount == 0) {
      throw new ItemNotFoundException(String.format("Library with id %d could not be found.", domain.getId()));
    }

    LibraryDao libraryDao = jpaRepository.save(LibraryDaoMapper.domainToDao(domain));
    return LibraryDaoMapper.daoToDomain(libraryDao);
  }

  public long getCount() {
    return jpaRepository.count();
  }

}
