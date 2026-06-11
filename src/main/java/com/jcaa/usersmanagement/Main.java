package com.jcaa.usersmanagement;

import com.jcaa.usersmanagement.infrastructure.config.DependencyContainer;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.cli.AllergyManagementCli;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.cli.ChildManagementCli;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.UserManagementCli;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import java.util.Scanner;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dish.cli.DishManagementCli;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Main {

  private static final Logger log = LoggerFactory.getLogger(Main.class);

  private static final String BANNER = """
      ==========================================
           SIGCI - Comedor Infantil
      ==========================================""";

  public static void main(final String[] args) {
    log.info("Starting SIGCI...");
    final DependencyContainer container = new DependencyContainer();

    try (final Scanner scanner = new Scanner(System.in)) {
      final ConsoleIO console = new ConsoleIO(scanner, System.out);
      runMainLoop(container, console);
    }
  }

  private static void runMainLoop(final DependencyContainer container, final ConsoleIO console) {
    boolean running = true;
    while (running) {
      console.println(BANNER);
      console.println("  [1] Users Module");
      console.println("  [2] Children Module");
      console.println("  [3] Allergzies Module");
      console.println("  [4] Dishes Module");
      console.println("  [0] Exit");

      final int choice = console.readInt("\n  Option: ");
      switch (choice) {
        case 1 -> new UserManagementCli(container.userController(), console).start();
        case 2 -> new ChildManagementCli(container.childController(), console).start();
        case 3 -> new AllergyManagementCli(container.allergyController(), console).start();
        case 4 -> new DishManagementCli(container.dishController(), console).start();
        case 0 -> {
          console.println("\n  Goodbye!\n");
          running = false;
        }
        default -> console.println("  Invalid option.");
      }
    }
  }
}
