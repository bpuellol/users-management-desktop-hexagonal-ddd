package com.jcaa.usersmanagement.application.port.out.dish;

import com.jcaa.usersmanagement.domain.model.DishModel;
import java.util.List;

public interface GetAllDishesPort {
  List<DishModel> getAll();
}
