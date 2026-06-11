package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidDishIdException;
import java.util.Objects;

public record DishId(String value) {

  public DishId {
    final String normalized = Objects.requireNonNull(value, "DishId cannot be null").trim();
    if (normalized.isEmpty()) {
      throw InvalidDishIdException.becauseValueIsEmpty();
    }
    value = normalized;
  }

  @Override
  public String toString() {
    return value;
  }
}
