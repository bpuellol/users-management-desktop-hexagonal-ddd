package com.jcaa.usersmanagement.application.service.child;

import com.jcaa.usersmanagement.application.port.in.child.DeleteChildUseCase;
import com.jcaa.usersmanagement.application.port.out.child.DeleteChildPort;
import com.jcaa.usersmanagement.application.port.out.child.GetChildByIdPort;
import com.jcaa.usersmanagement.application.service.child.dto.command.DeleteChildCommand;
import com.jcaa.usersmanagement.application.service.child.mapper.ChildApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.ChildNotFoundException;
import com.jcaa.usersmanagement.domain.valueobject.ChildId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteChildService implements DeleteChildUseCase {

  private final DeleteChildPort deleteChildPort;
  private final GetChildByIdPort getChildByIdPort;
  private final Validator validator;

  @Override
  public void execute(final DeleteChildCommand command) {
    validate(command);
    final ChildId id = ChildApplicationMapper.fromDeleteCommandToChildId(command);
    getChildByIdPort.getById(id)
        .orElseThrow(() -> ChildNotFoundException.becauseIdWasNotFound(id.value()));
    deleteChildPort.delete(id);
  }

  private void validate(final DeleteChildCommand command) {
    final Set<ConstraintViolation<DeleteChildCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
