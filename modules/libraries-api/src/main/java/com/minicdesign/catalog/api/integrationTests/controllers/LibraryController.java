package com.minicdesign.catalog.api.integrationTests.controllers;

import javax.validation.Valid;

import com.minicdesign.catalog.api.integrationTests.controllers.domain.request.LibraryDetailsRequest;
import com.minicdesign.catalog.api.integrationTests.controllers.domain.response.LibraryDetailsResponse;
import com.minicdesign.catalog.api.integrationTests.controllers.usecases.CreateLibraryUseCase;
import com.minicdesign.catalog.api.integrationTests.domain.LibraryDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LibraryController {

  private final CreateLibraryUseCase createLibraryService;

  @PostMapping("/libraries")
  @ResponseStatus(HttpStatus.CREATED)
  public @ResponseBody LibraryDetailsResponse createLibrary(@Valid @RequestBody LibraryDetailsRequest request) {
    LibraryDomain domain = createLibraryService.createLibrary(LibraryDomain.from(request));
    return LibraryDetailsResponse.from(domain);
  }
}
