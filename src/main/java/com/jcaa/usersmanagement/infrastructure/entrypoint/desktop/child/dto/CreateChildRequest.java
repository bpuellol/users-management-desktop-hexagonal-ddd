package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.dto;

public record CreateChildRequest(
    String id,
    String enrollment,
    String fullName,
    String birthDate,
    String admissionDate) {}
