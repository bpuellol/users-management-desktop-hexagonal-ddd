package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.dto;

/**
 * DTO de respuesta — CEA #7:
 * "Consultar los niños con alergias registradas y los ingredientes que deben evitar."
 */
public record ChildAllergyResponse(
    String childId,
    String enrollment,
    String childFullName,
    String ingredient,
    String severity,
    String notes) {}
