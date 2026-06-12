package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.controller;

import com.jcaa.usersmanagement.application.port.in.allergy.CreateAllergyUseCase;
import com.jcaa.usersmanagement.application.port.in.allergy.DeleteAllergyUseCase;
import com.jcaa.usersmanagement.application.port.in.allergy.GetAllAllergiesUseCase;
import com.jcaa.usersmanagement.application.port.in.allergy.GetAllergyByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.allergy.GetChildrenWithAllergiesUseCase;
import com.jcaa.usersmanagement.application.port.in.allergy.GetForbiddenDishesByChildUseCase;
import com.jcaa.usersmanagement.application.port.in.allergy.UpdateAllergyUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.dto.AllergyResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.dto.ChildAllergyResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.dto.CreateAllergyRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.dto.ForbiddenDishResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.dto.UpdateAllergyRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.mapper.AllergyDesktopMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class AllergyController {

  // ── CRUDL ──────────────────────────────────────────────
  private final CreateAllergyUseCase             createAllergyUseCase;
  private final UpdateAllergyUseCase             updateAllergyUseCase;
  private final DeleteAllergyUseCase             deleteAllergyUseCase;
  private final GetAllergyByIdUseCase            getAllergyByIdUseCase;
  private final GetAllAllergiesUseCase           getAllAllergiesUseCase;

  // ── CEA Queries ────────────────────────────────────────
  private final GetChildrenWithAllergiesUseCase  getChildrenWithAllergiesUseCase;
  private final GetForbiddenDishesByChildUseCase getForbiddenDishesByChildUseCase;

  // ── CRUDL methods ──────────────────────────────────────
  public List<AllergyResponse> listAllAllergies() {
    return AllergyDesktopMapper.toResponseList(getAllAllergiesUseCase.execute());
  }

  public AllergyResponse findAllergyById(final String id) {
    return AllergyDesktopMapper.toResponse(
        getAllergyByIdUseCase.execute(AllergyDesktopMapper.toGetByIdQuery(id)));
  }

  public AllergyResponse createAllergy(final CreateAllergyRequest request) {
    return AllergyDesktopMapper.toResponse(
        createAllergyUseCase.execute(AllergyDesktopMapper.toCreateCommand(request)));
  }

  public AllergyResponse updateAllergy(final UpdateAllergyRequest request) {
    return AllergyDesktopMapper.toResponse(
        updateAllergyUseCase.execute(AllergyDesktopMapper.toUpdateCommand(request)));
  }

  public void deleteAllergy(final String id) {
    deleteAllergyUseCase.execute(AllergyDesktopMapper.toDeleteCommand(id));
  }

  // ── CEA Query methods ──────────────────────────────────

  /** CEA #7 — niños con alergias e ingredientes prohibidos */
  public List<ChildAllergyResponse> getChildrenWithAllergies() {
    return getChildrenWithAllergiesUseCase.execute();
  }

  /** CEA #9 — platos que no puede comer cada niño */
  public List<ForbiddenDishResponse> getForbiddenDishesByChild() {
    return getForbiddenDishesByChildUseCase.execute();
  }
}
