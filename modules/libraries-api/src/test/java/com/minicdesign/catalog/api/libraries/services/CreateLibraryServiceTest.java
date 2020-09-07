package com.minicdesign.catalog.api.libraries.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.minicdesign.catalog.api.libraries.domain.LibraryDomain;
import com.minicdesign.catalog.api.libraries.repositories.LibraryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreateLibraryServiceTest {

  @Mock
  LibraryRepository repository;

  CreateLibraryService service;

  @BeforeEach
  void setup() {
    service = new CreateLibraryService(repository);
  }

  @Test
  void givenNullLibrary_whenCreateLibrary_thenExceptionThrown() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      service.createLibrary(null);
    });
  }

  @Test
  void givenValidLibrary_whenCreateLibrary_thenDomainWithIdReturned() {
    LibraryDomain requestDomain = new LibraryDomain();
    requestDomain.setName("Library Name");
    requestDomain.setDescription("A Description for the Library.");

    LibraryDomain repoDomain = new LibraryDomain();
    repoDomain.setId(4L);
    repoDomain.setName("Library Name");
    repoDomain.setDescription("A Description for the Library.");

    when(repository.createLibrary(any())).thenReturn(repoDomain);

    LibraryDomain createdDomain = service.createLibrary(requestDomain);

    assertNotNull(createdDomain);
    assertEquals(repoDomain, createdDomain);
  }
}
