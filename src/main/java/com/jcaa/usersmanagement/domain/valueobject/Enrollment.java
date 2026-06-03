package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidEnrollmentException;
import java.util.Objects;

/** Número de matrícula del niño en la guardería — identificador de negocio único. */
public record Enrollment(String value) {

  public Enrollment {
    final String normalized = Objects.requireNonNull(value, "Enrollment cannot be null").trim();
    if (normalized.isEmpty()) {
      throw InvalidEnrollmentException.becauseValueIsEmpty();
    }
    value = normalized;
  }

  @Override
  public String toString() {
    return value;
  }
}
