package com.minicdesign.catalog.api.items.controllers.domain.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@AllArgsConstructor
@Data
public class PagedItemDetailsListResponse {

    private List<ItemDetailsResponse> itemList;
    private int page;
    private long count;
    private long pageCount;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pageable previousPage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pageable nextPage;
}
