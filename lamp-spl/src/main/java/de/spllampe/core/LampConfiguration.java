package de.spllampe.core;

import java.util.Objects;

public final class LampConfiguration {

    public enum ColorMode {
        WARM_WHITE,
        COLD_WHITE,
        Colorful
    }

    public enum DimmingMode {
        NO_DIMMING,
        STEP,
        Stepless
    }

    public enum TimerMode {
        NO_TIMER,
        Timer
    }

    private final ColorMode colorMode;
    private final DimmingMode dimmingMode;
    private final TimerMode timerMode;

    public LampConfiguration(ColorMode colorMode, DimmingMode dimmingMode, TimerMode timerMode) {
        this.colorMode = Objects.requireNonNull(colorMode, "colorMode must not be null");
        this.dimmingMode = Objects.requireNonNull(dimmingMode, "dimmingMode must not be null");
        this.timerMode = Objects.requireNonNull(timerMode, "timerMode must not be null");
    }

    public ColorMode getColorMode() {
        return colorMode;
    }

    public DimmingMode getDimmingMode() {
        return dimmingMode;
    }

    public TimerMode getTimerMode() {
        return timerMode;
    }

    public String toDisplayString() {
        return "Color: " + colorMode +
               "\nDimming: " + dimmingMode +
               "\nTimer: " + timerMode;
    }

    @Override
    public String toString() {
        return "LampConfiguration{" +
                "colorMode=" + colorMode +
                ", dimmingMode=" + dimmingMode +
                ", timerMode=" + timerMode +
                '}';
    }
}
