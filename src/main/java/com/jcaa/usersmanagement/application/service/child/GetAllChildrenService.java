package com.jcaa.usersmanagement.application.service.child;

import com.jcaa.usersmanagement.application.port.in.child.GetAllChildrenUseCase;
import com.jcaa.usersmanagement.application.port.out.child.GetAllChildrenPort;
import com.jcaa.usersmanagement.domain.model.ChildModel;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class GetAllChildrenService implements GetAllChildrenUseCase {

  private final GetAllChildrenPort getAllChildrenPort;

  @Override
  public List<ChildModel> execute() {
    return getAllChildrenPort.getAll();
  }
}
