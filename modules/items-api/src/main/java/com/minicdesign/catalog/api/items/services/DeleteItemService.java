package com.minicdesign.catalog.api.items.services;

import com.minicdesign.catalog.api.items.controllers.usecases.DeleteItemUseCase;
import com.minicdesign.catalog.api.items.repositories.ItemRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteItemService implements DeleteItemUseCase {

    private final ItemRepositoryAdapter itemRepositoryAdapter;

    @Override
    public void deleteItem(long id) {
        itemRepositoryAdapter.deleteItem(id);
    }
}
