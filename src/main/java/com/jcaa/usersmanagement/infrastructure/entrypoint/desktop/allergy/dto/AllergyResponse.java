package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.dto;

public record AllergyResponse(
    String id,
    String childId,
    String ingredient,
    String severity,
    String notes) {}
