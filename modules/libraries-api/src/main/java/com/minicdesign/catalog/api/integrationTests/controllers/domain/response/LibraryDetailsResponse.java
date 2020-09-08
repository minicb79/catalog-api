package com.minicdesign.catalog.api.integrationTests.controllers.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.minicdesign.catalog.api.integrationTests.domain.LibraryDomain;
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
public class LibraryDetailsResponse {

  private Long id;
  private String name;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String description;

  public static LibraryDetailsResponse from(LibraryDomain domain) {
    return new LibraryDetailsResponse(
        domain.getId(),
        domain.getName(),
        domain.getDescription());
  }

}
