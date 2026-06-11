package com.jcaa.usersmanagement.application.service.allergy;

import com.jcaa.usersmanagement.application.port.in.allergy.UpdateAllergyUseCase;
import com.jcaa.usersmanagement.application.port.out.allergy.GetAllergyByIdPort;
import com.jcaa.usersmanagement.application.port.out.allergy.UpdateAllergyPort;
import com.jcaa.usersmanagement.application.service.allergy.dto.command.UpdateAllergyCommand;
import com.jcaa.usersmanagement.application.service.allergy.mapper.AllergyApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.AllergyNotFoundException;
import com.jcaa.usersmanagement.domain.model.AllergyModel;
import com.jcaa.usersmanagement.domain.valueobject.AllergyId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UpdateAllergyService implements UpdateAllergyUseCase {

  private final UpdateAllergyPort updateAllergyPort;
  private final GetAllergyByIdPort getAllergyByIdPort;
  private final Validator validator;

  @Override
  public AllergyModel execute(final UpdateAllergyCommand command) {
    validate(command);

    final AllergyId id = new AllergyId(command.id());
    final AllergyModel existing = getAllergyByIdPort.getById(id)
        .orElseThrow(() -> AllergyNotFoundException.becauseIdWasNotFound(id.value()));

    // Preservar childId original — no se puede reasignar una alergia a otro niño
    final AllergyModel updated =
        AllergyApplicationMapper.fromUpdateCommandToModel(command, existing);

    return updateAllergyPort.update(updated);
  }

  private void validate(final UpdateAllergyCommand command) {
    final Set<ConstraintViolation<UpdateAllergyCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
