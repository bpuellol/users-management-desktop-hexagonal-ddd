package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidChildIdException;
import java.util.Objects;

public record ChildId(String value) {

  public ChildId {
    final String normalized = Objects.requireNonNull(value, "ChildId cannot be null").trim();
    if (normalized.isEmpty()) {
      throw InvalidChildIdException.becauseValueIsEmpty();
    }
    value = normalized;
  }

  @Override
  public String toString() {
    return value;
  }
}
