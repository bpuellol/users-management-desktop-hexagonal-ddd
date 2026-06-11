package com.jcaa.usersmanagement.application.service.dish;

import com.jcaa.usersmanagement.application.port.in.dish.CreateDishUseCase;
import com.jcaa.usersmanagement.application.port.out.dish.GetDishByNamePort;
import com.jcaa.usersmanagement.application.port.out.dish.SaveDishPort;
import com.jcaa.usersmanagement.application.service.dish.dto.command.CreateDishCommand;
import com.jcaa.usersmanagement.application.service.dish.mapper.DishApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.DishAlreadyExistsException;
import com.jcaa.usersmanagement.domain.model.DishModel;
import com.jcaa.usersmanagement.domain.valueobject.DishName;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateDishService implements CreateDishUseCase {

  private final SaveDishPort saveDishPort;
  private final GetDishByNamePort getDishByNamePort;
  private final Validator validator;

  @Override
  public DishModel execute(final CreateDishCommand command) {
    validate(command);
    getDishByNamePort.getByName(new DishName(command.name()))
        .ifPresent(ignored -> {
          throw DishAlreadyExistsException.becauseNameAlreadyExists(command.name());
        });
    return saveDishPort.save(DishApplicationMapper.fromCreateCommandToModel(command));
  }

  private void validate(final CreateDishCommand command) {
    final Set<ConstraintViolation<CreateDishCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
  }
}
