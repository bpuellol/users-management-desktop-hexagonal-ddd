package com.jcaa.usersmanagement.domain.exception;

public final class InvalidDishNameException extends DomainException {

  private InvalidDishNameException(final String message) { super(message); }

  public static InvalidDishNameException becauseValueIsEmpty() {
    return new InvalidDishNameException("DishName cannot be empty.");
  }

  public static InvalidDishNameException becauseLengthIsTooShort(final int min) {
    return new InvalidDishNameException(
        String.format("DishName must have at least %d characters.", min));
  }
}
