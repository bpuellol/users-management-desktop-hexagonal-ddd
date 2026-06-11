package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidDishNameException;
import java.util.Objects;

public record DishName(String value) {

  private static final int MINIMUM_LENGTH = 2;

  public DishName {
    final String normalized = Objects.requireNonNull(value, "DishName cannot be null").trim();
    if (normalized.isEmpty()) {
      throw InvalidDishNameException.becauseValueIsEmpty();
    }
    if (normalized.length() < MINIMUM_LENGTH) {
      throw InvalidDishNameException.becauseLengthIsTooShort(MINIMUM_LENGTH);
    }
    value = normalized;
  }

  @Override
  public String toString() {
    return value;
  }
}
