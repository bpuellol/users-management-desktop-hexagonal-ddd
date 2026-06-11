package com.jcaa.usersmanagement.infrastructure.adapter.persistence.allergy.entity;

public record AllergyEntity(
    String id,
    String childId,
    String ingredient,
    String severity,
    String notes,
    String createdAt,
    String updatedAt) {}
