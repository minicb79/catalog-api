package com.minicdesign.catalog.api.integrationTests.controllers;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import com.minicdesign.catalog.api.integrationTests.controllers.domain.request.LibraryDetailsRequest;
import com.minicdesign.catalog.api.integrationTests.controllers.domain.response.LibraryDetailsResponse;
import com.minicdesign.catalog.api.integrationTests.controllers.domain.response.PagedLibraryDetailsListResponse;
import com.minicdesign.catalog.api.integrationTests.controllers.usecases.CreateLibraryUseCase;
import com.minicdesign.catalog.api.integrationTests.controllers.usecases.GetLibraryCountUseCase;
import com.minicdesign.catalog.api.integrationTests.controllers.usecases.GetLibraryListUseCase;
import com.minicdesign.catalog.api.integrationTests.controllers.usecases.GetLibraryUseCase;
import com.minicdesign.catalog.api.integrationTests.domain.LibraryDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LibraryController {

  private final CreateLibraryUseCase createLibraryService;
  private final GetLibraryListUseCase getLibraryListService;
  private final GetLibraryUseCase getLibraryService;
  private final GetLibraryCountUseCase libraryCountService;

  @PostMapping("/libraries")
  @ResponseStatus(HttpStatus.CREATED)
  public @ResponseBody
  LibraryDetailsResponse createLibrary(@Valid @RequestBody LibraryDetailsRequest request) {
    LibraryDomain domain = createLibraryService.createLibrary(LibraryDomain.from(request));
    return LibraryDetailsResponse.from(domain);
  }

  @GetMapping("/libraries")
  @ResponseStatus(HttpStatus.OK)
  public @ResponseBody
  PagedLibraryDetailsListResponse getLibraryList(
      @RequestParam(required = false, defaultValue = "0") Integer page,
      @RequestParam(required = false, defaultValue = "6") Integer size) {

    Page<LibraryDomain> libraryDomainPage = getLibraryListService.getLibraryList(page, size);

    List<LibraryDetailsResponse> libraryDomainList = libraryDomainPage.stream()
        .map(LibraryDetailsResponse::from).collect(Collectors.toList());

    return new PagedLibraryDetailsListResponse(
        libraryDomainList,
        libraryDomainPage.getNumber(),
        libraryDomainPage.getTotalElements(),
        libraryDomainPage.getTotalPages(),
        libraryDomainPage.previousOrFirstPageable(),
        libraryDomainPage.nextOrLastPageable());
  }

  @GetMapping("/libraries/{id}")
  @ResponseStatus(HttpStatus.OK)
  public @ResponseBody LibraryDetailsResponse getLibrary(@PathVariable("id") Long id) {
    LibraryDomain domain = getLibraryService.getLibrary(id);
    return LibraryDetailsResponse.from(domain);
  }
}
