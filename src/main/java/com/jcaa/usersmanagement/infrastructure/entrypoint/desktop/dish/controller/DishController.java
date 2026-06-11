package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.controller;

import com.jcaa.usersmanagement.application.port.in.dish.CreateDishUseCase;
import com.jcaa.usersmanagement.application.port.in.dish.DeleteDishUseCase;
import com.jcaa.usersmanagement.application.port.in.dish.GetAllDishesUseCase;
import com.jcaa.usersmanagement.application.port.in.dish.GetDishByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.dish.UpdateDishUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.dto.CreateDishRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.dto.DishResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.dto.UpdateDishRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.mapper.DishDesktopMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * Controlador de presentación para el módulo de platos del comedor infantil.
 * Traduce solicitudes del CLI hacia los casos de uso de la capa de aplicación.
 */
@RequiredArgsConstructor
public final class DishController {

  private final CreateDishUseCase  createDishUseCase;
  private final UpdateDishUseCase  updateDishUseCase;
  private final DeleteDishUseCase  deleteDishUseCase;
  private final GetDishByIdUseCase getDishByIdUseCase;
  private final GetAllDishesUseCase getAllDishesUseCase;

  public List<DishResponse> listAllDishes() {
    return DishDesktopMapper.toResponseList(getAllDishesUseCase.execute());
  }

  public DishResponse findDishById(final String id) {
    return DishDesktopMapper.toResponse(
        getDishByIdUseCase.execute(DishDesktopMapper.toGetByIdQuery(id)));
  }

  public DishResponse createDish(final CreateDishRequest request) {
    return DishDesktopMapper.toResponse(
        createDishUseCase.execute(DishDesktopMapper.toCreateCommand(request)));
  }

  public DishResponse updateDish(final UpdateDishRequest request) {
    return DishDesktopMapper.toResponse(
        updateDishUseCase.execute(DishDesktopMapper.toUpdateCommand(request)));
  }

  public void deleteDish(final String id) {
    deleteDishUseCase.execute(DishDesktopMapper.toDeleteCommand(id));
  }
}
