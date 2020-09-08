package com.minicdesign.catalog.api.integrationTests.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.minicdesign.catalog.api.integrationTests.controllers.domain.request.LibraryDetailsRequest;
import com.minicdesign.catalog.api.integrationTests.controllers.domain.response.LibraryDetailsResponse;
import com.minicdesign.catalog.api.integrationTests.domain.LibraryDomain;
import com.minicdesign.catalog.api.integrationTests.services.CreateLibraryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LibraryControllerTest {

  @Mock
  private CreateLibraryService createLibraryService;

  private LibraryController controller;

  @BeforeEach
  public void setup() {
    controller = new LibraryController(createLibraryService);
  }

  @Test
  public void givenValidRequest_whenCreateLibrary_thenCreatedResponseReturned() {

    LibraryDetailsRequest request = new LibraryDetailsRequest();
    request.setName("Library Name");;
    request.setDescription("Library Long Description");

    LibraryDomain createdDomain = new LibraryDomain(3L, "Library Name", "Library Long Description");

    when(createLibraryService.createLibrary(any())).thenReturn(createdDomain);

    LibraryDetailsResponse response = controller.createLibrary(request);

    assertNotNull(response);
    assertNotNull(response.getId());
    assertEquals(request.getName(), response.getName());
    assertEquals(request.getDescription(), response.getDescription());
  }

}
