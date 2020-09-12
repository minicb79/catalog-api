package com.minicdesign.catalog.api.libraries.repositories;

import com.minicdesign.catalog.api.libraries.domain.LibraryDomain;
import com.minicdesign.catalog.api.libraries.repositories.db.LibraryDao;
import org.springframework.stereotype.Component;

@Component
public class LibraryDaoMapper {

    public static LibraryDomain daoToDomain(LibraryDao dao) {
        LibraryDomain domain = new LibraryDomain();
        domain.setId(dao.getId());
        domain.setName(dao.getName());
        domain.setDescription(dao.getDescription());
        return domain;
    }

    public static LibraryDao domainToDao(LibraryDomain domain) {
        LibraryDao dao = new LibraryDao();
        dao.setId(domain.getId());
        dao.setName(domain.getName());
        dao.setDescription(domain.getDescription());
        return dao;
    }
}
