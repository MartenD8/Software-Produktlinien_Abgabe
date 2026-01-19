package de.spllampe.app.cli;

import java.util.List;

import de.spllampe.core.ConfigurationValidator;
import de.spllampe.core.ConfigurationException;
import de.spllampe.core.Lamp;
import de.spllampe.core.LampConfiguration;
import de.spllampe.core.LampFactory;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;


public class CliApp {
    private final CliIO io;
    private final CliWizard wizard;
    private Lamp lamp;
    private LampConfiguration config;

    public CliApp(CliIO io) {
        if (io == null) {
            throw new IllegalArgumentException("io must not be null");
        }
        this.io = io;
        this.wizard = new CliWizard(io);
    }
    public void run() {
        io.println("Willkommen zum Lampen Konfigurator!");
        io.println("-------------------------------------");
        boolean running = true;
        while (running) { 
            printState();
            io.println("-----------------------------------");
            int sel = CliPrompts.askMenuSelection(
                    io,
                    "Hauptmenü:",
                    List.of(
                            "Neue Konfiguration (Wizard)",
                            "Konfiguration anzeigen",
                            "Konfiguration validieren",
                            "Lampe erzeugen",
                            "Lampen anzeigen",
                            "Session resetten ",
                            "Export",
                            "Beenden"
                    ),
                    1
            );
         switch (sel) {
                case 1 -> actionNewConfig();
                case 2 -> actionShowConfig();
                case 3 -> actionValidateConfig();
                case 4 -> actionCreateLamp();
                case 5 -> actionShowLampReport();
                case 6 -> actionReset();
                case 7 -> actionExport();
                case 8 -> running = false;
                default -> io.println("Ungültige Auswahl.");
            }
        if (running) pause();
        }

        io.println("");
        io.println("Programm beendet.");
    }

    private void printState() {
        io.println("Status:");
        io.println(" - Config: " + (config == null ? "keine" : "vorhanden"));
        io.println(" - Lampe:  " + (lamp == null ? "keine" : "erzeugt"));
    }

    private void actionNewConfig() {
        io.println("");
        config = wizard.run();
        lamp = null; 
        io.println("Neue Konfiguration gespeichert.");
    }

    private void actionShowConfig() {
        io.println("");
        io.println("Konfiguration");
        io.println("------------");
        if (config == null) {
            io.println("Keine Konfiguration vorhanden.");
            return;
        }
        io.println(config.toDisplayString());
    }

    private void actionValidateConfig() {
        io.println("Validierung");
        io.println("----------");
        if (config == null) {
            io.println("Keine Konfiguration vorhanden.");
            return;
        }

        try {
            ConfigurationValidator.validateOrThrow(config);
            io.println("Konfiguration ist gültig.");
        } catch (ConfigurationException ex) {
            io.println("Konfiguration ist ungültig.");
            io.println(ex.getMessage());
        }
    }

    private void actionCreateLamp() {
        io.println("");
        io.println("Lampe erzeugen");
        io.println("-------------");
        if (config == null) {
            io.println("Keine Konfiguration vorhanden.");
            return;
        }

        try {
            lamp = LampFactory.createLamp(config);
            io.println("Lampe wurde erzeugt.");
        } catch (RuntimeException ex) {
            io.println("Lampe konnte nicht erzeugt werden:");
            io.println(ex.getMessage());
            lamp = null;
        }
    }

    private void actionShowLampReport() {
        io.println("");
        io.println("Lamp-Report");
        io.println("----------");
        if (lamp == null) {
            io.println("Keine Lampe vorhanden. Bitte zuerst 'Lampe erzeugen' ausführen.");
            return;
        }

        lamp.printCapabilities();
    }

    private void actionReset() {
        io.println("");
        boolean ok = CliPrompts.askYesNo(io, "Wirklich zurücksetzen?", false);
        if (!ok) {
            io.println("Reset abgebrochen.");
            return;
        }
        config = null;
        lamp = null;
        io.println("Session zurückgesetzt ");
    }

    private void pause() {
        io.println("");
        io.print("ENTER um fortzufahren...");
        io.readLine();
    }

    private void actionExport() {
        io.println("");
        io.println("Export");
        io.println("---------------");

        if (config == null) {
            io.println("Keine Konfiguration vorhanden.");
            return;
        }

        String filename = "lamp_configuration.txt";
        try {
            Files.writeString(
                    Path.of(filename),
                    config.toDisplayString(),
                    StandardCharsets.UTF_8
            );
            io.println("Konfiguration wurde nach '" + filename + "' exportiert.");
        } catch (IOException ex) {
            io.println("Fehler beim Exportieren der Konfiguration:");
            io.println(ex.getMessage());
        }
    }
    
}
