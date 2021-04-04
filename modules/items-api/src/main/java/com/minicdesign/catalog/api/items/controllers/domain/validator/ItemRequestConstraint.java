package com.minicdesign.catalog.api.items.controllers.domain.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ItemRequestValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ItemRequestConstraint {

  String message() default "Invalid \"type\". Must be one of \"boardgame\", \"book\", \"magazine\", \"recipe\", or \"url\".";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};

  @Target({ElementType.TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @interface List {
    ItemRequestConstraint[] value();
  }
}
