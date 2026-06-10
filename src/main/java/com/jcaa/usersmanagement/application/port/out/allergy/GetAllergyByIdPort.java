package com.jcaa.usersmanagement.application.port.out.allergy;

import com.jcaa.usersmanagement.domain.model.AllergyModel;
import com.jcaa.usersmanagement.domain.valueobject.AllergyId;
import java.util.Optional;

public interface GetAllergyByIdPort {
  Optional<AllergyModel> getById(AllergyId id);
}
