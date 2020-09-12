package com.minicdesign.catalog.api.items.repositories;

import com.minicdesign.catalog.api.items.domain.ItemDomain;
import com.minicdesign.catalog.api.items.domain.ItemType;
import com.minicdesign.catalog.api.libraries.domain.LibraryDomain;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepositoryAdapter implements RepositoryAdapter {

    @Override
    public ItemType appliesTo() {
        return ItemType.ALL;
    }

    @Override
    public ItemDomain createItem(ItemDomain item, @Nullable LibraryDomain libraryDomain) {
        throw new UnsupportedOperationException("Can not create Item in ItemRepositoryAdapter.");
    }

}
