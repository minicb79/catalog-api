package com.minicdesign.catalog.api.libraries.controllers.usecases;

import com.minicdesign.catalog.api.libraries.domain.LibraryDomain;
import org.springframework.stereotype.Service;

@Service
public interface UpdateLibraryUseCase {

  LibraryDomain updateLibrary(LibraryDomain domain);
}
