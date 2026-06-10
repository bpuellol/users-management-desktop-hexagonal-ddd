package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidAllergyIdException;
import java.util.Objects;

public record AllergyId(String value) {

  public AllergyId {
    final String normalized = Objects.requireNonNull(value, "AllergyId cannot be null").trim();
    if (normalized.isEmpty()) {
      throw InvalidAllergyIdException.becauseValueIsEmpty();
    }
    value = normalized;
  }

  @Override
  public String toString() {
    return value;
  }
}
