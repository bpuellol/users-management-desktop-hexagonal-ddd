package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.cli.handler;

import com.jcaa.usersmanagement.domain.exception.AllergyNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.cli.io.AllergyResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.controller.AllergyController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.dto.UpdateAllergyRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UpdateAllergyHandler implements OperationHandler {

  private final AllergyController allergyController;
  private final ConsoleIO console;
  private final AllergyResponsePrinter printer;

  @Override
  public void handle() {
    final String id         = console.readRequired("  Allergy ID                             : ");
    final String ingredient = console.readRequired("  New ingredient                         : ");
    final String severity   = console.readRequired("  Severity (MILD / MODERATE / SEVERE)    : ");
    final String notes      = console.readOptional( "  Notes (blank = none)                   : ");

    try {
      final var updated = allergyController.updateAllergy(
          new UpdateAllergyRequest(id, ingredient, severity,
              notes.isBlank() ? null : notes));
      console.println("\n  Allergy updated successfully.");
      printer.print(updated);
    } catch (final AllergyNotFoundException ex) {
      console.println("  Error: " + ex.getMessage());
    }
  }
}
