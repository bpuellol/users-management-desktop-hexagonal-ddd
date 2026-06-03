package com.jcaa.usersmanagement.infrastructure.adapter.persistence.child.mapper;

import com.jcaa.usersmanagement.domain.enums.ChildStatus;
import com.jcaa.usersmanagement.domain.model.ChildModel;
import com.jcaa.usersmanagement.domain.valueobject.ChildId;
import com.jcaa.usersmanagement.domain.valueobject.ChildName;
import com.jcaa.usersmanagement.domain.valueobject.Enrollment;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.child.dto.ChildPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.child.entity.ChildEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ChildPersistenceMapper {

  public ChildPersistenceDto fromModelToDto(final ChildModel child) {
    return new ChildPersistenceDto(
        child.getId().value(),
        child.getEnrollment().value(),
        child.getFullName().value(),
        child.getBirthDate(),
        child.getAdmissionDate(),
        child.getDischargeDate(),
        child.getStatus().name(),
        null,
        null);
  }

  public ChildEntity fromResultSetToEntity(final ResultSet rs) throws SQLException {
    return new ChildEntity(
        rs.getString("id"),
        rs.getString("enrollment"),
        rs.getString("full_name"),
        rs.getString("birth_date"),
        rs.getString("admission_date"),
        rs.getString("discharge_date"),
        rs.getString("status"),
        rs.getString("created_at"),
        rs.getString("updated_at"));
  }

  public ChildModel fromEntityToModel(final ChildEntity entity) {
    return new ChildModel(
        new ChildId(entity.id()),
        new Enrollment(entity.enrollment()),
        new ChildName(entity.fullName()),
        entity.birthDate(),
        entity.admissionDate(),
        entity.dischargeDate(),
        ChildStatus.fromString(entity.status()));
  }

  public ChildModel fromResultSetToModel(final ResultSet rs) throws SQLException {
    return fromEntityToModel(fromResultSetToEntity(rs));
  }

  public List<ChildModel> fromResultSetToModelList(final ResultSet rs) throws SQLException {
    final List<ChildModel> children = new ArrayList<>();
    while (rs.next()) {
      children.add(fromResultSetToModel(rs));
    }
    return children;
  }
}
