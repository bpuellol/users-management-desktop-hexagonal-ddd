package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.cli.io.DishResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.controller.DishController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListDishesHandler implements OperationHandler {

  private final DishController dishController;
  private final DishResponsePrinter printer;

  @Override
  public void handle() {
    printer.printList(dishController.listAllDishes());
  }
}
