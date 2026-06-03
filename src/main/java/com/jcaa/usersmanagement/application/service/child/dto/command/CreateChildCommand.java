package com.jcaa.usersmanagement.application.service.child.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateChildCommand(
    @NotBlank(message = "id must not be blank")
    String id,

    @NotBlank(message = "enrollment must not be blank")
    String enrollment,

    @NotBlank(message = "fullName must not be blank")
    @Size(min = 2, message = "fullName must have at least 2 characters")
    String fullName,

    @NotBlank(message = "birthDate must not be blank")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "birthDate must be in format YYYY-MM-DD")
    String birthDate,

    @NotBlank(message = "admissionDate must not be blank")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "admissionDate must be in format YYYY-MM-DD")
    String admissionDate
) {}
