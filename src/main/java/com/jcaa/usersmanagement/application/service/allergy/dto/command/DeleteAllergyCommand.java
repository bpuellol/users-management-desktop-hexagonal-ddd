package com.jcaa.usersmanagement.application.service.allergy.dto.command;

import jakarta.validation.constraints.NotBlank;

public record DeleteAllergyCommand(
    @NotBlank(message = "id must not be blank") String id
) {}
