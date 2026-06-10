package com.jcaa.usersmanagement.infrastructure.adapter.persistence.allergy.mapper;

import com.jcaa.usersmanagement.domain.enums.AllergySeverity;
import com.jcaa.usersmanagement.domain.model.AllergyModel;
import com.jcaa.usersmanagement.domain.valueobject.AllergyId;
import com.jcaa.usersmanagement.domain.valueobject.ChildId;
import com.jcaa.usersmanagement.domain.valueobject.Ingredient;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.allergy.dto.AllergyPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.allergy.entity.AllergyEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AllergyPersistenceMapper {

  public AllergyPersistenceDto fromModelToDto(final AllergyModel allergy) {
    return new AllergyPersistenceDto(
        allergy.getId().value(),
        allergy.getChildId().value(),
        allergy.getIngredient().value(),
        allergy.getSeverity().name(),
        allergy.getNotes(),
        null,
        null);
  }

  public AllergyEntity fromResultSetToEntity(final ResultSet rs) throws SQLException {
    return new AllergyEntity(
        rs.getString("id"),
        rs.getString("child_id"),
        rs.getString("ingredient"),
        rs.getString("severity"),
        rs.getString("notes"),
        rs.getString("created_at"),
        rs.getString("updated_at"));
  }

  public AllergyModel fromEntityToModel(final AllergyEntity entity) {
    return new AllergyModel(
        new AllergyId(entity.id()),
        new ChildId(entity.childId()),
        new Ingredient(entity.ingredient()),
        AllergySeverity.fromString(entity.severity()),
        entity.notes());
  }

  public AllergyModel fromResultSetToModel(final ResultSet rs) throws SQLException {
    return fromEntityToModel(fromResultSetToEntity(rs));
  }

  public List<AllergyModel> fromResultSetToModelList(final ResultSet rs) throws SQLException {
    final List<AllergyModel> allergies = new ArrayList<>();
    while (rs.next()) {
      allergies.add(fromResultSetToModel(rs));
    }
    return allergies;
  }
}
