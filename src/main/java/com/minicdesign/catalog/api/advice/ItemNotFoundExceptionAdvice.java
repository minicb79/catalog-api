package com.minicdesign.catalog.api.advice;

import com.minicdesign.catalog.api.domain.ErrorResponse;
import com.minicdesign.catalog.api.exceptions.ItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ItemNotFoundExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse itemNotFoundExceptionHandler(ItemNotFoundException e) {
        return ErrorResponse.builder()
                .errorCode("NF-404")
                .message(e.getMessage())
                .build();
    }
}
