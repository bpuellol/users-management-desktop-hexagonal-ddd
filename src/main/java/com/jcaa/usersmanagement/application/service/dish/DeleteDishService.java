package com.jcaa.usersmanagement.application.service.dish;

import com.jcaa.usersmanagement.application.port.in.dish.DeleteDishUseCase;
import com.jcaa.usersmanagement.application.port.out.dish.DeleteDishPort;
import com.jcaa.usersmanagement.application.port.out.dish.GetDishByIdPort;
import com.jcaa.usersmanagement.application.service.dish.dto.command.DeleteDishCommand;
import com.jcaa.usersmanagement.application.service.dish.mapper.DishApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.DishNotFoundException;
import com.jcaa.usersmanagement.domain.valueobject.DishId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteDishService implements DeleteDishUseCase {

  private final DeleteDishPort deleteDishPort;
  private final GetDishByIdPort getDishByIdPort;
  private final Validator validator;

  @Override
  public void execute(final DeleteDishCommand command) {
    validate(command);
    final DishId id = DishApplicationMapper.fromDeleteCommandToDishId(command);
    getDishByIdPort.getById(id)
        .orElseThrow(() -> DishNotFoundException.becauseIdWasNotFound(id.value()));
    deleteDishPort.delete(id);
  }

  private void validate(final DeleteDishCommand command) {
    final Set<ConstraintViolation<DeleteDishCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
  }
}
