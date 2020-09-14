package com.minicdesign.catalog.api.items.repositories;

import com.minicdesign.catalog.api.items.domain.ItemDomain;
import com.minicdesign.catalog.api.items.repositories.db.UrlDao;
import com.minicdesign.catalog.api.libraries.repositories.db.LibraryDao;
import org.springframework.stereotype.Component;

@Component
public class UrlDaoMapper {

    public static UrlDao domainToDao(ItemDomain domain, LibraryDao libraryDao) {
        UrlDao dao = new UrlDao();
        dao.setId(domain.getId());
        dao.setTitle(domain.getTitle());
        dao.setSubtitle(domain.getSubtitle());
        dao.setSummary(domain.getSummary());
        dao.setLibrary(libraryDao);
        dao.setUrl(domain.getUrl());
        return dao;
    }
}
