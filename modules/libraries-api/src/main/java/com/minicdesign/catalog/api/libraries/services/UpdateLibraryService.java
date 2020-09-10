package com.minicdesign.catalog.api.libraries.services;

import com.minicdesign.catalog.api.libraries.controllers.usecases.UpdateLibraryUseCase;
import com.minicdesign.catalog.api.libraries.domain.LibraryDomain;
import com.minicdesign.catalog.api.libraries.repositories.LibraryRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateLibraryService implements UpdateLibraryUseCase {

  private final LibraryRepositoryAdapter repository;

  @Override
  public LibraryDomain updateLibrary(LibraryDomain domain) {
    return repository.updateLibrary(domain);
  }
}
