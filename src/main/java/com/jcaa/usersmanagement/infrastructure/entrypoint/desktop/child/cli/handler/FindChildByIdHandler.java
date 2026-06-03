package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.cli.handler;

import com.jcaa.usersmanagement.domain.exception.ChildNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.cli.io.ChildResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.controller.ChildController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class FindChildByIdHandler implements OperationHandler {

  private final ChildController childController;
  private final ConsoleIO console;
  private final ChildResponsePrinter printer;

  @Override
  public void handle() {
    final String id = console.readRequired("  Child ID: ");
    try {
      printer.print(childController.findChildById(id));
    } catch (final ChildNotFoundException ex) {
      console.println("  Error: " + ex.getMessage());
    }
  }
}
