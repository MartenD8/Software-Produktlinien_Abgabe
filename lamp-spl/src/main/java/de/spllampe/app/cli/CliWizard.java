package de.spllampe.app.cli;

import java.util.List;

import de.spllampe.core.LampConfiguration;
import de.spllampe.core.LampConfiguration.ColorMode;
import de.spllampe.core.LampConfiguration.DimmingMode;
import de.spllampe.core.LampConfiguration.TimerMode;

public final class CliWizard {

    private final CliIO io;

    public CliWizard(CliIO io) {
        if (io == null) {
            throw new IllegalArgumentException("io must not be null");
        }
        this.io = io;
    }

    public LampConfiguration run() {
        io.println("Lamp Product Configurator");
        io.println("-------------------------");

        ColorMode color = askColorMode();
        DimmingMode dimming = askDimmingMode();
        TimerMode timer = askTimerMode();

        LampConfiguration cfg = new LampConfiguration(color, dimming, timer);

        io.println("");
        io.println("Summary");
        io.println("-------");
        io.println(cfg.toDisplayString());

        boolean ok = CliPrompts.askYesNo(io, "Konfiguration so erstellen?", true);
        if (!ok) {
            io.println("");
            io.println("Abgebrochen. Wir starten den Wizard erneut.");
            io.println("");
            return run(); 
        }

        return cfg;
    }

    private ColorMode askColorMode() {
        io.println("");
        int sel = CliPrompts.askMenuSelection(
                io,
                "1) Farbmodus wählen:",
                List.of(
                        "WarmWhite",
                        "ColdWhite",
                        "Colorful"
                ),
                1
        );

        return switch (sel) {
            case 1 -> ColorMode.WARM_WHITE;
            case 2 -> ColorMode.COLD_WHITE;
            case 3 -> ColorMode.Colorful;
            default -> throw new IllegalStateException("Unexpected selection: " + sel);
        };
    }

    private DimmingMode askDimmingMode() {
        // IF Abfrage wenn Colorfül, dann nicht aufrufen, da nicht Dimmable
        io.println("");
        int sel = CliPrompts.askMenuSelection(
                io,
                "2) Dimmung wählen:",
                List.of(
                        "NoDimming",
                        "StepDimming",
                        "SteplessDimming"
                ),
                1
        );

        return switch (sel) {
            case 1 -> DimmingMode.NO_DIMMING;
            case 2 -> DimmingMode.STEP;
            case 3 -> DimmingMode.Stepless;
            default -> throw new IllegalStateException("Unexpected selection: " + sel);
        };
    }

    private TimerMode askTimerMode() {
        io.println("");
        int sel = CliPrompts.askMenuSelection(
                io,
                "3) Timer wählen:",
                List.of(
                        "NoTimer",
                        "Timer"
                ),
                1
        );

        return switch (sel) {
            case 1 -> TimerMode.NO_TIMER;
            case 2 -> TimerMode.Timer;
            default -> throw new IllegalStateException("Unexpected selection: " + sel);
        };
    }
}
