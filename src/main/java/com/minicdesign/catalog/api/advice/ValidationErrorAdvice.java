package com.minicdesign.catalog.api.advice;

import java.util.HashMap;
import java.util.Map;

import com.minicdesign.catalog.api.domain.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ValidationErrorAdvice {

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse validationExceptionHandler(MethodArgumentNotValidException e) {

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String name;
            if (error instanceof FieldError) {
                name = ((FieldError) error).getField();
            } else {
                name = error.getObjectName();
            }
            String errorMessage = error.getDefaultMessage();
            errors.put(name, errorMessage);
        });

        return ValidationErrorResponse.builder()
                .errorCode("VE-001")
                .message("Validation errors occurred.")
                .validationErrors(errors)
                .build();
    }
}
