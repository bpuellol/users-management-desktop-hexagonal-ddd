package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.dto;

public record UpdateAllergyRequest(
    String id,
    String ingredient,
    String severity,
    String notes) {}
