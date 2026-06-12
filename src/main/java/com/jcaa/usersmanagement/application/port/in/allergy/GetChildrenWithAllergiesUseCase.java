package com.jcaa.usersmanagement.application.port.in.allergy;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.dto.ChildAllergyResponse;
import java.util.List;

/**
 * Caso de uso — CEA #7:
 * "Consultar los niños con alergias registradas y los ingredientes que deben evitar."
 */
public interface GetChildrenWithAllergiesUseCase {
  List<ChildAllergyResponse> execute();
}
