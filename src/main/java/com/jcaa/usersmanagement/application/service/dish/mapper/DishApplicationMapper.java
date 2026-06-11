package com.jcaa.usersmanagement.application.service.dish.mapper;

import com.jcaa.usersmanagement.application.service.dish.dto.command.CreateDishCommand;
import com.jcaa.usersmanagement.application.service.dish.dto.command.DeleteDishCommand;
import com.jcaa.usersmanagement.application.service.dish.dto.command.UpdateDishCommand;
import com.jcaa.usersmanagement.application.service.dish.dto.query.GetDishByIdQuery;
import com.jcaa.usersmanagement.domain.model.DishModel;
import com.jcaa.usersmanagement.domain.valueobject.DishId;
import com.jcaa.usersmanagement.domain.valueobject.DishName;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DishApplicationMapper {

  public DishModel fromCreateCommandToModel(final CreateDishCommand command) {
    return DishModel.create(
        new DishId(command.id()),
        new DishName(command.name()),
        command.ingredients());
  }

  public DishModel fromUpdateCommandToModel(
      final UpdateDishCommand command, final DishModel existing) {
    return new DishModel(
        existing.getId(),
        new DishName(command.name()),
        command.ingredients());
  }

  public DishId fromGetDishByIdQueryToDishId(final GetDishByIdQuery query) {
    return new DishId(query.id());
  }

  public DishId fromDeleteCommandToDishId(final DeleteDishCommand command) {
    return new DishId(command.id());
  }
}
