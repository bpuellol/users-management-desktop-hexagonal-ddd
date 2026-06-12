package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.controller.AllergyController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.dto.ChildAllergyResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * Handler — CEA #7:
 * "Consultar los niños con alergias registradas y los ingredientes que deben evitar."
 */
@RequiredArgsConstructor
public final class GetChildrenWithAllergiesHandler implements OperationHandler {

  private static final String SEP = "-".repeat(80);
  private static final String FMT = "  %-12s %-22s %-20s %-10s%n";

  private final AllergyController allergyController;
  private final ConsoleIO console;

  @Override
  public void handle() {
    final List<ChildAllergyResponse> results = allergyController.getChildrenWithAllergies();
    if (results.isEmpty()) {
      console.println("  No children with registered allergies found.");
      return;
    }
    console.println();
    console.println(SEP);
    console.printf("  CEA Query 7 — Children with allergies and forbidden ingredients%n");
    console.println(SEP);
    console.printf(FMT, "Enrollment", "Child Name", "Ingredient", "Severity");
    console.println(SEP);
    results.forEach(r -> console.printf(FMT,
        r.enrollment(), r.childFullName(), r.ingredient(), r.severity()));
    console.println(SEP);
    console.printf("  Total records: %d%n", results.size());
  }
}
