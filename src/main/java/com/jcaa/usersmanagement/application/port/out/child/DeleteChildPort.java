package com.jcaa.usersmanagement.application.port.out.child;

import com.jcaa.usersmanagement.domain.valueobject.ChildId;

public interface DeleteChildPort {
  void delete(ChildId id);
}
