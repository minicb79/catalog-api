package com.minicdesign.catalog.api.dbTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.minicdesign.catalog.api.items.repositories.db.BookDao;
import com.minicdesign.catalog.api.items.repositories.db.ItemDao;
import com.minicdesign.catalog.api.items.repositories.db.ItemJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
@ActiveProfiles("test")
public class ItemJpaRepositoryTest {

  @Autowired
  private ItemJpaRepository repo;

  @Test
  public void testDbAccess() {
    List<ItemDao> itemList = repo.findAll();

    assertEquals(7, itemList.size());
    ItemDao dao = itemList.get(0);
    assertEquals("Item 1", dao.getTitle());
    assertEquals(BookDao.class, dao.getClass());

    BookDao bDao = (BookDao)dao;
    assertEquals(111, bDao.getPages());
  }
}
