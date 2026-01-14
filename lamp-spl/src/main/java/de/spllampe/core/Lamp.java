package de.spllampe.core;

import java.util.Objects;

import de.spllampe.Features.Color.ColorFeature;
import de.spllampe.Features.Dimmable.DimmableFeature;
import de.spllampe.Features.Timer.TimerFeature;

public class Lamp {
    private final ColorFeature color;
    private final DimmableFeature dimming;
    private final TimerFeature timer;    

    public Lamp (ColorFeature color, DimmableFeature dimming, TimerFeature timer){
        this.color = Objects.requireNonNull(color);
        this.dimming = Objects.requireNonNull(dimming);
        this.timer = Objects.requireNonNull(timer);
    }

    public void printCapabilities() {
        System.out.println("Lamp configuration:");
        System.out.println(" - Color:   " + color.getModeName());
        System.out.println(" - Dimming: " + dimming.getDimmingName());
        System.out.println(" - Timer:   " + timer.getTimerName());
    }
}
