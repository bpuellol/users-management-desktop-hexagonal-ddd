package com.jcaa.usersmanagement.domain.enums;

import com.jcaa.usersmanagement.domain.exception.DomainException;

/** Estado de inscripción del niño en la guardería. */
public enum ChildStatus {

  ACTIVE("Activo"),
  INACTIVE("Dado de baja");

  private final String label;

  ChildStatus(final String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  public static ChildStatus fromString(final String value) {
    for (final ChildStatus status : values()) {
      if (status.name().equalsIgnoreCase(value)) {
        return status;
      }
    }
    throw new DomainException("Invalid child status: " + value) {};
  }
}
