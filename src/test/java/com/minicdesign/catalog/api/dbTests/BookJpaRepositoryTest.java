package com.minicdesign.catalog.api.dbTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import com.minicdesign.catalog.api.items.repositories.db.BookJpaRepository;
import com.minicdesign.catalog.api.items.repositories.db.BookDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
@ActiveProfiles("test")
public class BookJpaRepositoryTest {

  @Autowired
  private BookJpaRepository repo;

  @Test
  public void testDbAccess() {
    List<BookDao> itemList = repo.findAll();

    BookDao dao = itemList.get(5);

    assertEquals(6, itemList.size());
    assertEquals("Item 7", dao.getTitle());
    assertEquals("The 7th Subtitle", dao.getSubtitle());
    assertNull(dao.getSummary());
    assertEquals("7777777777", dao.getBarcode());
  }

}
