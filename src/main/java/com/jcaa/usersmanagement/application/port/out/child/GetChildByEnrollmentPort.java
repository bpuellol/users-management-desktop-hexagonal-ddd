package com.jcaa.usersmanagement.application.port.out.child;

import com.jcaa.usersmanagement.domain.model.ChildModel;
import com.jcaa.usersmanagement.domain.valueobject.Enrollment;
import java.util.Optional;

public interface GetChildByEnrollmentPort {
  Optional<ChildModel> getByEnrollment(Enrollment enrollment);
}
