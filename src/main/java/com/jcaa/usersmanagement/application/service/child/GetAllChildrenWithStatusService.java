package com.jcaa.usersmanagement.application.service.child;

import com.jcaa.usersmanagement.application.port.in.child.GetAllChildrenWithStatusUseCase;
import com.jcaa.usersmanagement.application.port.out.child.GetAllChildrenWithStatusPort;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.dto.ChildStatusResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * Servicio de aplicación — CEA #1:
 * "Listar todos los niños inscritos en la guardería, junto con su estado (activo/baja)."
 */
@RequiredArgsConstructor
public final class GetAllChildrenWithStatusService implements GetAllChildrenWithStatusUseCase {

  private final GetAllChildrenWithStatusPort getAllChildrenWithStatusPort;

  @Override
  public List<ChildStatusResponse> execute() {
    return getAllChildrenWithStatusPort.getAllChildrenWithStatus();
  }
}
