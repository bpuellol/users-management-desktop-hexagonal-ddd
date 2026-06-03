package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.mapper;

import com.jcaa.usersmanagement.application.service.child.dto.command.CreateChildCommand;
import com.jcaa.usersmanagement.application.service.child.dto.command.DeleteChildCommand;
import com.jcaa.usersmanagement.application.service.child.dto.command.UpdateChildCommand;
import com.jcaa.usersmanagement.application.service.child.dto.query.GetChildByIdQuery;
import com.jcaa.usersmanagement.domain.model.ChildModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.dto.ChildResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.dto.CreateChildRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.dto.UpdateChildRequest;
import java.util.List;

public final class ChildDesktopMapper {

  private ChildDesktopMapper() {}

  public static CreateChildCommand toCreateCommand(final CreateChildRequest request) {
    return new CreateChildCommand(
        request.id(), request.enrollment(), request.fullName(),
        request.birthDate(), request.admissionDate());
  }

  public static UpdateChildCommand toUpdateCommand(final UpdateChildRequest request) {
    return new UpdateChildCommand(
        request.id(), request.fullName(), request.birthDate(),
        request.admissionDate(), request.dischargeDate(), request.status());
  }

  public static DeleteChildCommand toDeleteCommand(final String id) {
    return new DeleteChildCommand(id);
  }

  public static GetChildByIdQuery toGetByIdQuery(final String id) {
    return new GetChildByIdQuery(id);
  }

  public static ChildResponse toResponse(final ChildModel child) {
    return new ChildResponse(
        child.getId().value(),
        child.getEnrollment().value(),
        child.getFullName().value(),
        child.getBirthDate(),
        child.getAdmissionDate(),
        child.getDischargeDate() != null ? child.getDischargeDate() : "-",
        child.getStatus().name());
  }

  public static List<ChildResponse> toResponseList(final List<ChildModel> children) {
    return children.stream().map(ChildDesktopMapper::toResponse).toList();
  }
}
