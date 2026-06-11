package com.jcaa.usersmanagement.application.port.out.dish;

import com.jcaa.usersmanagement.domain.model.DishModel;
import com.jcaa.usersmanagement.domain.valueobject.DishId;
import java.util.Optional;

public interface GetDishByIdPort {
  Optional<DishModel> getById(DishId id);
}
