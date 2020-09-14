package com.minicdesign.catalog.api.items.repositories;

import com.minicdesign.catalog.api.items.domain.ItemDomain;
import com.minicdesign.catalog.api.items.domain.ItemType;
import com.minicdesign.catalog.api.items.repositories.db.BookDao;
import com.minicdesign.catalog.api.items.repositories.db.BookJpaRepository;
import com.minicdesign.catalog.api.items.repositories.db.ItemDao;
import com.minicdesign.catalog.api.libraries.domain.LibraryDomain;
import com.minicdesign.catalog.api.libraries.repositories.LibraryDaoMapper;
import com.minicdesign.catalog.api.libraries.repositories.db.LibraryDao;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookRepositoryAdapter implements RepositoryAdapter {

    private final BookJpaRepository jpaRepository;

    @Override
    public ItemType appliesTo() {
        return ItemType.BOOK;
    }

    @Override
    public ItemDomain createItem(ItemDomain domain, LibraryDomain libraryDomain) {

        if (domain == null) {
            throw new IllegalArgumentException("ItemDomain must not be null when create an Item.");
        }

        LibraryDao libraryDao = LibraryDaoMapper.domainToDao(libraryDomain);

        BookDao itemDao = jpaRepository.save(BookDaoMapper.domainToDao(domain, libraryDao));
        return itemDao.daoToDomain();
    }

    @Override
    public Page<ItemDomain> getItemsForPage(long libraryId, int page, int size) {
        return null;
    }
}
