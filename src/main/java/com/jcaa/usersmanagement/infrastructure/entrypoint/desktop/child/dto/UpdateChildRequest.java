package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.dto;

public record UpdateChildRequest(
    String id,
    String fullName,
    String birthDate,
    String admissionDate,
    String dischargeDate,
    String status) {}
