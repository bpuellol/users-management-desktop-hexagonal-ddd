package com.jcaa.usersmanagement.application.service.child.mapper;

import com.jcaa.usersmanagement.application.service.child.dto.command.CreateChildCommand;
import com.jcaa.usersmanagement.application.service.child.dto.command.DeleteChildCommand;
import com.jcaa.usersmanagement.application.service.child.dto.command.UpdateChildCommand;
import com.jcaa.usersmanagement.application.service.child.dto.query.GetChildByIdQuery;
import com.jcaa.usersmanagement.domain.enums.ChildStatus;
import com.jcaa.usersmanagement.domain.model.ChildModel;
import com.jcaa.usersmanagement.domain.valueobject.ChildId;
import com.jcaa.usersmanagement.domain.valueobject.ChildName;
import com.jcaa.usersmanagement.domain.valueobject.Enrollment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ChildApplicationMapper {

  public ChildModel fromCreateCommandToModel(final CreateChildCommand command) {
    return ChildModel.create(
        new ChildId(command.id()),
        new Enrollment(command.enrollment()),
        new ChildName(command.fullName()),
        command.birthDate(),
        command.admissionDate());
  }

  public ChildModel fromUpdateCommandToModel(final UpdateChildCommand command) {
    return new ChildModel(
        new ChildId(command.id()),
        // La matrícula no se actualiza — viene del repositorio; aquí usamos placeholder
        new Enrollment("__unchanged__"),
        new ChildName(command.fullName()),
        command.birthDate(),
        command.admissionDate(),
        command.dischargeDate(),
        ChildStatus.fromString(command.status()));
  }

  public ChildId fromGetChildByIdQueryToChildId(final GetChildByIdQuery query) {
    return new ChildId(query.id());
  }

  public ChildId fromDeleteCommandToChildId(final DeleteChildCommand command) {
    return new ChildId(command.id());
  }
}
