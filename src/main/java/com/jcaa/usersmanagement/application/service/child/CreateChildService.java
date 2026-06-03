package com.jcaa.usersmanagement.application.service.child;

import com.jcaa.usersmanagement.application.port.in.child.CreateChildUseCase;
import com.jcaa.usersmanagement.application.port.out.child.GetChildByEnrollmentPort;
import com.jcaa.usersmanagement.application.port.out.child.SaveChildPort;
import com.jcaa.usersmanagement.application.service.child.dto.command.CreateChildCommand;
import com.jcaa.usersmanagement.application.service.child.mapper.ChildApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.ChildAlreadyExistsException;
import com.jcaa.usersmanagement.domain.model.ChildModel;
import com.jcaa.usersmanagement.domain.valueobject.Enrollment;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateChildService implements CreateChildUseCase {

  private final SaveChildPort saveChildPort;
  private final GetChildByEnrollmentPort getChildByEnrollmentPort;
  private final Validator validator;

  @Override
  public ChildModel execute(final CreateChildCommand command) {
    validate(command);
    ensureEnrollmentIsNotTaken(new Enrollment(command.enrollment()));
    final ChildModel child = ChildApplicationMapper.fromCreateCommandToModel(command);
    return saveChildPort.save(child);
  }

  private void validate(final CreateChildCommand command) {
    final Set<ConstraintViolation<CreateChildCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private void ensureEnrollmentIsNotTaken(final Enrollment enrollment) {
    getChildByEnrollmentPort
        .getByEnrollment(enrollment)
        .ifPresent(ignored -> {
          throw ChildAlreadyExistsException.becauseEnrollmentAlreadyExists(enrollment.value());
        });
  }
}
