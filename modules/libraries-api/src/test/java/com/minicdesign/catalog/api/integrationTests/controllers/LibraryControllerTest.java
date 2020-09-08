package com.minicdesign.catalog.api.integrationTests.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.minicdesign.catalog.api.integrationTests.controllers.domain.request.LibraryDetailsRequest;
import com.minicdesign.catalog.api.integrationTests.controllers.domain.response.LibraryDetailsResponse;
import com.minicdesign.catalog.api.integrationTests.controllers.domain.response.PagedLibraryDetailsListResponse;
import com.minicdesign.catalog.api.integrationTests.controllers.usecases.GetLibraryCountUseCase;
import com.minicdesign.catalog.api.integrationTests.controllers.usecases.GetLibraryListUseCase;
import com.minicdesign.catalog.api.integrationTests.domain.LibraryDomain;
import com.minicdesign.catalog.api.integrationTests.services.CreateLibraryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MockitoExtension.class)
public class LibraryControllerTest {

  @Mock
  private CreateLibraryService createLibraryService;

  @Mock
  private GetLibraryListUseCase getLibraryListService;

  @Mock
  private GetLibraryCountUseCase getLibraryCountUseCase;

  private LibraryController controller;

  @BeforeEach
  public void setup() {
    controller = new LibraryController(createLibraryService, getLibraryListService, getLibraryCountUseCase);
  }

  @Test
  public void givenValidRequest_whenCreateLibrary_thenCreatedResponseReturned() {

    LibraryDetailsRequest request = new LibraryDetailsRequest();
    request.setName("Library Name");

    request.setDescription("Library Long Description");

    LibraryDomain createdDomain = new LibraryDomain(3L, "Library Name", "Library Long Description");

    when(createLibraryService.createLibrary(any())).thenReturn(createdDomain);

    LibraryDetailsResponse response = controller.createLibrary(request);

    assertNotNull(response);
    assertNotNull(response.getId());
    assertEquals(request.getName(), response.getName());
    assertEquals(request.getDescription(), response.getDescription());
  }

  @Test
  public void givenValidRequest_whenGetLibraryList_thenPageOfDataReturned() {

    List<LibraryDomain> libraryDomainList = new ArrayList<>();
    libraryDomainList.add(new LibraryDomain(1L, "Library 1", "Description 1"));
    libraryDomainList.add(new LibraryDomain(2L, "Library 2", ""));
    libraryDomainList.add(new LibraryDomain(3L, "Library 3", null));
    libraryDomainList.add(new LibraryDomain(4L, "Library 4", "Description 4"));
    libraryDomainList.add(new LibraryDomain(5L, "Library 5", null));
    libraryDomainList.add(new LibraryDomain(6L, "Library 6", "Quite a long description for this test."));

    Page<LibraryDomain> domainPage = new PageImpl(libraryDomainList, PageRequest.of(0, 6), 15);

    when(getLibraryListService.getLibraryList(anyInt(), anyInt())).thenReturn(domainPage);

    PagedLibraryDetailsListResponse libraryList = controller.getLibraryList(0, 6);

    assertNotNull(libraryList);
    assertEquals(15, libraryList.getCount());
    assertEquals(3, libraryList.getPageCount());
    assertEquals(0, libraryList.getPage());
  }

}
