package com.jcaa.usersmanagement.application.port.in.child;

import com.jcaa.usersmanagement.application.service.child.dto.command.DeleteChildCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface DeleteChildUseCase {
  void execute(@NotNull @Valid DeleteChildCommand command);
}
