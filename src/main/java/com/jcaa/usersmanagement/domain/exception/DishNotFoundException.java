package com.jcaa.usersmanagement.domain.exception;

public final class DishNotFoundException extends DomainException {

  private static final String MESSAGE_BY_ID = "The dish with id '%s' was not found.";
  private static final String MESSAGE_BY_NAME = "The dish with name '%s' was not found.";

  private DishNotFoundException(final String message) { super(message); }

  public static DishNotFoundException becauseIdWasNotFound(final String id) {
    return new DishNotFoundException(String.format(MESSAGE_BY_ID, id));
  }

  public static DishNotFoundException becauseNameWasNotFound(final String name) {
    return new DishNotFoundException(String.format(MESSAGE_BY_NAME, name));
  }
}
