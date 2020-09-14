package com.minicdesign.catalog.api.items.controllers.domain.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.minicdesign.catalog.api.items.domain.ItemType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemDetailsRequest {

    @NotNull(message = "must be present [boardgame, book, magazine, recipe, url]")
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

    @Min(value = 1, message = "must be blank or greater than or equal to 1")
    private Integer pages;

    private String meal;

    @Size(max = 255)
    private String url;
}
