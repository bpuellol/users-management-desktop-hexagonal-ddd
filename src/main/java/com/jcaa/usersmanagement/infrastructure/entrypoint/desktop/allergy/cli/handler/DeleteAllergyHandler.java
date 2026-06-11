package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.cli.handler;

import com.jcaa.usersmanagement.domain.exception.AllergyNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.controller.AllergyController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteAllergyHandler implements OperationHandler {

  private final AllergyController allergyController;
  private final ConsoleIO console;

  @Override
  public void handle() {
    final String id = console.readRequired("  Allergy ID to delete: ");
    try {
      allergyController.deleteAllergy(id);
      console.println("  Allergy deleted successfully.");
    } catch (final AllergyNotFoundException ex) {
      console.println("  Error: " + ex.getMessage());
    }
  }
}
