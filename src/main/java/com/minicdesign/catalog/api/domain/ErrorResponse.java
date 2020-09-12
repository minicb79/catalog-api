package com.minicdesign.catalog.api.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@EqualsAndHashCode
@ToString
public class ErrorResponse {

    private final String errorCode;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String longMessage;

}
