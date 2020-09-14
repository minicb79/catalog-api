package com.minicdesign.catalog.api.items.controllers.usecases;

import org.springframework.stereotype.Service;

@Service
public interface DeleteItemUseCase {

    void deleteItem(long id);
}
