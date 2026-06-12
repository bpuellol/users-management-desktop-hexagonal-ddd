package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.controller;

import com.jcaa.usersmanagement.application.port.in.child.CreateChildUseCase;
import com.jcaa.usersmanagement.application.port.in.child.DeleteChildUseCase;
import com.jcaa.usersmanagement.application.port.in.child.GetAllChildrenUseCase;
import com.jcaa.usersmanagement.application.port.in.child.GetAllChildrenWithStatusUseCase;
import com.jcaa.usersmanagement.application.port.in.child.GetChildByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.child.GetInactiveChildrenUseCase;
import com.jcaa.usersmanagement.application.port.in.child.UpdateChildUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.dto.ChildResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.dto.ChildStatusResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.dto.CreateChildRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.dto.UpdateChildRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.mapper.ChildDesktopMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ChildController {

  // ── CRUDL ──────────────────────────────────────────────
  private final CreateChildUseCase              createChildUseCase;
  private final UpdateChildUseCase              updateChildUseCase;
  private final DeleteChildUseCase              deleteChildUseCase;
  private final GetChildByIdUseCase             getChildByIdUseCase;
  private final GetAllChildrenUseCase           getAllChildrenUseCase;

  // ── CEA Queries ────────────────────────────────────────
  private final GetAllChildrenWithStatusUseCase getAllChildrenWithStatusUseCase;
  private final GetInactiveChildrenUseCase      getInactiveChildrenUseCase;

  // ── CRUDL methods ──────────────────────────────────────
  public List<ChildResponse> listAllChildren() {
    return ChildDesktopMapper.toResponseList(getAllChildrenUseCase.execute());
  }

  public ChildResponse findChildById(final String id) {
    return ChildDesktopMapper.toResponse(
        getChildByIdUseCase.execute(ChildDesktopMapper.toGetByIdQuery(id)));
  }

  public ChildResponse createChild(final CreateChildRequest request) {
    return ChildDesktopMapper.toResponse(
        createChildUseCase.execute(ChildDesktopMapper.toCreateCommand(request)));
  }

  public ChildResponse updateChild(final UpdateChildRequest request) {
    return ChildDesktopMapper.toResponse(
        updateChildUseCase.execute(ChildDesktopMapper.toUpdateCommand(request)));
  }

  public void deleteChild(final String id) {
    deleteChildUseCase.execute(ChildDesktopMapper.toDeleteCommand(id));
  }

  // ── CEA Query methods ──────────────────────────────────

  /** CEA #1 — todos los niños con su estado */
  public List<ChildStatusResponse> getAllChildrenWithStatus() {
    return getAllChildrenWithStatusUseCase.execute();
  }

  /** CEA #2 — niños dados de baja */
  public List<ChildStatusResponse> getInactiveChildren() {
    return getInactiveChildrenUseCase.execute();
  }
}
