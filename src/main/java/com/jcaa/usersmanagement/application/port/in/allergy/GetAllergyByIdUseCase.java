package com.jcaa.usersmanagement.application.port.in.allergy;

import com.jcaa.usersmanagement.application.service.allergy.dto.query.GetAllergyByIdQuery;
import com.jcaa.usersmanagement.domain.model.AllergyModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface GetAllergyByIdUseCase {
  AllergyModel execute(@NotNull @Valid GetAllergyByIdQuery query);
}
