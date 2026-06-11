package com.jcaa.usersmanagement.application.port.in.dish;

import com.jcaa.usersmanagement.domain.model.DishModel;
import java.util.List;

public interface GetAllDishesUseCase {
  List<DishModel> execute();
}
