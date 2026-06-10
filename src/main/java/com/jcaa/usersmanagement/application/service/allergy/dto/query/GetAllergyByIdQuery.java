package com.jcaa.usersmanagement.application.service.allergy.dto.query;

import jakarta.validation.constraints.NotBlank;

public record GetAllergyByIdQuery(
    @NotBlank(message = "id must not be blank") String id
) {}
