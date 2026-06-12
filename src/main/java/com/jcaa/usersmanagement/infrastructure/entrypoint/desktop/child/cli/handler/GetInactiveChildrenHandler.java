package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.controller.ChildController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.dto.ChildStatusResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * Handler — CEA #2:
 * "Consultar los niños que han sido dados de baja y la fecha en que se retiraron."
 */
@RequiredArgsConstructor
public final class GetInactiveChildrenHandler implements OperationHandler {

  private static final String SEP = "-".repeat(80);
  private static final String FMT = "  %-12s %-25s %-15s%n";

  private final ChildController childController;
  private final ConsoleIO console;

  @Override
  public void handle() {
    final List<ChildStatusResponse> results = childController.getInactiveChildren();
    if (results.isEmpty()) {
      console.println("  No inactive children found.");
      return;
    }
    console.println();
    console.println(SEP);
    console.printf("  CEA Query 2 — Inactive children and discharge date%n");
    console.println(SEP);
    console.printf(FMT, "Enrollment", "Full Name", "Discharge Date");
    console.println(SEP);
    results.forEach(r -> console.printf(FMT,
        r.enrollment(), r.fullName(), r.dischargeDate()));
    console.println(SEP);
    console.printf("  Total: %d inactive children%n", results.size());
  }
}
