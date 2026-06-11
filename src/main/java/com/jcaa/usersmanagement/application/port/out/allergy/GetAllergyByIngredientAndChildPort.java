package com.jcaa.usersmanagement.application.port.out.allergy;

import com.jcaa.usersmanagement.domain.model.AllergyModel;
import com.jcaa.usersmanagement.domain.valueobject.ChildId;
import com.jcaa.usersmanagement.domain.valueobject.Ingredient;
import java.util.Optional;

public interface GetAllergyByIngredientAndChildPort {
  Optional<AllergyModel> getByIngredientAndChild(Ingredient ingredient, ChildId childId);
}
