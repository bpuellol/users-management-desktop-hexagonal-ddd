package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dish.mapper;

import com.jcaa.usersmanagement.domain.model.DishModel;
import com.jcaa.usersmanagement.domain.valueobject.DishId;
import com.jcaa.usersmanagement.domain.valueobject.DishName;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dish.dto.DishPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dish.entity.DishEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DishPersistenceMapper {

  public DishPersistenceDto fromModelToDto(final DishModel dish) {
    return new DishPersistenceDto(
        dish.getId().value(),
        dish.getName().value(),
        dish.getIngredients(),
        null, null);
  }

  public DishEntity fromResultSetToEntity(final ResultSet rs) throws SQLException {
    return new DishEntity(
        rs.getString("id"),
        rs.getString("name"),
        rs.getString("ingredients"),
        rs.getString("created_at"),
        rs.getString("updated_at"));
  }

  public DishModel fromEntityToModel(final DishEntity entity) {
    return new DishModel(
        new DishId(entity.id()),
        new DishName(entity.name()),
        entity.ingredients());
  }

  public DishModel fromResultSetToModel(final ResultSet rs) throws SQLException {
    return fromEntityToModel(fromResultSetToEntity(rs));
  }

  public List<DishModel> fromResultSetToModelList(final ResultSet rs) throws SQLException {
    final List<DishModel> dishes = new ArrayList<>();
    while (rs.next()) {
      dishes.add(fromResultSetToModel(rs));
    }
    return dishes;
  }
}
