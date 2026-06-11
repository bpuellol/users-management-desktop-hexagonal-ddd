package com.jcaa.usersmanagement.application.port.out.allergy;

import com.jcaa.usersmanagement.domain.model.AllergyModel;

public interface UpdateAllergyPort {
  AllergyModel update(AllergyModel allergy);
}
