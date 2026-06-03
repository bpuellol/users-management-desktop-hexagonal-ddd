package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.cli.io.ChildResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.controller.ChildController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListChildrenHandler implements OperationHandler {

  private final ChildController childController;
  private final ChildResponsePrinter printer;

  @Override
  public void handle() {
    printer.printList(childController.listAllChildren());
  }
}
