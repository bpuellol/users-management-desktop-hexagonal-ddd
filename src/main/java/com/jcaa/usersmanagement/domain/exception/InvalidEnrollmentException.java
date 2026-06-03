package com.jcaa.usersmanagement.domain.exception;

public final class InvalidEnrollmentException extends DomainException {

  private InvalidEnrollmentException(final String message) { super(message); }

  public static InvalidEnrollmentException becauseValueIsEmpty() {
    return new InvalidEnrollmentException("Enrollment number cannot be empty.");
  }
}
