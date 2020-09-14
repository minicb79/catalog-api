package com.minicdesign.catalog.api.items.repositories;

import com.minicdesign.catalog.api.items.domain.ItemDomain;
import com.minicdesign.catalog.api.items.repositories.db.RecipeDao;
import com.minicdesign.catalog.api.libraries.repositories.db.LibraryDao;
import org.springframework.stereotype.Component;

@Component
public class RecipeDaoMapper {

    public static RecipeDao domainToDao(ItemDomain domain, LibraryDao libraryDao) {
        RecipeDao dao = new RecipeDao();
        dao.setId(domain.getId());
        dao.setTitle(domain.getTitle());
        dao.setSubtitle(domain.getSubtitle());
        dao.setSummary(domain.getSummary());
        dao.setLibrary(libraryDao);
        dao.setMeal(domain.getMeal());
        return dao;
    }
}
