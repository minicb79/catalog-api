package com.minicdesign.catalog.api.libraries.controllers.usecases;

import com.minicdesign.catalog.api.libraries.domain.LibraryDomain;
import org.springframework.stereotype.Service;

@Service
public interface CreateLibraryUseCase {

  LibraryDomain createLibrary(LibraryDomain library);

}
