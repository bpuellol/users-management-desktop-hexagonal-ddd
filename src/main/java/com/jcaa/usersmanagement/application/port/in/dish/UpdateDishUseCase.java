package com.jcaa.usersmanagement.application.port.in.dish;

import com.jcaa.usersmanagement.application.service.dish.dto.command.UpdateDishCommand;
import com.jcaa.usersmanagement.domain.model.DishModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface UpdateDishUseCase {
  DishModel execute(@NotNull @Valid UpdateDishCommand command);
}
