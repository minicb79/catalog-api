package com.minicdesign.catalog.api.items.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;

import com.minicdesign.catalog.api.items.controllers.usecases.CreateItemUseCase;
import com.minicdesign.catalog.api.items.domain.ItemDomain;
import com.minicdesign.catalog.api.items.domain.ItemType;
import com.minicdesign.catalog.api.items.repositories.RepositoryAdapter;
import com.minicdesign.catalog.api.libraries.domain.LibraryDomain;
import com.minicdesign.catalog.api.libraries.repositories.LibraryRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateItemService implements CreateItemUseCase {

  private final List<RepositoryAdapter> repositoryAdapterList;
  private final LibraryRepositoryAdapter libraryRepository;

  private Map<ItemType, RepositoryAdapter> repositoryAdapterMap = new HashMap<>();

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
}
