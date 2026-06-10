package com.jcaa.usersmanagement.infrastructure.adapter.persistence.allergy.dto;

public record AllergyPersistenceDto(
    String id,
    String childId,
    String ingredient,
    String severity,
    String notes,
    String createdAt,
    String updatedAt) {}
