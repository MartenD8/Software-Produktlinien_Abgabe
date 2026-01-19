package de.spllampe.core;

import de.spllampe.Features.Color.*;
import de.spllampe.Features.Dimmable.*;
import de.spllampe.Features.Timer.*;

public class LampFactory {

    public static Lamp createLamp(LampConfiguration config) {

        ColorFeature color = switch (config.getColorMode()) {
            case WARM_WHITE -> new WarmWhite();
            case COLD_WHITE -> new ColdWhite();
            case Colorful -> new Colorful();
        };

        DimmableFeature dimmable = switch (config.getDimmingMode()) {
            case NO_DIMMING -> new NoDimming();
            case STEP -> new Steps();
            case Stepless -> new Stepless();
        };

        TimerFeature timer = switch (config.getTimerMode()) {
            case Timer -> new Timer();
            case NO_TIMER -> new NoTimer();
        };
        return new Lamp(color, dimmable, timer);
    }
}
