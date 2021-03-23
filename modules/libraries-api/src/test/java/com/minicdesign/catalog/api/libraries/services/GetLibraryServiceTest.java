package com.minicdesign.catalog.api.libraries.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
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
public class GetLibraryServiceTest {

  @Mock
  LibraryRepositoryAdapter repository;

  GetLibraryService service;

  @BeforeEach
  void setup() {
    service = new GetLibraryService(repository);
  }

  @Test
  public void givenValidLibraryId_whenGetLibrary_thenReturnLibrary() {
    LibraryDomain library = new LibraryDomain();
    library.setId(2L);
    library.setName("Library Name");
    library.setDescription("A Description for the Library.");

    when(repository.getLibrary(anyLong())).thenReturn(library);

    LibraryDomain fetchedLibrary = service.getLibrary(2L);

    assertNotNull(fetchedLibrary);
  }

  @Test
  public void givenInvalidLibraryId_whenGetLibrary_thenItemNotFoundExceptionThrown() {
    when(repository.getLibrary(anyLong())).thenThrow(new ItemNotFoundException());

    assertThrows(ItemNotFoundException.class, () -> service.getLibrary(2L));
  }
}
