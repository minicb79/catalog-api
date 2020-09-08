package com.minicdesign.catalog.api.integrationTests.controllers.usecases;

import com.minicdesign.catalog.api.integrationTests.domain.LibraryDomain;
import org.springframework.stereotype.Service;

@Service
public interface CreateLibraryUseCase {

  LibraryDomain createLibrary(LibraryDomain library);

}
