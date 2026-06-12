package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.dto;

/**
 * DTO de respuesta — CEA #9:
 * "Mostrar las alergias registradas y los platos que NO pueden ser consumidos por cada niño."
 * Resultado del JOIN entre allergies y dishes por ingrediente.
 */
public record ForbiddenDishResponse(
    String childId,
    String enrollment,
    String childFullName,
    String allergenIngredient,
    String severity,
    String dishId,
    String dishName,
    String dishIngredients) {}
