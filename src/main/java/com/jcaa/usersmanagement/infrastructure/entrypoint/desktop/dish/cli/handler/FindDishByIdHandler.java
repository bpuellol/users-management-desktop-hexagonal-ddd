package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.cli.handler;

import com.jcaa.usersmanagement.domain.exception.DishNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.cli.io.DishResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.controller.DishController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class FindDishByIdHandler implements OperationHandler {

  private final DishController dishController;
  private final ConsoleIO console;
  private final DishResponsePrinter printer;

  @Override
  public void handle() {
    final String id = console.readRequired("  Dish ID: ");
    try {
      printer.print(dishController.findDishById(id));
    } catch (final DishNotFoundException ex) {
      console.println("  Error: " + ex.getMessage());
    }
  }
}
