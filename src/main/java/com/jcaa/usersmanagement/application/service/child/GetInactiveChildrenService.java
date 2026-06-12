package com.jcaa.usersmanagement.application.service.child;

import com.jcaa.usersmanagement.application.port.in.child.GetInactiveChildrenUseCase;
import com.jcaa.usersmanagement.application.port.out.child.GetInactiveChildrenPort;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.dto.ChildStatusResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * Servicio de aplicación — CEA #2:
 * "Consultar los niños que han sido dados de baja y la fecha en que se retiraron."
 */
@RequiredArgsConstructor
public final class GetInactiveChildrenService implements GetInactiveChildrenUseCase {

  private final GetInactiveChildrenPort getInactiveChildrenPort;

  @Override
  public List<ChildStatusResponse> execute() {
    return getInactiveChildrenPort.getInactiveChildren();
  }
}
