package com.jcaa.usersmanagement.application.service.child;

import com.jcaa.usersmanagement.application.port.in.child.GetChildByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.child.GetChildByIdPort;
import com.jcaa.usersmanagement.application.service.child.dto.query.GetChildByIdQuery;
import com.jcaa.usersmanagement.application.service.child.mapper.ChildApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.ChildNotFoundException;
import com.jcaa.usersmanagement.domain.model.ChildModel;
import com.jcaa.usersmanagement.domain.valueobject.ChildId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class GetChildByIdService implements GetChildByIdUseCase {

  private final GetChildByIdPort getChildByIdPort;
  private final Validator validator;

  @Override
  public ChildModel execute(final GetChildByIdQuery query) {
    validate(query);
    final ChildId id = ChildApplicationMapper.fromGetChildByIdQueryToChildId(query);
    return getChildByIdPort.getById(id)
        .orElseThrow(() -> ChildNotFoundException.becauseIdWasNotFound(id.value()));
  }

  private void validate(final GetChildByIdQuery query) {
    final Set<ConstraintViolation<GetChildByIdQuery>> violations = validator.validate(query);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
