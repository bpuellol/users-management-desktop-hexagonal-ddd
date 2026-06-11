package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.cli.handler;

import com.jcaa.usersmanagement.domain.exception.DishNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.cli.io.DishResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.controller.DishController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.dto.UpdateDishRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UpdateDishHandler implements OperationHandler {

  private final DishController dishController;
  private final ConsoleIO console;
  private final DishResponsePrinter printer;

  @Override
  public void handle() {
    final String id          = console.readRequired("  Dish ID                           : ");
    final String name        = console.readRequired("  New name                          : ");
    final String ingredients = console.readRequired("  New ingredients (comma separated) : ");

    try {
      final var updated = dishController.updateDish(
          new UpdateDishRequest(id, name, ingredients));
      console.println("\n  Dish updated successfully.");
      printer.print(updated);
    } catch (final DishNotFoundException ex) {
      console.println("  Error: " + ex.getMessage());
    }
  }
}
