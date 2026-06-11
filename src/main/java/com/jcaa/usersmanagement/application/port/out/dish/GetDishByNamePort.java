package com.jcaa.usersmanagement.application.port.out.dish;

import com.jcaa.usersmanagement.domain.model.DishModel;
import com.jcaa.usersmanagement.domain.valueobject.DishName;
import java.util.Optional;

public interface GetDishByNamePort {
  Optional<DishModel> getByName(DishName name);
}
