package com.minicdesign.catalog.api.items.repositories;

import java.util.Optional;
import java.util.stream.Collectors;

import com.minicdesign.catalog.api.exceptions.ItemNotFoundException;
import com.minicdesign.catalog.api.items.domain.ItemDomain;
import com.minicdesign.catalog.api.items.domain.ItemType;
import com.minicdesign.catalog.api.items.repositories.db.ItemDao;
import com.minicdesign.catalog.api.items.repositories.db.ItemJpaRepository;
import com.minicdesign.catalog.api.items.repositories.db.LibraryFilterJpaRepository;
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
public class ItemRepositoryAdapter extends AbstractRepositoryAdapter<ItemDao> {

    private final ItemJpaRepository jpaRepository;

    @Override
    public ItemType appliesTo() {
        return ItemType.ALL;
    }

    @Override
    public ItemDomain createItem(ItemDomain item, @Nullable LibraryDomain libraryDomain) {
        throw new UnsupportedOperationException("Can not create Item in ItemRepositoryAdapter. Consider using one of the more specific RepositoryAdapters.");
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

    @Override
    public ItemDomain getItem(long itemId) {

        Optional<ItemDao> dao = jpaRepository.findById(itemId);

        if (dao.isEmpty()) {
            throw new ItemNotFoundException(String.format("Item with id %d could not be found.", itemId));
        }

        return dao.get().daoToDomain();

    }

    @Override
    public ItemDomain updateItem(ItemDomain item) {
        throw new UnsupportedOperationException("Can not update Item in ItemRepositoryAdapter. Consider using one of the more specific RepositoryAdapters.");
    }

    @Override
    protected LibraryFilterJpaRepository<ItemDao, Long> getJpaRepository() {
        return jpaRepository;
    }
}
