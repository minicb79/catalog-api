package com.minicdesign.catalog.api.items.controllers;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import com.minicdesign.catalog.api.items.controllers.domain.request.ItemDetailsRequest;
import com.minicdesign.catalog.api.items.controllers.domain.response.ItemDetailsResponse;
import com.minicdesign.catalog.api.items.controllers.domain.response.PagedItemDetailsListResponse;
import com.minicdesign.catalog.api.items.controllers.usecases.CreateItemUseCase;
import com.minicdesign.catalog.api.items.controllers.usecases.DeleteItemUseCase;
import com.minicdesign.catalog.api.items.controllers.usecases.GetItemListUseCase;
import com.minicdesign.catalog.api.items.controllers.usecases.GetItemUseCase;
import com.minicdesign.catalog.api.items.controllers.usecases.UpdateItemUseCase;
import com.minicdesign.catalog.api.items.domain.ItemDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final CreateItemUseCase createItemService;
    private final GetItemListUseCase getItemListService;
    private final GetItemUseCase getItemUseCase;
    private final UpdateItemUseCase updateItemUseCase;
    private final DeleteItemUseCase deleteItemUseCase;

    @PostMapping("/libraries/{libraryId}/items")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDetailsResponse createItem(
            @PathVariable("libraryId") Long libraryId,
            @RequestBody @Valid ItemDetailsRequest request) {

        ItemDomain domain = createItemService.createItem(ItemDomain.from(request, libraryId));
        return ItemDetailsResponse.from(domain);
    }

    @GetMapping("/libraries/{libraryId}/items")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    PagedItemDetailsListResponse getItemList(
            @PathVariable("libraryId") Long libraryId,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "6") Integer size) {

        Page<ItemDomain> itemDomainPage = getItemListService.getItemList(libraryId, page, size);

        List<ItemDetailsResponse> itemDomainList = itemDomainPage.stream()
                .map(ItemDetailsResponse::from).collect(Collectors.toList());

        return new PagedItemDetailsListResponse(
                itemDomainList,
                itemDomainPage.getNumber(),
                itemDomainPage.getTotalElements(),
                itemDomainPage.getTotalPages(),
                itemDomainPage.previousOrFirstPageable(),
                itemDomainPage.nextOrLastPageable());
    }

    @GetMapping("/items/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ItemDetailsResponse getItem(@PathVariable("id") Long id) {
        ItemDomain domain = getItemUseCase.getItem(id);
        return ItemDetailsResponse.from(domain);
    }

    @PutMapping("/items/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateItem(@PathVariable("id") Long id, @RequestBody @Valid ItemDetailsRequest request) {
        ItemDomain domain = ItemDomain.from(request);
        domain.setId(id);
        updateItemUseCase.updateItem(domain);
    }

    @DeleteMapping("/items/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteItem(@PathVariable("id") Long id) {
        deleteItemUseCase.deleteItem(id);
    }
}
