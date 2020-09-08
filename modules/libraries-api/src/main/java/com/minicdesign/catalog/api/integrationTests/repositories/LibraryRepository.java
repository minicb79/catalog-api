package com.minicdesign.catalog.api.integrationTests.repositories;

import java.util.stream.Collectors;

import com.minicdesign.catalog.api.integrationTests.domain.LibraryDomain;
import com.minicdesign.catalog.api.integrationTests.repositories.db.LibraryDao;
import com.minicdesign.catalog.api.integrationTests.repositories.db.LibraryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LibraryRepository {

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
    Page<LibraryDomain> domainPage = new PageImpl<>(daoPage.getContent().stream().map(LibraryDomain::from).collect(Collectors.toList()));

    return domainPage;
  }

  public long getCount() {
    return jpaRepository.count();
  }

}
