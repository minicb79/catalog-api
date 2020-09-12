package com.minicdesign.catalog.api.items.controllers.domain.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.minicdesign.catalog.api.items.domain.ItemType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemDetailsRequest {

  @NotBlank
  private ItemType type;

  @NotBlank
  @Size(max = 60)
  private String title;

  @Size(max = 120)
  private String subtitle;

  @Size(max = 1024)
  private String summary;

  @Size(max = 60)
  private String author;

  @Size(max = 20)
  private String isbn;

  @Size(max = 20)
  private String barcode;

  @Min(0)
  private Integer pages;

  private String meal;
}
