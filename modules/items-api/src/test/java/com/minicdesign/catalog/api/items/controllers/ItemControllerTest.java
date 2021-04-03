package com.minicdesign.catalog.api.items.controllers;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.minicdesign.catalog.api.items.controllers.domain.request.ItemDetailsRequest;
import com.minicdesign.catalog.api.items.controllers.domain.response.ItemDetailsResponse;
import com.minicdesign.catalog.api.items.controllers.usecases.CreateItemUseCase;
import com.minicdesign.catalog.api.items.domain.ItemDomain;
import com.minicdesign.catalog.api.items.domain.ItemType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class ItemControllerTest {

  private MockMvc mockMvc;

  @Mock
  private CreateItemUseCase createItemUseCase;

  private ItemController controller;

  @BeforeEach
  public void setup() {
    controller = new ItemController(createItemUseCase, null, null, null, null);
    this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  public void givenValidRequest_whenCreateItem_theCreatedResponseReturned() {

    ItemDetailsRequest request = new ItemDetailsRequest();
    request.setType(ItemType.BOOK);
    request.setTitle("Book title");
    request.setAuthor("Author");
    request.setIsbn("1234567891234");

    ItemDomain createdDomain = new ItemDomain(3L, ItemType.BOOK, "Book title", "subtitle", "Summary", "Author", "12345678901234", "B-1234", 20, "ALL", "www.recipes.com", 1L);

    when(createItemUseCase.createItem(any())).thenReturn(createdDomain);

    ItemDetailsResponse response = controller.createItem(1L, request);

    assertNotNull(response);
    assertNotNull(response.getId());
    assertEquals(3L, response.getId());
    assertEquals("Book title", response.getTitle());
    assertEquals("subtitle", response.getSubtitle());
    assertEquals("Summary", response.getSummary());
    assertEquals("Author", response.getAuthor());
    assertEquals("12345678901234", response.getIsbn());
    assertEquals("B-1234", response.getBarcode());
    assertEquals(20, response.getPages());
    assertEquals("ALL", response.getMeal());
    assertEquals("www.recipes.com", response.getUrl());
  }


}
