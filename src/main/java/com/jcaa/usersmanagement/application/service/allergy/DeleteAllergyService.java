package com.jcaa.usersmanagement.application.service.allergy;

import com.jcaa.usersmanagement.application.port.in.allergy.DeleteAllergyUseCase;
import com.jcaa.usersmanagement.application.port.out.allergy.DeleteAllergyPort;
import com.jcaa.usersmanagement.application.port.out.allergy.GetAllergyByIdPort;
import com.jcaa.usersmanagement.application.service.allergy.dto.command.DeleteAllergyCommand;
import com.jcaa.usersmanagement.application.service.allergy.mapper.AllergyApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.AllergyNotFoundException;
import com.jcaa.usersmanagement.domain.valueobject.AllergyId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteAllergyService implements DeleteAllergyUseCase {

  private final DeleteAllergyPort deleteAllergyPort;
  private final GetAllergyByIdPort getAllergyByIdPort;
  private final Validator validator;

  @Override
  public void execute(final DeleteAllergyCommand command) {
    validate(command);
    final AllergyId id = AllergyApplicationMapper.fromDeleteCommandToAllergyId(command);
    getAllergyByIdPort.getById(id)
        .orElseThrow(() -> AllergyNotFoundException.becauseIdWasNotFound(id.value()));
    deleteAllergyPort.delete(id);
  }

  private void validate(final DeleteAllergyCommand command) {
    final Set<ConstraintViolation<DeleteAllergyCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
