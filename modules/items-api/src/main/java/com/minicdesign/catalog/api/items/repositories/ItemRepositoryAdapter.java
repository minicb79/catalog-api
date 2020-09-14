package com.minicdesign.catalog.api.items.repositories;

import java.util.function.Function;
import java.util.stream.Collectors;

import com.minicdesign.catalog.api.items.domain.ItemDomain;
import com.minicdesign.catalog.api.items.domain.ItemType;
import com.minicdesign.catalog.api.items.repositories.db.ItemDao;
import com.minicdesign.catalog.api.items.repositories.db.ItemJpaRepository;
import com.minicdesign.catalog.api.libraries.domain.LibraryDomain;
import com.minicdesign.catalog.api.libraries.repositories.db.LibraryDao;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryAdapter implements RepositoryAdapter {

    private final ItemJpaRepository jpaRepository;

    @Override
    public ItemType appliesTo() {
        return ItemType.ALL;
    }

    @Override
    public ItemDomain createItem(ItemDomain item, @Nullable LibraryDomain libraryDomain) {
        throw new UnsupportedOperationException("Can not create Item in ItemRepositoryAdapter.");
    }

    @Override
    public Page<ItemDomain> getItemsForPage(long libraryId, int page, int size) {

        LibraryDao libraryDao = new LibraryDao();
        libraryDao.setId(libraryId);

        Page<? extends ItemDao> daoPage = jpaRepository.findAllByLibrary(libraryDao, PageRequest.of(page, size));

        return new PageImpl<>(
                daoPage.getContent().stream()
                        .map(ItemDao::daoToDomain)
                        .collect(Collectors.toList()),
                daoPage.getPageable(),
                daoPage.getTotalElements());

    }

}
