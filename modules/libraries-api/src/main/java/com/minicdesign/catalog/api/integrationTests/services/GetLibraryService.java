package com.minicdesign.catalog.api.integrationTests.services;

import com.minicdesign.catalog.api.integrationTests.controllers.usecases.GetLibraryUseCase;
import com.minicdesign.catalog.api.integrationTests.domain.LibraryDomain;
import com.minicdesign.catalog.api.integrationTests.repositories.LibraryRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetLibraryService implements GetLibraryUseCase {

  private final LibraryRepositoryAdapter repository;

  @Override
  public LibraryDomain getLibrary(Long id) {
    return repository.getLibrary(id);
  }
}
