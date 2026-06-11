package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.cli.io;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.dto.AllergyResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class AllergyResponsePrinter {

  private static final String SEPARATOR = "-".repeat(56);
  private static final String ROW_FORMAT = "  %-15s : %s%n";

  private final ConsoleIO console;

  public void print(final AllergyResponse allergy) {
    console.println(SEPARATOR);
    console.printf(ROW_FORMAT, "ID",         allergy.id());
    console.printf(ROW_FORMAT, "Child ID",   allergy.childId());
    console.printf(ROW_FORMAT, "Ingredient", allergy.ingredient());
    console.printf(ROW_FORMAT, "Severity",   allergy.severity());
    console.printf(ROW_FORMAT, "Notes",      allergy.notes());
    console.println(SEPARATOR);
  }

  public void printList(final List<AllergyResponse> allergies) {
    if (allergies.isEmpty()) {
      console.println("  No allergies found.");
      return;
    }
    console.printf("%n  Total: %d allerg(ies)%n", allergies.size());
    allergies.forEach(this::print);
  }
}
