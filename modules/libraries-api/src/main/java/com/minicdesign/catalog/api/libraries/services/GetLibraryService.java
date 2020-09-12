package com.minicdesign.catalog.api.libraries.services;

import com.minicdesign.catalog.api.libraries.controllers.usecases.GetLibraryUseCase;
import com.minicdesign.catalog.api.libraries.domain.LibraryDomain;
import com.minicdesign.catalog.api.libraries.repositories.LibraryRepositoryAdapter;
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
