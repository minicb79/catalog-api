package com.minicdesign.catalog.api.dbTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import com.minicdesign.catalog.api.items.repositories.db.RecipeDao;
import com.minicdesign.catalog.api.items.repositories.db.RecipeJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
@ActiveProfiles("test")
public class RecipeJpaRepositoryTest {

    @Autowired
    private RecipeJpaRepository repo;

    @Test
    public void testDbAccess() {
        List<RecipeDao> itemList = repo.findAll();


        assertEquals(1, itemList.size());
        RecipeDao dao = itemList.get(0);
        assertEquals("Item 6", dao.getTitle());
        assertEquals("breakfast", dao.getMeal());
        assertNull(dao.getSubtitle());
        assertNull(dao.getSummary());
    }
}
