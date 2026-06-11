package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.mapper;

import com.jcaa.usersmanagement.application.service.dish.dto.command.CreateDishCommand;
import com.jcaa.usersmanagement.application.service.dish.dto.command.DeleteDishCommand;
import com.jcaa.usersmanagement.application.service.dish.dto.command.UpdateDishCommand;
import com.jcaa.usersmanagement.application.service.dish.dto.query.GetDishByIdQuery;
import com.jcaa.usersmanagement.domain.model.DishModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.dto.CreateDishRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.dto.DishResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.dto.UpdateDishRequest;
import java.util.List;

public final class DishDesktopMapper {

  private DishDesktopMapper() {}

  public static CreateDishCommand toCreateCommand(final CreateDishRequest request) {
    return new CreateDishCommand(request.id(), request.name(), request.ingredients());
  }

  public static UpdateDishCommand toUpdateCommand(final UpdateDishRequest request) {
    return new UpdateDishCommand(request.id(), request.name(), request.ingredients());
  }

  public static DeleteDishCommand toDeleteCommand(final String id) {
    return new DeleteDishCommand(id);
  }

  public static GetDishByIdQuery toGetByIdQuery(final String id) {
    return new GetDishByIdQuery(id);
  }

  public static DishResponse toResponse(final DishModel dish) {
    return new DishResponse(
        dish.getId().value(),
        dish.getName().value(),
        dish.getIngredients());
  }

  public static List<DishResponse> toResponseList(final List<DishModel> dishes) {
    return dishes.stream().map(DishDesktopMapper::toResponse).toList();
  }
}
