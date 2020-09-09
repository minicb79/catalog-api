package com.minicdesign.catalog.api.advice;

import java.util.HashMap;
import java.util.Map;

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
  public Map<String, String> itemNotFoundExceptionHandler(ItemNotFoundException e) {
    Map<String, String> errors = new HashMap<>();
    errors.put("message", e.getMessage());
    return errors;
  }
}
