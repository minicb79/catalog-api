package com.minicdesign.catalog.api.items.repositories;

import com.minicdesign.catalog.api.items.domain.ItemDomain;
import com.minicdesign.catalog.api.items.repositories.db.BookDao;
import com.minicdesign.catalog.api.items.repositories.db.BookJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookRepositoryAdapter {

  private final BookJpaRepository jpaRepository;

  public ItemDomain createBook(ItemDomain domain) {

    if (domain == null) {
      throw new IllegalArgumentException("BookDomain must not be null when creating a Book.");
    }

    BookDao dao = jpaRepository.save(BookDaoMapper.domainToDao(domain));
    return BookDaoMapper.daoToDomain(dao);

  }
}
