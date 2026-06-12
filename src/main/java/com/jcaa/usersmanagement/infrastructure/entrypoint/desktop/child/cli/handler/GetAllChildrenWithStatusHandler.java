package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.controller.ChildController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.dto.ChildStatusResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * Handler — CEA #1:
 * "Listar todos los niños inscritos en la guardería, junto con su estado (activo/baja)."
 */
@RequiredArgsConstructor
public final class GetAllChildrenWithStatusHandler implements OperationHandler {

  private static final String SEP = "-".repeat(80);
  private static final String FMT = "  %-12s %-25s %-12s %-10s%n";

  private final ChildController childController;
  private final ConsoleIO console;

  @Override
  public void handle() {
    final List<ChildStatusResponse> results = childController.getAllChildrenWithStatus();
    if (results.isEmpty()) {
      console.println("  No children found.");
      return;
    }
    console.println();
    console.println(SEP);
    console.printf("  CEA Query 1 — All children with enrollment status%n");
    console.println(SEP);
    console.printf(FMT, "Enrollment", "Full Name", "Admission", "Status");
    console.println(SEP);
    results.forEach(r -> console.printf(FMT,
        r.enrollment(), r.fullName(), r.admissionDate(), r.status()));
    console.println(SEP);
    console.printf("  Total: %d children%n", results.size());
  }
}
