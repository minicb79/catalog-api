package com.minicdesign.catalog.api.items.services;

import com.minicdesign.catalog.api.items.controllers.usecases.GetItemUseCase;
import com.minicdesign.catalog.api.items.domain.ItemDomain;
import com.minicdesign.catalog.api.items.repositories.ItemRepositoryAdapter;
import org.springframework.stereotype.Service;

@Service
public class GetItemService implements GetItemUseCase {

    ItemRepositoryAdapter itemRepositoryAdapter;

    @Override
    public ItemDomain getItem(long itemId) {
        itemRepositoryAdapter.getItem(itemId);
        return null;
    }
}
