package com.jcaa.usersmanagement.application.service.allergy.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateAllergyCommand(
    @NotBlank(message = "id must not be blank")
    String id,

    @NotBlank(message = "ingredient must not be blank")
    @Size(min = 2, message = "ingredient must have at least 2 characters")
    String ingredient,

    @NotBlank(message = "severity must not be blank")
    @Pattern(regexp = "MILD|MODERATE|SEVERE", message = "severity must be MILD, MODERATE or SEVERE")
    String severity,

    // Las notas son opcionales
    String notes
) {}
