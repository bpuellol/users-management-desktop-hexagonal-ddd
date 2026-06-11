package com.jcaa.usersmanagement.application.port.in.allergy;

import com.jcaa.usersmanagement.domain.model.AllergyModel;
import java.util.List;

public interface GetAllAllergiesUseCase {
  List<AllergyModel> execute();
}
