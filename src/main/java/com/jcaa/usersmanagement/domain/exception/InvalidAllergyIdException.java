package com.jcaa.usersmanagement.domain.exception;

public final class InvalidAllergyIdException extends DomainException {

  private InvalidAllergyIdException(final String message) { super(message); }

  public static InvalidAllergyIdException becauseValueIsEmpty() {
    return new InvalidAllergyIdException("AllergyId cannot be empty.");
  }
}
