package com.jcaa.usersmanagement.domain.exception;

public final class InvalidChildNameException extends DomainException {

  private InvalidChildNameException(final String message) { super(message); }

  public static InvalidChildNameException becauseValueIsEmpty() {
    return new InvalidChildNameException("ChildName cannot be empty.");
  }

  public static InvalidChildNameException becauseLengthIsTooShort(final int min) {
    return new InvalidChildNameException(
        String.format("ChildName must have at least %d characters.", min));
  }
}
