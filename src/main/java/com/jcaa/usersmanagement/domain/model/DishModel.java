package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.valueobject.DishId;
import com.jcaa.usersmanagement.domain.valueobject.DishName;
import lombok.Value;

/**
 * Entidad de dominio que representa un plato del menú del comedor infantil.
 * Los ingredientes se almacenan como texto separado por comas para simplificar
 * la consulta de compatibilidad con alergias.
 */
@Value
public class DishModel {

  DishId   id;
  DishName name;
  String   ingredients; // ej: "leche, huevo, trigo, maní"

  public static DishModel create(
      final DishId   id,
      final DishName name,
      final String   ingredients) {
    return new DishModel(id, name, ingredients);
  }

  public DishModel withName(final DishName newName) {
    return new DishModel(id, newName, ingredients);
  }

  public DishModel withIngredients(final String newIngredients) {
    return new DishModel(id, name, newIngredients);
  }
}
