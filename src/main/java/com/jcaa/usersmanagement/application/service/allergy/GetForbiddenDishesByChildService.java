package com.jcaa.usersmanagement.application.service.allergy;

import com.jcaa.usersmanagement.application.port.in.allergy.GetForbiddenDishesByChildUseCase;
import com.jcaa.usersmanagement.application.port.out.allergy.GetForbiddenDishesByChildPort;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.dto.ForbiddenDishResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * Servicio de aplicación — CEA #9:
 * "Mostrar las alergias registradas y los platos que NO pueden ser consumidos por cada niño."
 */
@RequiredArgsConstructor
public final class GetForbiddenDishesByChildService implements GetForbiddenDishesByChildUseCase {

  private final GetForbiddenDishesByChildPort getForbiddenDishesByChildPort;

  @Override
  public List<ForbiddenDishResponse> execute() {
    return getForbiddenDishesByChildPort.getForbiddenDishesByChild();
  }
}
