package com.jcaa.usersmanagement.domain.exception;

public final class ChildAlreadyExistsException extends DomainException {

  private static final String MESSAGE =
      "A child with enrollment '%s' already exists.";

  private ChildAlreadyExistsException(final String message) { super(message); }

  public static ChildAlreadyExistsException becauseEnrollmentAlreadyExists(final String enrollment) {
    return new ChildAlreadyExistsException(String.format(MESSAGE, enrollment));
  }
}
