package com.jcaa.usersmanagement.application.service.dish;

import com.jcaa.usersmanagement.application.port.in.dish.GetAllDishesUseCase;
import com.jcaa.usersmanagement.application.port.out.dish.GetAllDishesPort;
import com.jcaa.usersmanagement.domain.model.DishModel;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class GetAllDishesService implements GetAllDishesUseCase {

  private final GetAllDishesPort getAllDishesPort;

  @Override
  public List<DishModel> execute() {
    return getAllDishesPort.getAll();
  }
}
