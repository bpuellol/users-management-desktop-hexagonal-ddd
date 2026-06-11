package com.jcaa.usersmanagement.application.port.in.dish;

import com.jcaa.usersmanagement.application.service.dish.dto.query.GetDishByIdQuery;
import com.jcaa.usersmanagement.domain.model.DishModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface GetDishByIdUseCase {
  DishModel execute(@NotNull @Valid GetDishByIdQuery query);
}
