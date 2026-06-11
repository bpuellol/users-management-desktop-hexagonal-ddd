package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.dto;

public record CreateDishRequest(
    String id,
    String name,
    String ingredients) {}
