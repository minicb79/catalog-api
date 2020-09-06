package com.minicdesign.catalog.api.libraries.services;

import com.minicdesign.catalog.api.libraries.controllers.usecases.CreateLibraryUseCase;
import com.minicdesign.catalog.api.libraries.domain.LibraryDomain;
import com.minicdesign.catalog.api.libraries.repositories.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateLibraryService implements CreateLibraryUseCase {

  private final LibraryRepository repository;

  @Override
  public LibraryDomain createLibrary(LibraryDomain library) {
    return repository.createLibrary(library);
  }

}
