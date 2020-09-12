package com.minicdesign.catalog.api.items.repositories;

import com.minicdesign.catalog.api.items.domain.ItemDomain;
import com.minicdesign.catalog.api.items.domain.ItemType;
import com.minicdesign.catalog.api.items.repositories.db.BookDao;
import com.minicdesign.catalog.api.libraries.repositories.db.LibraryDao;
import org.springframework.stereotype.Component;

@Component
public class BookDaoMapper {

    public static ItemDomain daoToDomain(BookDao dao) {
        ItemDomain domain = new ItemDomain();
        domain.setId(dao.getId());
        domain.setType(ItemType.BOOK);
        domain.setTitle(dao.getTitle());
        domain.setSubtitle(dao.getSubtitle());
        domain.setSummary(dao.getSummary());
        domain.setLibraryId(dao.getLibrary().getId());
        domain.setAuthor(dao.getAuthor());
        domain.setIsbn(dao.getIsbn());
        domain.setBarcode(dao.getBarcode());
        domain.setPages(dao.getPages());
        return domain;
    }

    public static BookDao domainToDao(ItemDomain domain, LibraryDao libraryDao) {
        BookDao dao = new BookDao();
        dao.setId(domain.getId());
        dao.setTitle(domain.getTitle());
        dao.setSubtitle(domain.getSubtitle());
        dao.setSummary(domain.getSummary());
        dao.setAuthor(domain.getAuthor());
        dao.setIsbn(domain.getIsbn());
        dao.setBarcode(domain.getBarcode());
        dao.setPages(domain.getPages());
        dao.setLibrary(libraryDao);
        return dao;
    }
}
