package com.jcaa.usersmanagement.application.port.out.allergy;

import com.jcaa.usersmanagement.domain.model.AllergyModel;

public interface SaveAllergyPort {
  AllergyModel save(AllergyModel allergy);
}
