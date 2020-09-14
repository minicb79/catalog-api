package com.minicdesign.catalog.api.items.controllers.usecases;

import com.minicdesign.catalog.api.items.domain.ItemDomain;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface GetItemListUseCase {

    Page<ItemDomain> getItemList(long libraryId, int page, int size);
}
