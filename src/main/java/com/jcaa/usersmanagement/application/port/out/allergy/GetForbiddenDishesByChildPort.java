package com.jcaa.usersmanagement.application.port.out.allergy;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.dto.ForbiddenDishResponse;
import java.util.List;

/**
 * Puerto de salida — CEA #9:
 * "Mostrar las alergias registradas y los platos que NO pueden ser consumidos por cada niño."
 * Requiere JOIN entre allergies, children y dishes por ingrediente.
 */
public interface GetForbiddenDishesByChildPort {
  List<ForbiddenDishResponse> getForbiddenDishesByChild();
}
