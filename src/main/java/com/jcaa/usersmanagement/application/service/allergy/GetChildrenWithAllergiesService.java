package com.jcaa.usersmanagement.application.service.allergy;

import com.jcaa.usersmanagement.application.port.in.allergy.GetChildrenWithAllergiesUseCase;
import com.jcaa.usersmanagement.application.port.out.allergy.GetChildrenWithAllergiesPort;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.dto.ChildAllergyResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * Servicio de aplicación — CEA #7:
 * "Consultar los niños con alergias registradas y los ingredientes que deben evitar."
 */
@RequiredArgsConstructor
public final class GetChildrenWithAllergiesService implements GetChildrenWithAllergiesUseCase {

  private final GetChildrenWithAllergiesPort getChildrenWithAllergiesPort;

  @Override
  public List<ChildAllergyResponse> execute() {
    return getChildrenWithAllergiesPort.getChildrenWithAllergies();
  }
}
