package com.minicdesign.catalog.api.items.repositories;

import com.minicdesign.catalog.api.items.domain.ItemDomain;
import com.minicdesign.catalog.api.items.domain.ItemType;
import com.minicdesign.catalog.api.libraries.domain.LibraryDomain;
import org.jetbrains.annotations.Nullable;

public interface RepositoryAdapter {

    ItemType appliesTo();

    /**
     * Creates an Item in the Database.
     *
     * @param item          The Item to save to the repository.
     * @param libraryDomain The domain object of the Library that this Item will be related to. Although this parameter is
     *                      nullable, it will not be associated with a Library if not specified, and as such is not recommended.
     * @return The fully created ItemDomain, with id.
     */
    ItemDomain createItem(ItemDomain item, @Nullable LibraryDomain libraryDomain);
}
