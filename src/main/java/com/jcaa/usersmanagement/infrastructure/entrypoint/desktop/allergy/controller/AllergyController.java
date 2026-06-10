package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.controller;

import com.jcaa.usersmanagement.application.port.in.allergy.CreateAllergyUseCase;
import com.jcaa.usersmanagement.application.port.in.allergy.DeleteAllergyUseCase;
import com.jcaa.usersmanagement.application.port.in.allergy.GetAllAllergiesUseCase;
import com.jcaa.usersmanagement.application.port.in.allergy.GetAllergyByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.allergy.UpdateAllergyUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.dto.AllergyResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.dto.CreateAllergyRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.dto.UpdateAllergyRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.mapper.AllergyDesktopMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * Controlador de presentación para el módulo de alergias.
 * Traduce las solicitudes del CLI hacia los casos de uso de la capa de aplicación.
 */
@RequiredArgsConstructor
public final class AllergyController {

  private final CreateAllergyUseCase  createAllergyUseCase;
  private final UpdateAllergyUseCase  updateAllergyUseCase;
  private final DeleteAllergyUseCase  deleteAllergyUseCase;
  private final GetAllergyByIdUseCase getAllergyByIdUseCase;
  private final GetAllAllergiesUseCase getAllAllergiesUseCase;

  public List<AllergyResponse> listAllAllergies() {
    return AllergyDesktopMapper.toResponseList(getAllAllergiesUseCase.execute());
  }

  public AllergyResponse findAllergyById(final String id) {
    final var query = AllergyDesktopMapper.toGetByIdQuery(id);
    return AllergyDesktopMapper.toResponse(getAllergyByIdUseCase.execute(query));
  }

  public AllergyResponse createAllergy(final CreateAllergyRequest request) {
    final var command = AllergyDesktopMapper.toCreateCommand(request);
    return AllergyDesktopMapper.toResponse(createAllergyUseCase.execute(command));
  }

  public AllergyResponse updateAllergy(final UpdateAllergyRequest request) {
    final var command = AllergyDesktopMapper.toUpdateCommand(request);
    return AllergyDesktopMapper.toResponse(updateAllergyUseCase.execute(command));
  }

  public void deleteAllergy(final String id) {
    final var command = AllergyDesktopMapper.toDeleteCommand(id);
    deleteAllergyUseCase.execute(command);
  }
}
