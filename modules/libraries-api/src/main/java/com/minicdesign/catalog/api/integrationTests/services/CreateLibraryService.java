package com.minicdesign.catalog.api.integrationTests.services;

import com.minicdesign.catalog.api.integrationTests.controllers.usecases.CreateLibraryUseCase;
import com.minicdesign.catalog.api.integrationTests.domain.LibraryDomain;
import com.minicdesign.catalog.api.integrationTests.repositories.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateLibraryService implements CreateLibraryUseCase {

  private final LibraryRepository repository;

  @Override
  public LibraryDomain createLibrary(LibraryDomain library) {

    if (library == null) {
      throw new IllegalArgumentException("LibraryDomain must not be null when creating a Library.");
    }

    return repository.createLibrary(library);
  }

}
