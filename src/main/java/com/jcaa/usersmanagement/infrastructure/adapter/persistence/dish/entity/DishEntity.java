package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dish.entity;

public record DishEntity(
    String id,
    String name,
    String ingredients,
    String createdAt,
    String updatedAt) {}
