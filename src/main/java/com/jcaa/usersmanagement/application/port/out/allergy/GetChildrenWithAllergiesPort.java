package com.jcaa.usersmanagement.application.port.out.allergy;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.dto.ChildAllergyResponse;
import java.util.List;

/**
 * Puerto de salida — CEA #7:
 * "Consultar los niños con alergias registradas y los ingredientes que deben evitar."
 * Requiere JOIN entre allergies y children.
 */
public interface GetChildrenWithAllergiesPort {
  List<ChildAllergyResponse> getChildrenWithAllergies();
}
