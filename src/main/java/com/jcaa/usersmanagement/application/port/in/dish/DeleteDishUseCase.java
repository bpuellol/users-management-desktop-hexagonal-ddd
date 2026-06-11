package com.jcaa.usersmanagement.application.port.in.dish;

import com.jcaa.usersmanagement.application.service.dish.dto.command.DeleteDishCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface DeleteDishUseCase {
  void execute(@NotNull @Valid DeleteDishCommand command);
}
