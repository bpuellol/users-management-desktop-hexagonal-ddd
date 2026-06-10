package com.jcaa.usersmanagement.application.port.out.allergy;

import com.jcaa.usersmanagement.domain.model.AllergyModel;
import java.util.List;

public interface GetAllAllergiesPort {
  List<AllergyModel> getAll();
}
