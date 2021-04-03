package com.minicdesign.catalog.api.items.controllers.domain.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.minicdesign.catalog.api.items.controllers.domain.request.ItemDetailsRequest;

public class ItemRequestValidator implements
    ConstraintValidator<ItemRequestConstraint, Object> {

  private String message;

  @Override
  public void initialize(ItemRequestConstraint constraintAnnotation) {
    this.message = constraintAnnotation.message();
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {

    if (value.getClass().isAssignableFrom(ItemDetailsRequest.class)) {
      ItemDetailsRequest request = (ItemDetailsRequest) value;

      if (request.getType() == null) {
        return false;
      }

      switch(request.getType()) {
        case BOOK:
          return validateBook(request);
        case MAGAZINE:
          return validateMagazine(request);
        case BOARD_GAME:
          return validateBoardGame(request);
        case RECIPE:
          return validateRecipe(request);
        case URL:
          return validateUrl(request);
        default:
          return false;
      }
    }

    return false;
  }

  private boolean validateBook(ItemDetailsRequest request) {
    if (request.getAuthor() == null || request.getAuthor().isBlank()) {
      message = "Author must not be null or blank.";
      return false;
    }
    if (request.getTitle() == null || request.getTitle().isBlank()) {
      message = "Title must not be null or blank.";
      return false;
    }
    return true;
  }

  private boolean validateMagazine(ItemDetailsRequest request) {
    return false;
  }

  private boolean validateBoardGame(ItemDetailsRequest request) {
    return false;
  }

  private boolean validateRecipe(ItemDetailsRequest request) {
    return false;
  }

  private boolean validateUrl(ItemDetailsRequest request) {
    return false;
  }
}
