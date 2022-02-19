package com.minicdesign.catalog.api.libraries.controllers.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.minicdesign.catalog.api.libraries.domain.LibraryDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Data
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
