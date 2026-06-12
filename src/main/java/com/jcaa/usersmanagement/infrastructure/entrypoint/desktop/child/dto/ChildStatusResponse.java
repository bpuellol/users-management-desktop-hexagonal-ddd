package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.dto;

/**
 * DTO de respuesta para las consultas del CEA sobre estado de niños.
 * CEA #1: Listar todos los niños con su estado (activo/baja).
 * CEA #2: Niños dados de baja y fecha de retiro.
 */
public record ChildStatusResponse(
    String id,
    String enrollment,
    String fullName,
    String birthDate,
    String admissionDate,
    String dischargeDate,
    String status) {}
