package com.minicdesign.catalog.api.items.controllers.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.minicdesign.catalog.api.items.domain.ItemDomain;
import com.minicdesign.catalog.api.items.domain.ItemType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemDetailsResponse {

    private Long id;
    private ItemType type;
    private String title;
    private String subtitle;
    private String summary;
    private String author;
    private String isbn;
    private String barcode;
    private Integer pages;
    private String meal;
    private String url;
    private Long libraryId;

    public static ItemDetailsResponse from(ItemDomain domain) {
        return new ItemDetailsResponse(
                domain.getId(),
                domain.getType() == null ? null : domain.getType(),
                domain.getTitle(),
                domain.getSubtitle(),
                domain.getSummary(),
                domain.getAuthor(),
                domain.getIsbn(),
                domain.getBarcode(),
                domain.getPages(),
                domain.getMeal(),
                domain.getUrl(),
                domain.getLibraryId());
    }
}
