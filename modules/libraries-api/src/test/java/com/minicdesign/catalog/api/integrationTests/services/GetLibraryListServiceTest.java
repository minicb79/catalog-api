package com.minicdesign.catalog.api.integrationTests.services;

import com.minicdesign.catalog.api.integrationTests.repositories.LibraryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetLibraryListServiceTest {

  @Mock
  private LibraryRepository repository;

  private GetLibraryListService service;

  @BeforeEach
  void setup() {
    service = new GetLibraryListService(repository);
  }

}
