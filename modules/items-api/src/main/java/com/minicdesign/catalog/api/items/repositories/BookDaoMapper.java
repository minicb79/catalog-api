package com.minicdesign.catalog.api.items.repositories;

import com.minicdesign.catalog.api.items.domain.ItemDomain;
import com.minicdesign.catalog.api.items.repositories.db.BookDao;
import org.springframework.stereotype.Component;

@Component
public class BookDaoMapper {

  public static ItemDomain daoToDomain(BookDao dao) {
    return ItemDomain.builder()
        .id(dao.getId())
        .title(dao.getTitle())
        .subtitle(dao.getSubtitle())
        .summary(dao.getSummary())
        .author(dao.getAuthor())
        .isbn(dao.getIsbn())
        .barcode(dao.getBarcode())
        .pages(dao.getPages())
        .build();
  }

  public static BookDao domainToDao(ItemDomain domain) {
    BookDao dao = new BookDao();
    dao.setId(domain.getId());
    dao.setTitle(domain.getTitle());
    dao.setSubtitle(domain.getSubtitle());
    dao.setSummary(domain.getSummary());
    dao.setAuthor(domain.getAuthor());
    dao.setIsbn(domain.getIsbn());
    dao.setBarcode(domain.getBarcode());
    dao.setPages(domain.getPages());
    return dao;
  }
}
