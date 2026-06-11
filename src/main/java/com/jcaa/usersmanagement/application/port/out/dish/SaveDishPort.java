package com.jcaa.usersmanagement.application.port.out.dish;

import com.jcaa.usersmanagement.domain.model.DishModel;

public interface SaveDishPort {
  DishModel save(DishModel dish);
}
