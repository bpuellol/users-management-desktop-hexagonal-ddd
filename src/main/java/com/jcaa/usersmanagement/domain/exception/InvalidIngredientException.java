package com.jcaa.usersmanagement.domain.exception;

public final class InvalidIngredientException extends DomainException {

  private InvalidIngredientException(final String message) { super(message); }

  public static InvalidIngredientException becauseValueIsEmpty() {
    return new InvalidIngredientException("Ingredient cannot be empty.");
  }

  public static InvalidIngredientException becauseLengthIsTooShort(final int min) {
    return new InvalidIngredientException(
        String.format("Ingredient must have at least %d characters.", min));
  }
}
