package com.minicdesign.catalog.api.items.repositories;

import com.minicdesign.catalog.api.items.domain.ItemDomain;
import com.minicdesign.catalog.api.items.domain.ItemType;
import com.minicdesign.catalog.api.items.repositories.db.LibraryFilterJpaRepository;
import com.minicdesign.catalog.api.items.repositories.db.RecipeDao;
import com.minicdesign.catalog.api.items.repositories.db.RecipeJpaRepository;
import com.minicdesign.catalog.api.libraries.domain.LibraryDomain;
import com.minicdesign.catalog.api.libraries.repositories.LibraryDaoMapper;
import com.minicdesign.catalog.api.libraries.repositories.db.LibraryDao;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public Page<ItemDomain> getItemsForPage(long libraryId, int page, int size) {
        return null;
    }

    @Override
    public ItemDomain getItem(long itemId) {
        return null;
    }

    @Override
    protected LibraryFilterJpaRepository<RecipeDao, Long> getJpaRepository() {
        return jpaRepository;
    }

}
