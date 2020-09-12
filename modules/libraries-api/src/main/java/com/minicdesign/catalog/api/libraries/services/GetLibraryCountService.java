package com.minicdesign.catalog.api.libraries.services;

import com.minicdesign.catalog.api.libraries.controllers.usecases.GetLibraryCountUseCase;
import com.minicdesign.catalog.api.libraries.repositories.LibraryRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetLibraryCountService implements GetLibraryCountUseCase {

    private final LibraryRepositoryAdapter repository;

    @Override
    public long getCount() {
        return repository.getCount();
    }
}
