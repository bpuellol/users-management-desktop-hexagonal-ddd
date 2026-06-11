package com.jcaa.usersmanagement.application.service.dish.dto.query;

import jakarta.validation.constraints.NotBlank;

public record GetDishByIdQuery(
    @NotBlank(message = "id must not be blank") String id
) {}
