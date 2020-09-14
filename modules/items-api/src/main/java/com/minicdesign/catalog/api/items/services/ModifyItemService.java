package com.minicdesign.catalog.api.items.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;

import com.minicdesign.catalog.api.items.controllers.usecases.CreateItemUseCase;
import com.minicdesign.catalog.api.items.controllers.usecases.UpdateItemUseCase;
import com.minicdesign.catalog.api.items.domain.ItemDomain;
import com.minicdesign.catalog.api.items.domain.ItemType;
import com.minicdesign.catalog.api.items.repositories.RepositoryAdapter;
import com.minicdesign.catalog.api.libraries.domain.LibraryDomain;
import com.minicdesign.catalog.api.libraries.repositories.LibraryRepositoryAdapter;
import org.springframework.stereotype.Service;

@Service
public class ModifyItemService implements CreateItemUseCase, UpdateItemUseCase {

    private final List<RepositoryAdapter> repositoryAdapterList;
    private final LibraryRepositoryAdapter libraryRepository;

    private final Map<ItemType, RepositoryAdapter> repositoryAdapterMap = new HashMap<>();

    public ModifyItemService(List<RepositoryAdapter> repositoryAdapterList, LibraryRepositoryAdapter libraryRepository) {
        this.repositoryAdapterList = repositoryAdapterList;
        this.libraryRepository = libraryRepository;
    }

    @PostConstruct
    public void init() {
        for (RepositoryAdapter adapter : repositoryAdapterList) {
            repositoryAdapterMap.put(adapter.appliesTo(), adapter);
        }
    }

    @Override
    public ItemDomain createItem(ItemDomain item) {

        if (item == null) {
            throw new IllegalArgumentException("ItemDomain must not be null when creating an Item.");
        }

        RepositoryAdapter repository = repositoryAdapterMap.get(item.getType());

        LibraryDomain libraryDomain = libraryRepository.getLibrary(item.getLibraryId());

        return repository.createItem(item, libraryDomain);
    }

    @Override
    public void updateItem(ItemDomain item) {

        if (item == null) {
            throw new IllegalArgumentException("ItemDomain must not be null when creating an Item.");
        }

        RepositoryAdapter repository = repositoryAdapterMap.get(item.getType());;

        repository.updateItem(item);
    }
}
