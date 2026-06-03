package com.jcaa.usersmanagement.application.service.child;

import com.jcaa.usersmanagement.application.port.in.child.UpdateChildUseCase;
import com.jcaa.usersmanagement.application.port.out.child.GetChildByIdPort;
import com.jcaa.usersmanagement.application.port.out.child.UpdateChildPort;
import com.jcaa.usersmanagement.application.service.child.dto.command.UpdateChildCommand;
import com.jcaa.usersmanagement.domain.exception.ChildNotFoundException;
import com.jcaa.usersmanagement.domain.model.ChildModel;
import com.jcaa.usersmanagement.domain.valueobject.ChildId;
import com.jcaa.usersmanagement.domain.valueobject.ChildName;
import com.jcaa.usersmanagement.domain.enums.ChildStatus;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UpdateChildService implements UpdateChildUseCase {

  private final UpdateChildPort updateChildPort;
  private final GetChildByIdPort getChildByIdPort;
  private final Validator validator;

  @Override
  public ChildModel execute(final UpdateChildCommand command) {
    validate(command);

    final ChildId id = new ChildId(command.id());
    final ChildModel existing = getChildByIdPort.getById(id)
        .orElseThrow(() -> ChildNotFoundException.becauseIdWasNotFound(id.value()));

    // Preservar la matrícula original del niño
    final ChildModel updated = new ChildModel(
        existing.getId(),
        existing.getEnrollment(),
        new ChildName(command.fullName()),
        command.birthDate(),
        command.admissionDate(),
        command.dischargeDate(),
        ChildStatus.fromString(command.status()));

    return updateChildPort.update(updated);
  }

  private void validate(final UpdateChildCommand command) {
    final Set<ConstraintViolation<UpdateChildCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
