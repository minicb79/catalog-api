package com.minicdesign.catalog.api.items.domain;

import com.minicdesign.catalog.api.items.controllers.domain.request.ItemDetailsRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDomain {

  private Long id;
  private ItemType type = ItemType.ALL;
  private String title;
  private String subtitle;
  private String summary;
  private String author;
  private String isbn;
  private String barcode;
  private Integer pages;
  private String meal;
  private Long libraryId;

  public static ItemDomain from(ItemDetailsRequest request, Long libraryId) {
    ItemDomain domain = new ItemDomain();
    domain.setType(request.getType());
    domain.setTitle(request.getTitle());
    domain.setSubtitle(request.getSubtitle());
    domain.setAuthor(request.getAuthor());
    domain.setIsbn(request.getIsbn());
    domain.setBarcode(request.getBarcode());
    domain.setPages(request.getPages());
    domain.setMeal(request.getMeal());
    domain.setLibraryId(libraryId);
    return domain;
  }
}
