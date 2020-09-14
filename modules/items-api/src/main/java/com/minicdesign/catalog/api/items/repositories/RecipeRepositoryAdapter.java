package com.minicdesign.catalog.api.items.repositories;

import java.util.Optional;

import com.minicdesign.catalog.api.exceptions.ItemNotFoundException;
import com.minicdesign.catalog.api.items.domain.ItemDomain;
import com.minicdesign.catalog.api.items.domain.ItemType;
import com.minicdesign.catalog.api.items.repositories.db.BookDao;
import com.minicdesign.catalog.api.items.repositories.db.LibraryFilterJpaRepository;
import com.minicdesign.catalog.api.items.repositories.db.RecipeDao;
import com.minicdesign.catalog.api.items.repositories.db.RecipeJpaRepository;
import com.minicdesign.catalog.api.libraries.domain.LibraryDomain;
import com.minicdesign.catalog.api.libraries.repositories.LibraryDaoMapper;
import com.minicdesign.catalog.api.libraries.repositories.db.LibraryDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RecipeRepositoryAdapter extends AbstractRepositoryAdapter<RecipeDao> {

    private final RecipeJpaRepository jpaRepository;

    @Override
    public ItemType appliesTo() {
        return ItemType.RECIPE;
    }

    @Override
    public ItemDomain createItem(ItemDomain domain, LibraryDomain libraryDomain) {

        if (domain == null) {
            throw new IllegalArgumentException("ItemDomain must not be null when create an Item.");
        }

        LibraryDao libraryDao = LibraryDaoMapper.domainToDao(libraryDomain);

        RecipeDao itemDao = jpaRepository.save(RecipeDaoMapper.domainToDao(domain, libraryDao));
        return itemDao.daoToDomain();
    }

    @Override
    public void updateItem(ItemDomain item) {
        Optional<RecipeDao> dao = jpaRepository.findById(item.getId());

        if (dao.isEmpty()) {
            throw new ItemNotFoundException(String.format("Item with id %d could not be found.", item.getId()));
        }

        LibraryDao libraryDao = dao.get().getLibrary();

        jpaRepository.save(RecipeDaoMapper.domainToDao(item, libraryDao));
    }

    @Override
    protected LibraryFilterJpaRepository<RecipeDao, Long> getJpaRepository() {
        return jpaRepository;
    }

}
