package com.jcaa.usersmanagement.application.service.child.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateChildCommand(
    @NotBlank(message = "id must not be blank")
    String id,

    @NotBlank(message = "fullName must not be blank")
    @Size(min = 2, message = "fullName must have at least 2 characters")
    String fullName,

    @NotBlank(message = "birthDate must not be blank")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "birthDate must be in format YYYY-MM-DD")
    String birthDate,

    @NotBlank(message = "admissionDate must not be blank")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "admissionDate must be in format YYYY-MM-DD")
    String admissionDate,

    // Puede ser vacío si el niño sigue activo
    String dischargeDate,

    @NotBlank(message = "status must not be blank")
    String status
) {}
