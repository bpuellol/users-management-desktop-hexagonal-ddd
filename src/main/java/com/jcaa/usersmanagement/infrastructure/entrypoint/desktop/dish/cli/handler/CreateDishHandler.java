package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.cli.handler;

import com.jcaa.usersmanagement.domain.exception.DishAlreadyExistsException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.cli.io.DishResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.controller.DishController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.dto.CreateDishRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateDishHandler implements OperationHandler {

  private final DishController dishController;
  private final ConsoleIO console;
  private final DishResponsePrinter printer;

  @Override
  public void handle() {
    final String id          = console.readRequired("  ID (UUID)                        : ");
    final String name        = console.readRequired("  Dish name                         : ");
    final String ingredients = console.readRequired("  Ingredients (comma separated)     : ");

    try {
      final var created = dishController.createDish(
          new CreateDishRequest(id, name, ingredients));
      console.println("\n  Dish registered successfully.");
      printer.print(created);
    } catch (final DishAlreadyExistsException ex) {
      console.println("  Error: " + ex.getMessage());
    }
  }
}
