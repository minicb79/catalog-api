package com.minicdesign.catalog.api.items.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDomain {

  private Long id;
  private String title;
  private String subtitle;
  private String summary;
  private String author;
  private String isbn;
  private String barcode;
  private Integer pages;
  private Long libraryId;
}
