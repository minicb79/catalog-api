package com.minicdesign.catalog.api.libraries.services;

import com.minicdesign.catalog.api.libraries.controllers.usecases.CreateLibraryUseCase;
import com.minicdesign.catalog.api.libraries.domain.LibraryDomain;
import com.minicdesign.catalog.api.libraries.repositories.LibraryRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateLibraryService implements CreateLibraryUseCase {

  private final LibraryRepositoryAdapter repository;

  @Override
  public LibraryDomain createLibrary(LibraryDomain library) {

    if (library == null) {
      throw new IllegalArgumentException("LibraryDomain must not be null when creating a Library.");
    }

    return repository.createLibrary(library);
  }

}
