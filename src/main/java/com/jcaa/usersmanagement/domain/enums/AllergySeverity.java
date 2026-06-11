package com.jcaa.usersmanagement.domain.enums;

import com.jcaa.usersmanagement.domain.exception.DomainException;

/** Nivel de severidad de una alergia registrada en el comedor infantil. */
public enum AllergySeverity {

  MILD("Leve"),
  MODERATE("Moderada"),
  SEVERE("Severa");

  private final String label;

  AllergySeverity(final String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  public static AllergySeverity fromString(final String value) {
    for (final AllergySeverity severity : values()) {
      if (severity.name().equalsIgnoreCase(value)) {
        return severity;
      }
    }
    throw new DomainException("Invalid allergy severity: " + value) {};
  }
}
