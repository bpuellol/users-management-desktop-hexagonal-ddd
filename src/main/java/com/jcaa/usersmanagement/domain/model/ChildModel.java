package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.enums.ChildStatus;
import com.jcaa.usersmanagement.domain.valueobject.ChildId;
import com.jcaa.usersmanagement.domain.valueobject.ChildName;
import com.jcaa.usersmanagement.domain.valueobject.Enrollment;
import lombok.Value;

/**
 * Entidad de dominio que representa a un niño inscrito en la guardería.
 * Contiene únicamente lógica de negocio pura, sin dependencias de frameworks.
 */
@Value
public class ChildModel {

  ChildId     id;
  Enrollment  enrollment;
  ChildName   fullName;
  String      birthDate;
  String      admissionDate;
  String      dischargeDate;   // null cuando el niño sigue activo
  ChildStatus status;

  /** Factory method: crea un niño recién inscrito (siempre en estado ACTIVE). */
  public static ChildModel create(
      final ChildId    id,
      final Enrollment enrollment,
      final ChildName  fullName,
      final String     birthDate,
      final String     admissionDate) {
    return new ChildModel(id, enrollment, fullName, birthDate, admissionDate, null, ChildStatus.ACTIVE);
  }

  /** Da de baja al niño registrando la fecha de baja. */
  public ChildModel discharge(final String dischargeDate) {
    return new ChildModel(id, enrollment, fullName, birthDate, admissionDate,
        dischargeDate, ChildStatus.INACTIVE);
  }

  /** Reactiva al niño (por ejemplo, si fue dado de baja por error). */
  public ChildModel reactivate() {
    return new ChildModel(id, enrollment, fullName, birthDate, admissionDate,
        null, ChildStatus.ACTIVE);
  }

  public boolean isActive() {
    return ChildStatus.ACTIVE.equals(status);
  }
}
