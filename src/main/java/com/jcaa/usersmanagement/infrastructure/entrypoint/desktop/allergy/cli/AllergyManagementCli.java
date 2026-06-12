package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.cli;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.cli.handler.CreateAllergyHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.cli.handler.DeleteAllergyHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.cli.handler.FindAllergyByIdHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.cli.handler.GetChildrenWithAllergiesHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.cli.handler.GetForbiddenDishesByChildHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.cli.handler.ListAllergiesHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.cli.handler.UpdateAllergyHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.cli.io.AllergyResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.controller.AllergyController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import jakarta.validation.ConstraintViolationException;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class AllergyManagementCli {

  private static final String BORDER =
      "  ==========================================";

  private final AllergyController allergyController;
  private final ConsoleIO console;

  public void start() {
    final AllergyResponsePrinter printer = new AllergyResponsePrinter(console);
    final Map<Integer, OperationHandler> handlers = buildHandlers(printer);

    boolean running = true;
    while (running) {
      printMenu();
      final int choice = console.readInt("\n  Option: ");
      switch (choice) {
        case 1 -> handlers.get(1).handle();
        case 2 -> handlers.get(2).handle();
        case 3 -> safeExecute(() -> handlers.get(3).handle());
        case 4 -> safeExecute(() -> handlers.get(4).handle());
        case 5 -> handlers.get(5).handle();
        case 6 -> handlers.get(6).handle();
        case 7 -> handlers.get(7).handle();
        case 0 -> {
          console.println("\n  Returning to main menu...\n");
          running = false;
        }
        default -> console.println("  Invalid option. Please try again.");
      }
    }
  }

  private void safeExecute(final Runnable operation) {
    try {
      operation.run();
    } catch (final ConstraintViolationException ex) {
      console.println("  Validation errors:");
      ex.getConstraintViolations()
          .forEach(v -> console.println("    - " + v.getMessage()));
    } catch (final RuntimeException ex) {
      console.println("  Error: " + ex.getMessage());
    }
  }

  private Map<Integer, OperationHandler> buildHandlers(final AllergyResponsePrinter printer) {
    return Map.of(
        1, new ListAllergiesHandler(allergyController, printer),
        2, new FindAllergyByIdHandler(allergyController, console, printer),
        3, new CreateAllergyHandler(allergyController, console, printer),
        4, new UpdateAllergyHandler(allergyController, console, printer),
        5, new DeleteAllergyHandler(allergyController, console),
        6, new GetChildrenWithAllergiesHandler(allergyController, console),
        7, new GetForbiddenDishesByChildHandler(allergyController, console));
  }

  private void printMenu() {
    console.println();
    console.println(BORDER);
    console.println("    Allergies Menu  (SIGCI)");
    console.println(BORDER);
    console.println("    ── CRUDL ──────────────────────────");
    console.println("    [1] List all allergies");
    console.println("    [2] Find allergy by ID");
    console.println("    [3] Register allergy");
    console.println("    [4] Update allergy");
    console.println("    [5] Delete allergy");
    console.println("    ── CEA Queries ─────────────────────");
    console.println("    [6] CEA Q7 — Children with allergies");
    console.println("    [7] CEA Q9 — Forbidden dishes by child");
    console.println("    [0] Back to main menu");
    console.println(BORDER);
  }
}
