package com.jcaa.usersmanagement.application.service.allergy;

import com.jcaa.usersmanagement.application.port.in.allergy.CreateAllergyUseCase;
import com.jcaa.usersmanagement.application.port.out.allergy.GetAllergyByIngredientAndChildPort;
import com.jcaa.usersmanagement.application.port.out.allergy.SaveAllergyPort;
import com.jcaa.usersmanagement.application.service.allergy.dto.command.CreateAllergyCommand;
import com.jcaa.usersmanagement.application.service.allergy.mapper.AllergyApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.AllergyAlreadyExistsException;
import com.jcaa.usersmanagement.domain.model.AllergyModel;
import com.jcaa.usersmanagement.domain.valueobject.ChildId;
import com.jcaa.usersmanagement.domain.valueobject.Ingredient;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateAllergyService implements CreateAllergyUseCase {

  private final SaveAllergyPort saveAllergyPort;
  private final GetAllergyByIngredientAndChildPort getAllergyByIngredientAndChildPort;
  private final Validator validator;

  @Override
  public AllergyModel execute(final CreateAllergyCommand command) {
    validate(command);
    ensureIngredientIsNotDuplicated(
        new Ingredient(command.ingredient()), new ChildId(command.childId()));
    final AllergyModel allergy = AllergyApplicationMapper.fromCreateCommandToModel(command);
    return saveAllergyPort.save(allergy);
  }

  private void validate(final CreateAllergyCommand command) {
    final Set<ConstraintViolation<CreateAllergyCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private void ensureIngredientIsNotDuplicated(
      final Ingredient ingredient, final ChildId childId) {
    getAllergyByIngredientAndChildPort
        .getByIngredientAndChild(ingredient, childId)
        .ifPresent(ignored -> {
          throw AllergyAlreadyExistsException.becauseIngredientAlreadyExists(
              ingredient.value(), childId.value());
        });
  }
}
