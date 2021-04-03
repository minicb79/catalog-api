package com.minicdesign.catalog.api.dbTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import com.minicdesign.catalog.api.libraries.repositories.db.LibraryDao;
import com.minicdesign.catalog.api.libraries.repositories.db.LibraryJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;


@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
@ActiveProfiles("test")
public class LibraryJpaRepositoryTest {

    @Autowired
    private LibraryJpaRepository repo;

    @Test
    public void testDbAccess() {
        List<LibraryDao> libraryList = repo.findAll();
        assertEquals(14, libraryList.size());
    }

    @Test
    public void testSaveLibrary() {
        LibraryDao library = new LibraryDao();
        library.setName("New Library");
        library.setDescription("This has a description");

        LibraryDao savedDao = repo.save(library);

        assertNotNull(savedDao.getId());
        assertEquals(savedDao.getId(), library.getId());
    }

    @Test
    public void testLibraryCountById() {
        int count = repo.countById(3L);
        assertEquals(1, count);
    }

    @Test
    public void testLibraryCount() {
        long count = repo.count();
        assertEquals(14, count);
    }

}
