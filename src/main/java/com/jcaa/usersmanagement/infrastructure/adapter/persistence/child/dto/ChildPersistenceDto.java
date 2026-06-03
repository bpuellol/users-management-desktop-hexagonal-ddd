package com.jcaa.usersmanagement.infrastructure.adapter.persistence.child.dto;

public record ChildPersistenceDto(
    String id,
    String enrollment,
    String fullName,
    String birthDate,
    String admissionDate,
    String dischargeDate,
    String status,
    String createdAt,
    String updatedAt) {}
