package com.jcaa.usersmanagement.domain.exception;

public final class InvalidDishIdException extends DomainException {

  private InvalidDishIdException(final String message) { super(message); }

  public static InvalidDishIdException becauseValueIsEmpty() {
    return new InvalidDishIdException("DishId cannot be empty.");
  }
}
