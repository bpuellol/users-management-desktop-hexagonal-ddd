package com.jcaa.usersmanagement.domain.exception;

public final class DishAlreadyExistsException extends DomainException {

  private static final String MESSAGE = "A dish with name '%s' already exists.";

  private DishAlreadyExistsException(final String message) { super(message); }

  public static DishAlreadyExistsException becauseNameAlreadyExists(final String name) {
    return new DishAlreadyExistsException(String.format(MESSAGE, name));
  }
}
