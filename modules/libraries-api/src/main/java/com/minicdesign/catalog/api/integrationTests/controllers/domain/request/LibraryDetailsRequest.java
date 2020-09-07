package com.minicdesign.catalog.api.integrationTests.controllers.domain.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LibraryDetailsRequest {

  @NotBlank
  @Size(min = 2, max = 40)
  private String name;

  @Size(max = 160)
  private String description;

}
