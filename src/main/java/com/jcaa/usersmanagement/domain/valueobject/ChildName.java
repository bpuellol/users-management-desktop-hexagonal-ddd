package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidChildNameException;
import java.util.Objects;

public record ChildName(String value) {

  private static final int MINIMUM_LENGTH = 2;

  public ChildName {
    final String normalized = Objects.requireNonNull(value, "ChildName cannot be null").trim();
    if (normalized.isEmpty()) {
      throw InvalidChildNameException.becauseValueIsEmpty();
    }
    if (normalized.length() < MINIMUM_LENGTH) {
      throw InvalidChildNameException.becauseLengthIsTooShort(MINIMUM_LENGTH);
    }
    value = normalized;
  }

  @Override
  public String toString() {
    return value;
  }
}
