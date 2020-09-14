package com.minicdesign.catalog.api.items.repositories;

import java.util.Optional;
import java.util.stream.Collectors;

import com.minicdesign.catalog.api.exceptions.ItemNotFoundException;
import com.minicdesign.catalog.api.items.domain.ItemDomain;
import com.minicdesign.catalog.api.items.domain.ItemType;
import com.minicdesign.catalog.api.items.repositories.db.ItemDao;
import com.minicdesign.catalog.api.items.repositories.db.LibraryFilterJpaRepository;
import com.minicdesign.catalog.api.libraries.domain.LibraryDomain;
import com.minicdesign.catalog.api.libraries.repositories.db.LibraryDao;
import org.jetbrains.annotations.Nullable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
public abstract class AbstractRepositoryAdapter<T extends ItemDao> implements RepositoryAdapter {

    @Override
    public Page<ItemDomain> getItemsForPage(long libraryId, int page, int size) {

        LibraryDao libraryDao = new LibraryDao();
        libraryDao.setId(libraryId);

        Page<? extends ItemDao> daoPage = getJpaRepository().findAllByLibrary(libraryDao, PageRequest.of(page, size));

        return new PageImpl<>(
                daoPage.getContent().stream()
                        .map(ItemDao::daoToDomain)
                        .collect(Collectors.toList()),
                daoPage.getPageable(),
                daoPage.getTotalElements());
    }

    @Override
    public ItemDomain getItem(long itemId) {

        Optional<T> dao = getJpaRepository().findById(itemId);

        if (dao.isEmpty()) {
            throw new ItemNotFoundException(String.format("Item with id %d could not be found.", itemId));
        }

        return dao.get().daoToDomain();

    }

    @Override
    public void deleteItem(long itemId) {
        try {
            getJpaRepository().deleteById(itemId);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Item with " + itemId + " not found, ignoring Exception.");
        }
    }

    protected abstract LibraryFilterJpaRepository<T, Long> getJpaRepository();
}
