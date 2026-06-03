package com.jcaa.usersmanagement.application.port.in.child;

import com.jcaa.usersmanagement.application.service.child.dto.command.CreateChildCommand;
import com.jcaa.usersmanagement.domain.model.ChildModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CreateChildUseCase {
  ChildModel execute(@NotNull @Valid CreateChildCommand command);
}
