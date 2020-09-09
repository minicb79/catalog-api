package com.minicdesign.catalog.api.libraries.controllers.usecases;

import com.minicdesign.catalog.api.libraries.domain.LibraryDomain;

public interface GetLibraryUseCase {

  LibraryDomain getLibrary(Long id);
}
