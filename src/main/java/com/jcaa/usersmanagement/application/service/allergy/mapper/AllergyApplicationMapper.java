package com.jcaa.usersmanagement.application.service.allergy.mapper;

import com.jcaa.usersmanagement.application.service.allergy.dto.command.CreateAllergyCommand;
import com.jcaa.usersmanagement.application.service.allergy.dto.command.DeleteAllergyCommand;
import com.jcaa.usersmanagement.application.service.allergy.dto.command.UpdateAllergyCommand;
import com.jcaa.usersmanagement.application.service.allergy.dto.query.GetAllergyByIdQuery;
import com.jcaa.usersmanagement.domain.enums.AllergySeverity;
import com.jcaa.usersmanagement.domain.model.AllergyModel;
import com.jcaa.usersmanagement.domain.valueobject.AllergyId;
import com.jcaa.usersmanagement.domain.valueobject.ChildId;
import com.jcaa.usersmanagement.domain.valueobject.Ingredient;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AllergyApplicationMapper {

  public AllergyModel fromCreateCommandToModel(final CreateAllergyCommand command) {
    return AllergyModel.create(
        new AllergyId(command.id()),
        new ChildId(command.childId()),
        new Ingredient(command.ingredient()),
        AllergySeverity.fromString(command.severity()),
        command.notes());
  }

  public AllergyModel fromUpdateCommandToModel(
      final UpdateAllergyCommand command, final AllergyModel existing) {
    return new AllergyModel(
        existing.getId(),
        existing.getChildId(),
        new Ingredient(command.ingredient()),
        AllergySeverity.fromString(command.severity()),
        command.notes());
  }

  public AllergyId fromGetAllergyByIdQueryToAllergyId(final GetAllergyByIdQuery query) {
    return new AllergyId(query.id());
  }

  public AllergyId fromDeleteCommandToAllergyId(final DeleteAllergyCommand command) {
    return new AllergyId(command.id());
  }
}
