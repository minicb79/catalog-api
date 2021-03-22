package com.minicdesign.catalog.api.libraries.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.minicdesign.catalog.api.libraries.repositories.LibraryRepositoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetLibraryCountServiceTest {

  @Mock
  LibraryRepositoryAdapter repository;

  GetLibraryCountService service;

  @BeforeEach
  void setup() {
    service = new GetLibraryCountService(repository);
  }

  @Test
  void givenValidLibrary_whenGetCount_thenCountValidReturned() {
    when(repository.getCount()).thenReturn(2L);

    Long count = service.getCount();

    assertEquals(2, count);
  }
}
