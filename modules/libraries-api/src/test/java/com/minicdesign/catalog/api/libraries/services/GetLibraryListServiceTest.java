package com.minicdesign.catalog.api.integrationTests.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.minicdesign.catalog.api.integrationTests.domain.LibraryDomain;
import com.minicdesign.catalog.api.integrationTests.repositories.LibraryRepositoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@ExtendWith(MockitoExtension.class)
public class GetLibraryListServiceTest {

  @Mock
  private LibraryRepositoryAdapter repository;

  private GetLibraryListService service;

  @BeforeEach
  void setup() {
    service = new GetLibraryListService(repository);
  }

  @Test
  void givenRequestForFirstPageOfSix_whenGetLibraryList_thenPageOfResultsReturned() {

    List<LibraryDomain> domainList = new ArrayList<>();
    domainList.add(new LibraryDomain(1L, "Library Domain 1", "Library Description 1"));
    domainList.add(new LibraryDomain(2L, "Library Domain 2", "Library Description 2"));

    Page<LibraryDomain> mockPage = new PageImpl<>(domainList, PageRequest.of(0, 2, Sort.unsorted()), 15);

    when(repository.getLibrariesForPage(anyInt(), anyInt())).thenReturn(mockPage);

    Page<LibraryDomain> page = service.getLibraryList(0, 2);

    assertNotNull(page);
    assertEquals(2, page.getContent().size());
  }

}
