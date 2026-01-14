package de.spllampe.app.cli;

import java.util.List;

public final class CliPrompts {

    private CliPrompts() {
    }

    public static int askMenuSelection(
            CliIO io,
            String title,
            List<String> options,
            int defaultIndexOneBased
    ) {
        if (options == null || options.isEmpty()) {
            throw new IllegalArgumentException("options must not be empty");
        }
        if (defaultIndexOneBased < 1 || defaultIndexOneBased > options.size()) {
            throw new IllegalArgumentException("defaultIndexOneBased out of range");
        }

        io.println(title);
        for (int i = 0; i < options.size(); i++) {
            io.println("  [" + (i + 1) + "] " + options.get(i));
        }

        while (true) {
            io.print("Auswahl (Standard " + defaultIndexOneBased + "): ");
            String input = io.readLine().trim();

            if (input.isEmpty()) {
                return defaultIndexOneBased;
            }

            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= options.size()) {
                    return choice;
                }
            } catch (NumberFormatException ignored) {
                // fall through
            }

            io.println("Ungültige Eingabe. Bitte Zahl 1.." + options.size() + " eingeben (oder Enter für Standard).");
        }
    }

    public static boolean askYesNo(CliIO io, String question, boolean defaultYes) {
        String hint = defaultYes ? "[Y/n]" : "[y/N]";
        while (true) {
            io.print(question + " " + hint + ": ");
            String input = io.readLine().trim().toLowerCase();

            if (input.isEmpty()) {
                return defaultYes;
            }
            if (input.equals("y") || input.equals("yes") || input.equals("j") || input.equals("ja")) {
                return true;
            }
            if (input.equals("n") || input.equals("no") || input.equals("nein")) {
                return false;
            }

            io.println("Bitte y/n eingeben (oder Enter für Standard).");
        }
    }
}
