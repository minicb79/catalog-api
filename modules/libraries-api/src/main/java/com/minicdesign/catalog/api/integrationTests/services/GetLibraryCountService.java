package com.minicdesign.catalog.api.integrationTests.services;

import com.minicdesign.catalog.api.integrationTests.controllers.usecases.GetLibraryCountUseCase;
import com.minicdesign.catalog.api.integrationTests.repositories.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetLibraryCountService implements GetLibraryCountUseCase {

  private final LibraryRepository repository;

  @Override
  public long getCount() {
    return repository.getCount();
  }
}
