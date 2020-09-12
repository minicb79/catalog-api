package com.minicdesign.catalog.api.domain;


import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ValidationErrorResponse extends ErrorResponse {

    private final Map<String, String> validationErrors;

}
