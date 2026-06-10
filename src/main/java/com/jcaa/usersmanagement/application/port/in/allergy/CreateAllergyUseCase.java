package com.jcaa.usersmanagement.application.port.in.allergy;

import com.jcaa.usersmanagement.application.service.allergy.dto.command.CreateAllergyCommand;
import com.jcaa.usersmanagement.domain.model.AllergyModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CreateAllergyUseCase {
  AllergyModel execute(@NotNull @Valid CreateAllergyCommand command);
}
