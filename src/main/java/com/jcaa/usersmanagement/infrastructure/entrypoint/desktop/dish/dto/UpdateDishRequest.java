package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.dto;

public record UpdateDishRequest(
    String id,
    String name,
    String ingredients) {}
