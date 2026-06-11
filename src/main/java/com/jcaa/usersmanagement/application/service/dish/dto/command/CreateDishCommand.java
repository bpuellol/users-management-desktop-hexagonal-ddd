package com.jcaa.usersmanagement.application.service.dish.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateDishCommand(
    @NotBlank(message = "id must not be blank")
    String id,

    @NotBlank(message = "name must not be blank")
    @Size(min = 2, message = "name must have at least 2 characters")
    String name,

    @NotBlank(message = "ingredients must not be blank")
    String ingredients
) {}
