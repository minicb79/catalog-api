package com.minicdesign.catalog.api.items.repositories;

import java.util.Optional;

import com.minicdesign.catalog.api.exceptions.ItemNotFoundException;
import com.minicdesign.catalog.api.items.domain.ItemDomain;
import com.minicdesign.catalog.api.items.domain.ItemType;
import com.minicdesign.catalog.api.items.repositories.db.BookDao;
import com.minicdesign.catalog.api.items.repositories.db.LibraryFilterJpaRepository;
import com.minicdesign.catalog.api.items.repositories.db.UrlDao;
import com.minicdesign.catalog.api.items.repositories.db.UrlJpaRepository;
import com.minicdesign.catalog.api.libraries.domain.LibraryDomain;
import com.minicdesign.catalog.api.libraries.repositories.LibraryDaoMapper;
import com.minicdesign.catalog.api.libraries.repositories.db.LibraryDao;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UrlRepositoryAdapter extends AbstractRepositoryAdapter<UrlDao> {

    private final UrlJpaRepository jpaRepository;

    @Override
    protected LibraryFilterJpaRepository<UrlDao, Long> getJpaRepository() {
        return jpaRepository;
    }

    @Override
    public ItemType appliesTo() {
        return ItemType.URL;
    }

    @Override
    public ItemDomain createItem(ItemDomain domain, @Nullable LibraryDomain libraryDomain) {
        if (domain == null) {
            throw new IllegalArgumentException("ItemDomain must not be null when create an Item.");
        }

        LibraryDao libraryDao = LibraryDaoMapper.domainToDao(libraryDomain);

        UrlDao itemDao = jpaRepository.save(UrlDaoMapper.domainToDao(domain, libraryDao));
        return itemDao.daoToDomain();
    }

    @Override
    public void updateItem(ItemDomain item) {
        Optional<UrlDao> dao = jpaRepository.findById(item.getId());

        if (dao.isEmpty()) {
            throw new ItemNotFoundException(String.format("Item with id %d could not be found.", item.getId()));
        }

        LibraryDao libraryDao = dao.get().getLibrary();

        jpaRepository.save(UrlDaoMapper.domainToDao(item, libraryDao));
    }
}
