package com.jcaa.usersmanagement.application.port.in.child;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.dto.ChildStatusResponse;
import java.util.List;

/**
 * Caso de uso — CEA #2:
 * "Consultar los niños que han sido dados de baja y la fecha en que se retiraron."
 */
public interface GetInactiveChildrenUseCase {
  List<ChildStatusResponse> execute();
}
