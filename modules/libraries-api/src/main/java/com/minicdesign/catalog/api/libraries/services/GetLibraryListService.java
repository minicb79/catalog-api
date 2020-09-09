package com.minicdesign.catalog.api.integrationTests.services;

import com.minicdesign.catalog.api.integrationTests.controllers.usecases.GetLibraryListUseCase;
import com.minicdesign.catalog.api.integrationTests.domain.LibraryDomain;
import com.minicdesign.catalog.api.integrationTests.repositories.LibraryRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetLibraryListService implements GetLibraryListUseCase {

  private final LibraryRepositoryAdapter libraryRepository;

  @Override
  public Page<LibraryDomain> getLibraryList(int page, int size) {

    return libraryRepository.getLibrariesForPage(page, size);
  }
}
