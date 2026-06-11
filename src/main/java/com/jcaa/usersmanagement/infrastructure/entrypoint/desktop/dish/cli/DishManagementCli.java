package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.cli;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.cli.handler.CreateDishHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.cli.handler.DeleteDishHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.cli.handler.FindDishByIdHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.cli.handler.ListDishesHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.cli.handler.UpdateDishHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.cli.io.DishResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.controller.DishController;
import jakarta.validation.ConstraintViolationException;
import java.util.Map;
import lombok.RequiredArgsConstructor;

/**
 * CLI del módulo de gestión de platos del Comedor Infantil (SIGCI).
 */
@RequiredArgsConstructor
public final class DishManagementCli {

  private static final String BORDER =
      "  ==========================================";

  private final DishController dishController;
  private final ConsoleIO console;

  public void start() {
    final DishResponsePrinter printer = new DishResponsePrinter(console);
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

  private Map<Integer, OperationHandler> buildHandlers(final DishResponsePrinter printer) {
    return Map.of(
        1, new ListDishesHandler(dishController, printer),
        2, new FindDishByIdHandler(dishController, console, printer),
        3, new CreateDishHandler(dishController, console, printer),
        4, new UpdateDishHandler(dishController, console, printer),
        5, new DeleteDishHandler(dishController, console));
  }

  private void printMenu() {
    console.println();
    console.println(BORDER);
    console.println("    Dishes Menu  (SIGCI)");
    console.println(BORDER);
    console.println("    [1] List all dishes");
    console.println("    [2] Find dish by ID");
    console.println("    [3] Register dish");
    console.println("    [4] Update dish");
    console.println("    [5] Delete dish");
    console.println("    [0] Back to main menu");
    console.println(BORDER);
  }
}
