package com.minicdesign.catalog.api.libraries.services;

import com.minicdesign.catalog.api.libraries.controllers.usecases.GetLibraryListUseCase;
import com.minicdesign.catalog.api.libraries.domain.LibraryDomain;
import com.minicdesign.catalog.api.libraries.repositories.LibraryRepositoryAdapter;
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
