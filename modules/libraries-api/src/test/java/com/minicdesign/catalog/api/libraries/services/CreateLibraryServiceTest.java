package com.minicdesign.catalog.api.libraries.services;

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
}
