package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dish.dto;

public record DishPersistenceDto(
    String id,
    String name,
    String ingredients,
    String createdAt,
    String updatedAt) {}
