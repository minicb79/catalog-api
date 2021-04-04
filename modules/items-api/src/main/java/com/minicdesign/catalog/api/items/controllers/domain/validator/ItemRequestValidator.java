package com.minicdesign.catalog.api.items.controllers.domain.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.minicdesign.catalog.api.items.controllers.domain.request.ItemDetailsRequest;

public class ItemRequestValidator implements
    ConstraintValidator<ItemRequestConstraint, Object> {

  private static final String MISSING_AUTHOR_MSG = "Author must not be null or blank.";
  private static final String MISSING_TITLE_MSG = "Title must not be null or blank.";
  private static final String MISSING_URL_MSG = "URL must not be null or blank.";

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
      message = MISSING_AUTHOR_MSG;
      return false;
    }
    if (request.getTitle() == null || request.getTitle().isBlank()) {
      message = MISSING_TITLE_MSG;
      return false;
    }
    return true;
  }

  private boolean validateMagazine(ItemDetailsRequest request) {
    if (request.getTitle() == null || request.getTitle().isBlank()) {
      message = MISSING_TITLE_MSG;
      return false;
    }
    return true;
  }

  private boolean validateBoardGame(ItemDetailsRequest request) {
    if (request.getTitle() == null || request.getTitle().isBlank()) {
      message = MISSING_TITLE_MSG;
      return false;
    }
    return true;
  }

  private boolean validateRecipe(ItemDetailsRequest request) {
    if (request.getTitle() == null || request.getTitle().isBlank()) {
      message = MISSING_TITLE_MSG;
      return false;
    }
    return true;
  }

  private boolean validateUrl(ItemDetailsRequest request) {
    if (request.getUrl() == null || request.getUrl().isBlank()) {
      message = MISSING_URL_MSG;
      return false;
    }

    if (request.getTitle() == null || request.getTitle().isBlank()) {
      message = MISSING_TITLE_MSG;
      return false;
    }
    return true;
  }
}
