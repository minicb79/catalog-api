package com.minicdesign.catalog.api.libraries.repositories;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.minicdesign.catalog.api.exceptions.ItemNotFoundException;
import com.minicdesign.catalog.api.libraries.domain.LibraryDomain;
import com.minicdesign.catalog.api.libraries.repositories.db.LibraryDao;
import com.minicdesign.catalog.api.libraries.repositories.db.LibraryJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LibraryRepositoryAdapterTest {

    @Mock
    private LibraryJpaRepository jpaRepository;

    private LibraryRepositoryAdapter repository;

    @BeforeEach
    void setup() {
        repository = new LibraryRepositoryAdapter(jpaRepository);
    }

    @Test
    void givenValidDomain_whenCreateLibrary_thenUpdatedValidDomainReturned() {

        LibraryDomain requestDomain = new LibraryDomain();
        requestDomain.setName("Library Name");
        requestDomain.setDescription("Library Description that has lots of data.");

        LibraryDao savedDomain = new LibraryDao();
        savedDomain.setId(2L);
        savedDomain.setName("Library Name");
        savedDomain.setDescription("Library Description that has lots of data.");

        when(jpaRepository.save(any())).thenReturn(savedDomain);

        LibraryDomain responseDomain = repository.createLibrary(requestDomain);

        assertNotNull(responseDomain);
        assertEquals(savedDomain.getId(), responseDomain.getId());
        assertEquals(savedDomain.getName(), responseDomain.getName());
        assertEquals(savedDomain.getDescription(), responseDomain.getDescription());
    }

    @Test
    void givenNullDomain_whenCreateLibrary_thenIllegalArgumentExceptionThrown() {

        assertThrows(IllegalArgumentException.class, () -> repository.createLibrary(null));
    }

    @Test
    void givenKnownLibraryId_whenGetLibrary_thenLibraryDaoReturned() {

        LibraryDao savedDao = new LibraryDao();
        savedDao.setId(1L);
        savedDao.setName("Library Name");
        savedDao.setDescription("Library Description");

        when(jpaRepository.findById(anyLong())).thenReturn(Optional.of(savedDao));

        LibraryDomain responseDomain = repository.getLibrary(1L);

        assertNotNull(responseDomain);
        assertEquals(1L, savedDao.getId());
        assertEquals("Library Name", savedDao.getName());
        assertEquals("Library Description", savedDao.getDescription());
    }

    @Test
    void givenUnknownLibraryId_whenGetLibrary_thenItemNotFoundExceptionThrown() {

        assertThrows(ItemNotFoundException.class, () -> repository.getLibrary(100L));
    }

    @Test
    void givenValidLibrary_whenUpdateLibrary_thenUpdatedLibraryReturned() {
        LibraryDomain requestedDomain = new LibraryDomain();
        requestedDomain.setId(1L);
        requestedDomain.setName("Library Name");
        requestedDomain.setDescription("Library Description");

        LibraryDao libraryDao = new LibraryDao();
        libraryDao.setId(1L);
        libraryDao.setName("Library Name");
        libraryDao.setDescription("Library Description");

        when(jpaRepository.countById(anyLong())).thenReturn(1);
        when(jpaRepository.save(any())).thenReturn(libraryDao);

        LibraryDomain response = repository.updateLibrary(requestedDomain);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Library Name", response.getName());
        assertEquals("Library Description", response.getDescription());
    }

    @Test
    void givenInvalidLibraryWithMissingId_whenUpdateLibrary_thenItemNotFoundExceptionThrown() {

        LibraryDomain invalidLibrary = new LibraryDomain();
        invalidLibrary.setName("Library Name");
        invalidLibrary.setDescription("A valid description for invalid library - missing ID");

        assertThrows(ItemNotFoundException.class, () -> repository.updateLibrary(invalidLibrary));
    }

    @Test
    void givenNullLibrary_whenUpdateLibrary_thenIllegalArgumentExceptionThrown() {

        assertThrows(IllegalArgumentException.class, () -> repository.updateLibrary(null));
    }

}
