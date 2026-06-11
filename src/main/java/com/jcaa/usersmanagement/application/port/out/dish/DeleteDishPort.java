package com.jcaa.usersmanagement.application.port.out.dish;

import com.jcaa.usersmanagement.domain.valueobject.DishId;

public interface DeleteDishPort {
  void delete(DishId id);
}
