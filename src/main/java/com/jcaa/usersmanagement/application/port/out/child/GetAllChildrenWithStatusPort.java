package com.jcaa.usersmanagement.application.port.out.child;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.dto.ChildStatusResponse;
import java.util.List;

/**
 * Puerto de salida — CEA #1:
 * "Listar todos los niños inscritos en la guardería, junto con su estado (activo/baja)."
 */
public interface GetAllChildrenWithStatusPort {
  List<ChildStatusResponse> getAllChildrenWithStatus();
}
