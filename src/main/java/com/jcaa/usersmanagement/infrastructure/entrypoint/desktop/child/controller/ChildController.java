package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.controller;

import com.jcaa.usersmanagement.application.port.in.child.CreateChildUseCase;
import com.jcaa.usersmanagement.application.port.in.child.DeleteChildUseCase;
import com.jcaa.usersmanagement.application.port.in.child.GetAllChildrenUseCase;
import com.jcaa.usersmanagement.application.port.in.child.GetChildByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.child.UpdateChildUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.dto.ChildResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.dto.CreateChildRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.dto.UpdateChildRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.mapper.ChildDesktopMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * Controlador de presentación para el módulo de niños.
 * Traduce las solicitudes del CLI hacia los casos de uso de la capa de aplicación.
 */
@RequiredArgsConstructor
public final class ChildController {

  private final CreateChildUseCase  createChildUseCase;
  private final UpdateChildUseCase  updateChildUseCase;
  private final DeleteChildUseCase  deleteChildUseCase;
  private final GetChildByIdUseCase getChildByIdUseCase;
  private final GetAllChildrenUseCase getAllChildrenUseCase;

  public List<ChildResponse> listAllChildren() {
    return ChildDesktopMapper.toResponseList(getAllChildrenUseCase.execute());
  }

  public ChildResponse findChildById(final String id) {
    final var query = ChildDesktopMapper.toGetByIdQuery(id);
    return ChildDesktopMapper.toResponse(getChildByIdUseCase.execute(query));
  }

  public ChildResponse createChild(final CreateChildRequest request) {
    final var command = ChildDesktopMapper.toCreateCommand(request);
    return ChildDesktopMapper.toResponse(createChildUseCase.execute(command));
  }

  public ChildResponse updateChild(final UpdateChildRequest request) {
    final var command = ChildDesktopMapper.toUpdateCommand(request);
    return ChildDesktopMapper.toResponse(updateChildUseCase.execute(command));
  }

  public void deleteChild(final String id) {
    final var command = ChildDesktopMapper.toDeleteCommand(id);
    deleteChildUseCase.execute(command);
  }
}
