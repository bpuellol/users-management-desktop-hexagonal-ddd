package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.cli.handler;

import com.jcaa.usersmanagement.domain.exception.AllergyNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.cli.io.AllergyResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.controller.AllergyController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class FindAllergyByIdHandler implements OperationHandler {

  private final AllergyController allergyController;
  private final ConsoleIO console;
  private final AllergyResponsePrinter printer;

  @Override
  public void handle() {
    final String id = console.readRequired("  Allergy ID: ");
    try {
      printer.print(allergyController.findAllergyById(id));
    } catch (final AllergyNotFoundException ex) {
      console.println("  Error: " + ex.getMessage());
    }
  }
}
