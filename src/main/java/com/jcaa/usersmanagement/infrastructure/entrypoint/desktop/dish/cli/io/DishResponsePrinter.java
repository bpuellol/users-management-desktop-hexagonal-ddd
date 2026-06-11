package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.cli.io;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.dto.DishResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DishResponsePrinter {

  private static final String SEPARATOR = "-".repeat(60);
  private static final String ROW_FORMAT = "  %-15s : %s%n";

  private final ConsoleIO console;

  public void print(final DishResponse dish) {
    console.println(SEPARATOR);
    console.printf(ROW_FORMAT, "ID",          dish.id());
    console.printf(ROW_FORMAT, "Name",        dish.name());
    console.printf(ROW_FORMAT, "Ingredients", dish.ingredients());
    console.println(SEPARATOR);
  }

  public void printList(final List<DishResponse> dishes) {
    if (dishes.isEmpty()) {
      console.println("  No dishes found.");
      return;
    }
    console.printf("%n  Total: %d dish(es)%n", dishes.size());
    dishes.forEach(this::print);
  }
}
