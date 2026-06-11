package com.jcaa.usersmanagement.application.port.in.allergy;

import com.jcaa.usersmanagement.application.service.allergy.dto.command.DeleteAllergyCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface DeleteAllergyUseCase {
  void execute(@NotNull @Valid DeleteAllergyCommand command);
}
