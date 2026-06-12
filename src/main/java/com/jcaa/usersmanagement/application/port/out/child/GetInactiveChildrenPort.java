package com.jcaa.usersmanagement.application.port.out.child;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.dto.ChildStatusResponse;
import java.util.List;

/**
 * Puerto de salida — CEA #2:
 * "Consultar los niños que han sido dados de baja y la fecha en que se retiraron."
 */
public interface GetInactiveChildrenPort {
  List<ChildStatusResponse> getInactiveChildren();
}
