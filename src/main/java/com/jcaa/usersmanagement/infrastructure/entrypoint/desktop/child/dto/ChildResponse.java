package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.dto;

public record ChildResponse(
    String id,
    String enrollment,
    String fullName,
    String birthDate,
    String admissionDate,
    String dischargeDate,
    String status) {}
