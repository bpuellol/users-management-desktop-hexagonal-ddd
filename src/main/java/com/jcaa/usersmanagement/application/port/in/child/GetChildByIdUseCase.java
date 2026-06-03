package com.jcaa.usersmanagement.application.port.in.child;

import com.jcaa.usersmanagement.application.service.child.dto.query.GetChildByIdQuery;
import com.jcaa.usersmanagement.domain.model.ChildModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface GetChildByIdUseCase {
  ChildModel execute(@NotNull @Valid GetChildByIdQuery query);
}
