package com.minicdesign.catalog.api.items.services;

import com.minicdesign.catalog.api.items.controllers.usecases.GetItemListUseCase;
import com.minicdesign.catalog.api.items.domain.ItemDomain;
import com.minicdesign.catalog.api.items.repositories.ItemRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetItemListService implements GetItemListUseCase {

    private final ItemRepositoryAdapter itemRepositoryAdapter;

    @Override
    public Page<ItemDomain> getItemList(long libraryId, int page, int size) {
        return itemRepositoryAdapter.getItemsForPage(libraryId, page, size);
    }
}
