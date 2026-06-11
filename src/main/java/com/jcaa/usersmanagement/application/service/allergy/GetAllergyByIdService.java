package com.jcaa.usersmanagement.application.service.allergy;

import com.jcaa.usersmanagement.application.port.in.allergy.GetAllergyByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.allergy.GetAllergyByIdPort;
import com.jcaa.usersmanagement.application.service.allergy.dto.query.GetAllergyByIdQuery;
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
public final class GetAllergyByIdService implements GetAllergyByIdUseCase {

  private final GetAllergyByIdPort getAllergyByIdPort;
  private final Validator validator;

  @Override
  public AllergyModel execute(final GetAllergyByIdQuery query) {
    validate(query);
    final AllergyId id = AllergyApplicationMapper.fromGetAllergyByIdQueryToAllergyId(query);
    return getAllergyByIdPort.getById(id)
        .orElseThrow(() -> AllergyNotFoundException.becauseIdWasNotFound(id.value()));
  }

  private void validate(final GetAllergyByIdQuery query) {
    final Set<ConstraintViolation<GetAllergyByIdQuery>> violations = validator.validate(query);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
