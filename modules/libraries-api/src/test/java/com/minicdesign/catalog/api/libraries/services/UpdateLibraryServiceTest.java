package com.minicdesign.catalog.api.libraries.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.minicdesign.catalog.api.exceptions.ItemNotFoundException;
import com.minicdesign.catalog.api.libraries.domain.LibraryDomain;
import com.minicdesign.catalog.api.libraries.repositories.LibraryRepositoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UpdateLibraryServiceTest {

  @Mock
  LibraryRepositoryAdapter repository;

  UpdateLibraryService service;

  @BeforeEach
  public void setup() {
    service = new UpdateLibraryService(repository);
  }

  @Test
  public void givenValidLibrary_whenUpdateLibrary_thenUpdatedLibraryReturned() {
    LibraryDomain repoDomain = new LibraryDomain();
    repoDomain.setId(4L);
    repoDomain.setName("Library Name");
    repoDomain.setDescription("A Description for the Library.");

    when(repository.updateLibrary(any())).thenReturn(repoDomain);

    LibraryDomain returnedDomain = service.updateLibrary(repoDomain);

    assertNotNull(returnedDomain);
    assertEquals(4L, returnedDomain.getId());
  }

  @Test
  public void givenInvalidLibraryMissingId_whenUpdateLibrary_thenItemNotFoundException() {
    LibraryDomain invalidLibrary = new LibraryDomain();
    invalidLibrary.setName("Library Name");
    invalidLibrary.setDescription("A valid description for invalid library - missing ID");

    when(repository.updateLibrary(any())).thenThrow(new ItemNotFoundException());

    assertThrows(ItemNotFoundException.class, () -> service.updateLibrary(invalidLibrary));
  }
}
