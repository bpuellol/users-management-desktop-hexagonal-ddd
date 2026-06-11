package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidIngredientException;
import java.util.Objects;

/** Nombre del ingrediente al que el niño es alérgico — identificador de negocio único por niño. */
public record Ingredient(String value) {

  private static final int MINIMUM_LENGTH = 2;

  public Ingredient {
    final String normalized = Objects.requireNonNull(value, "Ingredient cannot be null").trim();
    if (normalized.isEmpty()) {
      throw InvalidIngredientException.becauseValueIsEmpty();
    }
    if (normalized.length() < MINIMUM_LENGTH) {
      throw InvalidIngredientException.becauseLengthIsTooShort(MINIMUM_LENGTH);
    }
    value = normalized;
  }

  @Override
  public String toString() {
    return value;
  }
}
