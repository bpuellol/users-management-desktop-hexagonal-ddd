package com.jcaa.usersmanagement.application.port.in.allergy;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.dto.ForbiddenDishResponse;
import java.util.List;

/**
 * Caso de uso — CEA #9:
 * "Mostrar las alergias registradas y los platos que NO pueden ser consumidos por cada niño."
 */
public interface GetForbiddenDishesByChildUseCase {
  List<ForbiddenDishResponse> execute();
}
