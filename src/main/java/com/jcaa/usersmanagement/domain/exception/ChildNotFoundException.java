package com.jcaa.usersmanagement.domain.exception;

public final class ChildNotFoundException extends DomainException {

  private static final String MESSAGE_BY_ID =
      "The child with id '%s' was not found.";
  private static final String MESSAGE_BY_ENROLLMENT =
      "The child with enrollment '%s' was not found.";

  private ChildNotFoundException(final String message) { super(message); }

  public static ChildNotFoundException becauseIdWasNotFound(final String id) {
    return new ChildNotFoundException(String.format(MESSAGE_BY_ID, id));
  }

  public static ChildNotFoundException becauseEnrollmentWasNotFound(final String enrollment) {
    return new ChildNotFoundException(String.format(MESSAGE_BY_ENROLLMENT, enrollment));
  }
}
