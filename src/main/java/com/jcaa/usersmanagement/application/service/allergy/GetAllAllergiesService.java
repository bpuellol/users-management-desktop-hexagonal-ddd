package com.jcaa.usersmanagement.application.service.allergy;

import com.jcaa.usersmanagement.application.port.in.allergy.GetAllAllergiesUseCase;
import com.jcaa.usersmanagement.application.port.out.allergy.GetAllAllergiesPort;
import com.jcaa.usersmanagement.domain.model.AllergyModel;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class GetAllAllergiesService implements GetAllAllergiesUseCase {

  private final GetAllAllergiesPort getAllAllergiesPort;

  @Override
  public List<AllergyModel> execute() {
    return getAllAllergiesPort.getAll();
  }
}
