package com.minicdesign.catalog.api.items.repositories;

import java.util.Optional;

import com.minicdesign.catalog.api.exceptions.ItemNotFoundException;
import com.minicdesign.catalog.api.items.domain.ItemDomain;
import com.minicdesign.catalog.api.items.domain.ItemType;
import com.minicdesign.catalog.api.items.repositories.db.BookDao;
import com.minicdesign.catalog.api.items.repositories.db.BookJpaRepository;
import com.minicdesign.catalog.api.items.repositories.db.LibraryFilterJpaRepository;
import com.minicdesign.catalog.api.libraries.domain.LibraryDomain;
import com.minicdesign.catalog.api.libraries.repositories.LibraryDaoMapper;
import com.minicdesign.catalog.api.libraries.repositories.db.LibraryDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookRepositoryAdapter extends AbstractRepositoryAdapter<BookDao> {

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
    public ItemDomain updateItem(ItemDomain item) {
        Optional<BookDao> dao = jpaRepository.findById(item.getId());

        if (dao.isEmpty()) {
            throw new ItemNotFoundException(String.format("Item with id %d could not be found.", item.getId()));
        }

        LibraryDao libraryDao = dao.get().getLibrary();

        BookDao itemDao = jpaRepository.save(BookDaoMapper.domainToDao(item, libraryDao));
        return itemDao.daoToDomain();
    }

    @Override
    protected LibraryFilterJpaRepository<BookDao, Long> getJpaRepository() {
        return jpaRepository;
    }
}
