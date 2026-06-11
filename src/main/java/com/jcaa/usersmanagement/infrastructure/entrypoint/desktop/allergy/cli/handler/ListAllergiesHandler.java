package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.cli.io.AllergyResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.controller.AllergyController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListAllergiesHandler implements OperationHandler {

  private final AllergyController allergyController;
  private final AllergyResponsePrinter printer;

  @Override
  public void handle() {
    printer.printList(allergyController.listAllAllergies());
  }
}
