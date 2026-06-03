package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.cli;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.cli.handler.CreateChildHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.cli.handler.DeleteChildHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.cli.handler.FindChildByIdHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.cli.handler.ListChildrenHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.cli.handler.UpdateChildHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.cli.io.ChildResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.controller.ChildController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import jakarta.validation.ConstraintViolationException;
import java.util.Map;
import lombok.RequiredArgsConstructor;

/**
 * CLI del módulo de gestión de niños del Comedor Infantil (SIGCI).
 * Punto de entrada de presentación — delega cada operación a su handler correspondiente.
 */
@RequiredArgsConstructor
public final class ChildManagementCli {

  private static final String MENU_BORDER =
      "  ==========================================";

  private final ChildController childController;
  private final ConsoleIO console;

  public void start() {
    final ChildResponsePrinter printer = new ChildResponsePrinter(console);
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

  private Map<Integer, OperationHandler> buildHandlers(final ChildResponsePrinter printer) {
    return Map.of(
        1, new ListChildrenHandler(childController, printer),
        2, new FindChildByIdHandler(childController, console, printer),
        3, new CreateChildHandler(childController, console, printer),
        4, new UpdateChildHandler(childController, console, printer),
        5, new DeleteChildHandler(childController, console));
  }

  private void printMenu() {
    console.println();
    console.println(MENU_BORDER);
    console.println("    Children Menu  (SIGCI)");
    console.println(MENU_BORDER);
    console.println("    [1] List all children");
    console.println("    [2] Find child by ID");
    console.println("    [3] Register child");
    console.println("    [4] Update child");
    console.println("    [5] Delete child");
    console.println("    [0] Back to main menu");
    console.println(MENU_BORDER);
  }
}
