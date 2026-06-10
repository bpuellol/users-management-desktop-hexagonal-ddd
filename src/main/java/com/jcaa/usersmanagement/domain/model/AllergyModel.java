package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.enums.AllergySeverity;
import com.jcaa.usersmanagement.domain.valueobject.AllergyId;
import com.jcaa.usersmanagement.domain.valueobject.ChildId;
import com.jcaa.usersmanagement.domain.valueobject.Ingredient;
import lombok.Value;

/**
 * Entidad de dominio que representa la alergia de un niño inscrito en el comedor infantil.
 * Contiene únicamente lógica de negocio pura, sin dependencias de frameworks.
 */
@Value
public class AllergyModel {

  AllergyId      id;
  ChildId        childId;
  Ingredient     ingredient;
  AllergySeverity severity;
  String         notes;   // null cuando no hay observaciones adicionales

  /** Factory method: registra una nueva alergia con severidad por defecto MILD. */
  public static AllergyModel create(
      final AllergyId      id,
      final ChildId        childId,
      final Ingredient     ingredient,
      final AllergySeverity severity,
      final String         notes) {
    return new AllergyModel(id, childId, ingredient, severity, notes);
  }

  /** Actualiza la severidad de la alergia. */
  public AllergyModel withSeverity(final AllergySeverity newSeverity) {
    return new AllergyModel(id, childId, ingredient, newSeverity, notes);
  }

  /** Actualiza las notas de la alergia. */
  public AllergyModel withNotes(final String newNotes) {
    return new AllergyModel(id, childId, ingredient, severity, newNotes);
  }
}
