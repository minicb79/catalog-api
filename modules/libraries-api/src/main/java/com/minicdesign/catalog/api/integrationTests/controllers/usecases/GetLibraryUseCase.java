package com.minicdesign.catalog.api.integrationTests.controllers.usecases;

import com.minicdesign.catalog.api.integrationTests.domain.LibraryDomain;

public interface GetLibraryUseCase {

  LibraryDomain getLibrary(Long id);
}
