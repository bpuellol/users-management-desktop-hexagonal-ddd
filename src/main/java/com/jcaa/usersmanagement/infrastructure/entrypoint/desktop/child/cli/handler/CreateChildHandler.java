package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.cli.handler;

import com.jcaa.usersmanagement.domain.exception.ChildAlreadyExistsException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.cli.io.ChildResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.controller.ChildController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.dto.CreateChildRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateChildHandler implements OperationHandler {

  private final ChildController childController;
  private final ConsoleIO console;
  private final ChildResponsePrinter printer;

  @Override
  public void handle() {
    final String id            = console.readRequired("  ID (UUID)                    : ");
    final String enrollment    = console.readRequired("  Enrollment (matricula)        : ");
    final String fullName      = console.readRequired("  Full name                     : ");
    final String birthDate     = console.readRequired("  Birth date    (YYYY-MM-DD)    : ");
    final String admissionDate = console.readRequired("  Admission date (YYYY-MM-DD)   : ");

    try {
      final var created = childController.createChild(
          new CreateChildRequest(id, enrollment, fullName, birthDate, admissionDate));
      console.println("\n  Child created successfully.");
      printer.print(created);
    } catch (final ChildAlreadyExistsException ex) {
      console.println("  Error: " + ex.getMessage());
    }
  }
}
