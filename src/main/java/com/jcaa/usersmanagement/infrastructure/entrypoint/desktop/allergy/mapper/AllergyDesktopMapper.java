package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.mapper;

import com.jcaa.usersmanagement.application.service.allergy.dto.command.CreateAllergyCommand;
import com.jcaa.usersmanagement.application.service.allergy.dto.command.DeleteAllergyCommand;
import com.jcaa.usersmanagement.application.service.allergy.dto.command.UpdateAllergyCommand;
import com.jcaa.usersmanagement.application.service.allergy.dto.query.GetAllergyByIdQuery;
import com.jcaa.usersmanagement.domain.model.AllergyModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.dto.AllergyResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.dto.CreateAllergyRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.dto.UpdateAllergyRequest;
import java.util.List;

public final class AllergyDesktopMapper {

  private AllergyDesktopMapper() {}

  public static CreateAllergyCommand toCreateCommand(final CreateAllergyRequest request) {
    return new CreateAllergyCommand(
        request.id(), request.childId(), request.ingredient(),
        request.severity(), request.notes());
  }

  public static UpdateAllergyCommand toUpdateCommand(final UpdateAllergyRequest request) {
    return new UpdateAllergyCommand(
        request.id(), request.ingredient(), request.severity(), request.notes());
  }

  public static DeleteAllergyCommand toDeleteCommand(final String id) {
    return new DeleteAllergyCommand(id);
  }

  public static GetAllergyByIdQuery toGetByIdQuery(final String id) {
    return new GetAllergyByIdQuery(id);
  }

  public static AllergyResponse toResponse(final AllergyModel allergy) {
    return new AllergyResponse(
        allergy.getId().value(),
        allergy.getChildId().value(),
        allergy.getIngredient().value(),
        allergy.getSeverity().name(),
        allergy.getNotes() != null ? allergy.getNotes() : "-");
  }

  public static List<AllergyResponse> toResponseList(final List<AllergyModel> allergies) {
    return allergies.stream().map(AllergyDesktopMapper::toResponse).toList();
  }
}
