package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.cli.handler;

import com.jcaa.usersmanagement.domain.exception.AllergyAlreadyExistsException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.cli.io.AllergyResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.controller.AllergyController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.dto.CreateAllergyRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateAllergyHandler implements OperationHandler {

  private final AllergyController allergyController;
  private final ConsoleIO console;
  private final AllergyResponsePrinter printer;

  @Override
  public void handle() {
    final String id         = console.readRequired("  ID (UUID)                              : ");
    final String childId    = console.readRequired("  Child ID (UUID)                        : ");
    final String ingredient = console.readRequired("  Ingredient                             : ");
    final String severity   = console.readRequired("  Severity (MILD / MODERATE / SEVERE)    : ");
    final String notes      = console.readOptional( "  Notes (blank = none)                   : ");

    try {
      final var created = allergyController.createAllergy(
          new CreateAllergyRequest(id, childId, ingredient, severity,
              notes.isBlank() ? null : notes));
      console.println("\n  Allergy registered successfully.");
      printer.print(created);
    } catch (final AllergyAlreadyExistsException ex) {
      console.println("  Error: " + ex.getMessage());
    }
  }
}
