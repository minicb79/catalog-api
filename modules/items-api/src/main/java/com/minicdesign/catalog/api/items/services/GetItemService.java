package com.minicdesign.catalog.api.items.services;

import com.minicdesign.catalog.api.items.controllers.usecases.GetItemUseCase;
import com.minicdesign.catalog.api.items.domain.ItemDomain;
import com.minicdesign.catalog.api.items.repositories.ItemRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetItemService implements GetItemUseCase {

    private final ItemRepositoryAdapter itemRepositoryAdapter;

    @Override
    public ItemDomain getItem(long itemId) {
        return itemRepositoryAdapter.getItem(itemId);
    }
}
