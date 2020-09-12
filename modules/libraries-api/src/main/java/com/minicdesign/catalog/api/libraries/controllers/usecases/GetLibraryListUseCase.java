package com.minicdesign.catalog.api.libraries.controllers.usecases;

import com.minicdesign.catalog.api.libraries.domain.LibraryDomain;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface GetLibraryListUseCase {

    Page<LibraryDomain> getLibraryList(int page, int size);
}
