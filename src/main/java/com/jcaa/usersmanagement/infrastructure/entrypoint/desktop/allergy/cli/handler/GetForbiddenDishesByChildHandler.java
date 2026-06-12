package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.controller.AllergyController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.dto.ForbiddenDishResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * Handler — CEA #9:
 * "Mostrar las alergias registradas y los platos que NO pueden ser consumidos por cada niño."
 */
@RequiredArgsConstructor
public final class GetForbiddenDishesByChildHandler implements OperationHandler {

  private static final String SEP = "-".repeat(80);
  private static final String FMT = "  %-12s %-20s %-15s %-20s%n";

  private final AllergyController allergyController;
  private final ConsoleIO console;

  @Override
  public void handle() {
    final List<ForbiddenDishResponse> results = allergyController.getForbiddenDishesByChild();
    if (results.isEmpty()) {
      console.println("  No forbidden dishes found. Check that dishes and allergies are registered.");
      return;
    }
    console.println();
    console.println(SEP);
    console.printf("  CEA Query 9 — Forbidden dishes per child based on allergies%n");
    console.println(SEP);
    console.printf(FMT, "Enrollment", "Child Name", "Allergen", "Forbidden Dish");
    console.println(SEP);
    results.forEach(r -> console.printf(FMT,
        r.enrollment(), r.childFullName(), r.allergenIngredient(), r.dishName()));
    console.println(SEP);
    console.printf("  Total records: %d%n", results.size());
  }
}
