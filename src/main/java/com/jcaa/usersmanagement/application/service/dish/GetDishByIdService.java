package com.jcaa.usersmanagement.application.service.dish;

import com.jcaa.usersmanagement.application.port.in.dish.GetDishByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.dish.GetDishByIdPort;
import com.jcaa.usersmanagement.application.service.dish.dto.query.GetDishByIdQuery;
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
public final class GetDishByIdService implements GetDishByIdUseCase {

  private final GetDishByIdPort getDishByIdPort;
  private final Validator validator;

  @Override
  public DishModel execute(final GetDishByIdQuery query) {
    validate(query);
    final DishId id = DishApplicationMapper.fromGetDishByIdQueryToDishId(query);
    return getDishByIdPort.getById(id)
        .orElseThrow(() -> DishNotFoundException.becauseIdWasNotFound(id.value()));
  }

  private void validate(final GetDishByIdQuery query) {
    final Set<ConstraintViolation<GetDishByIdQuery>> violations = validator.validate(query);
    if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
  }
}
