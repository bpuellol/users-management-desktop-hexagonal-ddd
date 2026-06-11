package com.jcaa.usersmanagement.application.service.dish;

import com.jcaa.usersmanagement.application.port.in.dish.UpdateDishUseCase;
import com.jcaa.usersmanagement.application.port.out.dish.GetDishByIdPort;
import com.jcaa.usersmanagement.application.port.out.dish.UpdateDishPort;
import com.jcaa.usersmanagement.application.service.dish.dto.command.UpdateDishCommand;
import com.jcaa.usersmanagement.application.service.dish.mapper.DishApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.DishNotFoundException;
import com.jcaa.usersmanagement.domain.model.DishModel;
import com.jcaa.usersmanagement.domain.valueobject.DishId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UpdateDishService implements UpdateDishUseCase {

  private final UpdateDishPort updateDishPort;
  private final GetDishByIdPort getDishByIdPort;
  private final Validator validator;

  @Override
  public DishModel execute(final UpdateDishCommand command) {
    validate(command);
    final DishId id = new DishId(command.id());
    final DishModel existing = getDishByIdPort.getById(id)
        .orElseThrow(() -> DishNotFoundException.becauseIdWasNotFound(id.value()));
    return updateDishPort.update(DishApplicationMapper.fromUpdateCommandToModel(command, existing));
  }

  private void validate(final UpdateDishCommand command) {
    final Set<ConstraintViolation<UpdateDishCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
  }
}
