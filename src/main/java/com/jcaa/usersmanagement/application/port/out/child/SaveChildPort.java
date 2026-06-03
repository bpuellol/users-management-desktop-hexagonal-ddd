package com.jcaa.usersmanagement.application.port.out.child;

import com.jcaa.usersmanagement.domain.model.ChildModel;

public interface SaveChildPort {
  ChildModel save(ChildModel child);
}
