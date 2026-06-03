package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.cli.io;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.dto.ChildResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ChildResponsePrinter {

  private static final String SEPARATOR = "-".repeat(56);
  private static final String ROW_FORMAT = "  %-15s : %s%n";

  private final ConsoleIO console;

  public void print(final ChildResponse child) {
    console.println(SEPARATOR);
    console.printf(ROW_FORMAT, "ID",            child.id());
    console.printf(ROW_FORMAT, "Enrollment",    child.enrollment());
    console.printf(ROW_FORMAT, "Full Name",     child.fullName());
    console.printf(ROW_FORMAT, "Birth Date",    child.birthDate());
    console.printf(ROW_FORMAT, "Admission",     child.admissionDate());
    console.printf(ROW_FORMAT, "Discharge",     child.dischargeDate());
    console.printf(ROW_FORMAT, "Status",        child.status());
    console.println(SEPARATOR);
  }

  public void printList(final List<ChildResponse> children) {
    if (children.isEmpty()) {
      console.println("  No children found.");
      return;
    }
    console.printf("%n  Total: %d child(ren)%n", children.size());
    children.forEach(this::print);
  }
}
