package com.jcaa.usersmanagement.domain.exception;

public final class AllergyAlreadyExistsException extends DomainException {

  private static final String MESSAGE =
      "An allergy for ingredient '%s' already exists for child '%s'.";

  private AllergyAlreadyExistsException(final String message) { super(message); }

  public static AllergyAlreadyExistsException becauseIngredientAlreadyExists(
      final String ingredient, final String childId) {
    return new AllergyAlreadyExistsException(String.format(MESSAGE, ingredient, childId));
  }
}
