package com.minicdesign.catalog.api.libraries.controllers.domain.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@AllArgsConstructor
@Data
public class PagedLibraryDetailsListResponse {

    private List<LibraryDetailsResponse> libraryList;
    private int page;
    private long count;
    private long pageCount;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pageable previousPage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pageable nextPage;
}
