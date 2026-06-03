package com.jcaa.usersmanagement.application.port.out.child;

import com.jcaa.usersmanagement.domain.model.ChildModel;
import java.util.List;

public interface GetAllChildrenPort {
  List<ChildModel> getAll();
}
