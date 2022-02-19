package com.minicdesign.catalog.api.items.controllers.usecases;

import com.minicdesign.catalog.api.items.domain.ItemDomain;
import org.springframework.stereotype.Service;

@Service
public interface UpdateItemUseCase {

    ItemDomain updateItem(ItemDomain domain);
}
