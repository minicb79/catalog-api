package com.minicdesign.catalog.api.items.controllers;

import com.minicdesign.catalog.api.items.controllers.domain.request.ItemDetailsRequest;
import com.minicdesign.catalog.api.items.controllers.domain.response.ItemDetailsResponse;
import com.minicdesign.catalog.api.items.controllers.usecases.CreateItemUseCase;
import com.minicdesign.catalog.api.items.domain.ItemDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemController {

  private final CreateItemUseCase createItemService;

  @PostMapping("/libraries/{libraryId}/items")
  public ItemDetailsResponse createItem(
      @PathVariable("libraryId") Long libraryId,
      @RequestBody ItemDetailsRequest request) throws Exception {

    ItemDomain domain = createItemService.createItem(ItemDomain.from(request, libraryId));
    return ItemDetailsResponse.from(domain);
  }
}
