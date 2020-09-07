package com.minicdesign.catalog.api.libraries.repositories;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.minicdesign.catalog.api.libraries.domain.LibraryDomain;
import com.minicdesign.catalog.api.libraries.repositories.db.LibraryDao;
import com.minicdesign.catalog.api.libraries.repositories.db.LibraryJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LibraryRepositoryTest {

  @Mock
  private LibraryJpaRepository jpaRepository;

  private LibraryRepository repository;

  @BeforeEach
  void setup() {
    repository = new LibraryRepository(jpaRepository);
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

    assertThrows(IllegalArgumentException.class, () -> {
      repository.createLibrary(null);
    });
  }

}
