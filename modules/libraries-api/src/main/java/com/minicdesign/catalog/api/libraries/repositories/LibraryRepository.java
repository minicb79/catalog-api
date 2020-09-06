package com.minicdesign.catalog.api.libraries.repositories;

import com.minicdesign.catalog.api.libraries.domain.LibraryDomain;
import com.minicdesign.catalog.api.libraries.repositories.db.LibraryDao;
import com.minicdesign.catalog.api.libraries.repositories.db.LibraryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LibraryRepository {

  private final LibraryJpaRepository jpaRepository;

  public LibraryDomain createLibrary(LibraryDomain domain) {
    LibraryDao libraryDao = jpaRepository.save(LibraryDao.from(domain));
    return LibraryDomain.from(libraryDao);
  }

}
