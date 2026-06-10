package com.jcaa.usersmanagement.domain.exception;

public final class AllergyNotFoundException extends DomainException {

  private static final String MESSAGE_BY_ID =
      "The allergy with id '%s' was not found.";
  private static final String MESSAGE_BY_INGREDIENT =
      "The allergy for ingredient '%s' in child '%s' was not found.";

  private AllergyNotFoundException(final String message) { super(message); }

  public static AllergyNotFoundException becauseIdWasNotFound(final String id) {
    return new AllergyNotFoundException(String.format(MESSAGE_BY_ID, id));
  }

  public static AllergyNotFoundException becauseIngredientWasNotFound(
      final String ingredient, final String childId) {
    return new AllergyNotFoundException(
        String.format(MESSAGE_BY_INGREDIENT, ingredient, childId));
  }
}
