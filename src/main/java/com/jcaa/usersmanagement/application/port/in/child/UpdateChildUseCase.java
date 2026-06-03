package com.jcaa.usersmanagement.application.port.in.child;

import com.jcaa.usersmanagement.application.service.child.dto.command.UpdateChildCommand;
import com.jcaa.usersmanagement.domain.model.ChildModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface UpdateChildUseCase {
  ChildModel execute(@NotNull @Valid UpdateChildCommand command);
}
