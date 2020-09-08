package com.minicdesign.catalog.api.integrationTests.controllers.domain.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PagedLibraryDetailsListResponse {

  private List<LibraryDetailsResponse> libraryList;
  private int page;
  private long count;
  private long pageCount;
}
