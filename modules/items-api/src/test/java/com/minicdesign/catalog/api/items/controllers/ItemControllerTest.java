package com.minicdesign.catalog.api.items.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.minicdesign.catalog.api.items.controllers.domain.request.ItemDetailsRequest;
import com.minicdesign.catalog.api.items.controllers.domain.response.ItemDetailsResponse;
import com.minicdesign.catalog.api.items.controllers.domain.response.PagedItemDetailsListResponse;
import com.minicdesign.catalog.api.items.controllers.usecases.CreateItemUseCase;
import com.minicdesign.catalog.api.items.controllers.usecases.DeleteItemUseCase;
import com.minicdesign.catalog.api.items.controllers.usecases.GetItemListUseCase;
import com.minicdesign.catalog.api.items.controllers.usecases.GetItemUseCase;
import com.minicdesign.catalog.api.items.controllers.usecases.UpdateItemUseCase;
import com.minicdesign.catalog.api.items.domain.ItemDomain;
import com.minicdesign.catalog.api.items.domain.ItemType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ItemControllerTest {

  private MockMvc mockMvc;

  @Mock
  private CreateItemUseCase createItemUseCase;

  @Mock
  private GetItemListUseCase getItemListUseCase;

  @Mock
  private GetItemUseCase getItemUseCase;

  @Mock
  private UpdateItemUseCase updateItemUseCase;

  @Mock
  private DeleteItemUseCase deleteItemUseCase;

  private ItemController controller;

  @BeforeEach
  public void setup() {
    controller = new ItemController(createItemUseCase, getItemListUseCase, getItemUseCase, updateItemUseCase, deleteItemUseCase);
    this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  public void givenValidRequest_whenCreateItem_thenCreatedResponseReturned() {

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

  @Test
  public void givenValidRequest_whenGetItemList_thenPageOfDataReturned() {
    List<ItemDomain> itemDomainList = new ArrayList<>();
    itemDomainList.add(new ItemDomain(1L, ItemType.BOOK, "Book title 1", "subtitle", "Summary", "Author", "12345678901234", "B-1234", 20, "ALL", "www.recipes.com", 1L));
    itemDomainList.add(new ItemDomain(2L, ItemType.BOOK, "Book title 2", "subtitle", "Summary", "Author", "12345678901234", "B-1234", 20, "ALL", "www.recipes.com", 1L));
    ItemDomain itemDomain = new ItemDomain(3L, ItemType.BOOK, "Book title 3", "subtitle", "Summary", "Author", "12345678901234", "B-1234", 20, "ALL", "www.recipes.com", 1L);
    itemDomainList.add(itemDomain);
    itemDomainList.add(new ItemDomain(4L, ItemType.BOOK, "Book title 4", "subtitle", "Summary", "Author", "12345678901234", "B-1234", 20, "ALL", "www.recipes.com", 1L));
    itemDomainList.add(new ItemDomain(5L, ItemType.BOOK, "Book title 5", "subtitle", "Summary", "Author", "12345678901234", "B-1234", 20, "ALL", "www.recipes.com", 1L));
    itemDomainList.add(new ItemDomain(6L, ItemType.BOOK, "Book title 6", "subtitle", "Summary", "Author", "12345678901234", "B-1234", 20, "ALL", "www.recipes.com", 1L));
    itemDomainList.add(new ItemDomain(7L, ItemType.BOOK, "Book title 7", "subtitle", "Summary", "Author", "12345678901234", "B-1234", 20, "ALL", "www.recipes.com", 1L));
    itemDomainList.add(new ItemDomain(8L, ItemType.BOOK, "Book title 8", "subtitle", "Summary", "Author", "12345678901234", "B-1234", 20, "ALL", "www.recipes.com", 1L));
    itemDomainList.add(new ItemDomain(9L, ItemType.BOOK, "Book title 9", "subtitle", "Summary", "Author", "12345678901234", "B-1234", 20, "ALL", "www.recipes.com", 1L));
    itemDomainList.add(new ItemDomain(10L, ItemType.BOOK, "Book title 10", "subtitle", "Summary", "Author", "12345678901234", "B-1234", 20, "ALL", "www.recipes.com", 1L));

    Page<ItemDomain> domainPage = new PageImpl<>(itemDomainList, PageRequest.of(0, 10), 12);

    when(getItemListUseCase.getItemList(1L, 0, 10)).thenReturn(domainPage);

    PagedItemDetailsListResponse itemList = controller.getItemList(1L, 0, 10);

    assertNotNull(itemList);
    assertEquals(12, itemList.getCount());
    assertEquals(2, itemList.getPageCount());
    assertEquals(0, itemList.getPage());
    assertEquals(10, itemList.getItemList().size());

    assertEquals(itemDomain.getAuthor(), itemList.getItemList().get(2).getAuthor());
  }

  @Test
  public void givenValidRequest_whenGetItem_thenLibraryReturned() {

    ItemDomain itemDomain = new ItemDomain(1L, ItemType.BOOK, "Book title 1", "subtitle", "Summary", "Author", "12345678901234", "B-1234", 20, "ALL", "www.recipes.com", 1L);

    when(getItemUseCase.getItem(anyLong())).thenReturn(itemDomain);

    ItemDetailsResponse response = controller.getItem(1L);

    assertNotNull(response);
    assertEquals(1L, response.getId());
    assertEquals("Book title 1", response.getTitle());
    assertEquals("subtitle", response.getSubtitle());
    assertEquals("12345678901234", response.getIsbn());
  }

  @Test
  public void givenValidRequest_whenUpdateItem_thenUpdatedItemReturned() {
    ItemDetailsRequest itemUpdateRequest = new ItemDetailsRequest();
    itemUpdateRequest.setTitle("Narnia");

    ItemDomain updatedItemDomain = new ItemDomain(1L, ItemType.BOOK, "Narnia", "subtitle", "Summary", "Author", "12345678901234", "B-1234", 20, "ALL", "www.recipes.com", 1L);

    when(updateItemUseCase.updateItem(any())).thenReturn(updatedItemDomain);

    ItemDetailsResponse response = controller.updateItem(1L, itemUpdateRequest);

    assertNotNull(response);
    assertEquals(1L, response.getId());
    assertEquals("Narnia", response.getTitle());
    assertEquals("subtitle", response.getSubtitle());
  }
}
