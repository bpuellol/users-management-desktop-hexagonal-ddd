package com.jcaa.usersmanagement.application.service.dish.dto.command;

import jakarta.validation.constraints.NotBlank;

public record DeleteDishCommand(
    @NotBlank(message = "id must not be blank") String id
) {}
