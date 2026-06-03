package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.cli.handler;

import com.jcaa.usersmanagement.domain.exception.ChildNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.cli.io.ChildResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.controller.ChildController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.dto.UpdateChildRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UpdateChildHandler implements OperationHandler {

  private final ChildController childController;
  private final ConsoleIO console;
  private final ChildResponsePrinter printer;

  @Override
  public void handle() {
    final String id            = console.readRequired("  Child ID                      : ");
    final String fullName      = console.readRequired("  New full name                 : ");
    final String birthDate     = console.readRequired("  Birth date    (YYYY-MM-DD)    : ");
    final String admissionDate = console.readRequired("  Admission date (YYYY-MM-DD)   : ");
    final String dischargeDate = console.readOptional("  Discharge date (YYYY-MM-DD, blank=none): ");
    final String status        = console.readRequired("  Status (ACTIVE / INACTIVE)    : ");

    try {
      final var updated = childController.updateChild(
          new UpdateChildRequest(id, fullName, birthDate, admissionDate,
              dischargeDate.isBlank() ? null : dischargeDate, status));
      console.println("\n  Child updated successfully.");
      printer.print(updated);
    } catch (final ChildNotFoundException ex) {
      console.println("  Error: " + ex.getMessage());
    }
  }
}
