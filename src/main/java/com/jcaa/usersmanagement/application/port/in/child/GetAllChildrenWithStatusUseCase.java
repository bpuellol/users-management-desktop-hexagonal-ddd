package com.jcaa.usersmanagement.application.port.in.child;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.dto.ChildStatusResponse;
import java.util.List;

/**
 * Caso de uso — CEA #1:
 * "Listar todos los niños inscritos en la guardería, junto con su estado (activo/baja)."
 */
public interface GetAllChildrenWithStatusUseCase {
  List<ChildStatusResponse> execute();
}
