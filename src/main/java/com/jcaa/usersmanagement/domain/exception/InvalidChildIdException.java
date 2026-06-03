package com.jcaa.usersmanagement.domain.exception;

public final class InvalidChildIdException extends DomainException {

  private InvalidChildIdException(final String message) { super(message); }

  public static InvalidChildIdException becauseValueIsEmpty() {
    return new InvalidChildIdException("ChildId cannot be empty.");
  }
}
