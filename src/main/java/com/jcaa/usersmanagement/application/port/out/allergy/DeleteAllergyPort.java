package com.jcaa.usersmanagement.application.port.out.allergy;

import com.jcaa.usersmanagement.domain.valueobject.AllergyId;

public interface DeleteAllergyPort {
  void delete(AllergyId id);
}
