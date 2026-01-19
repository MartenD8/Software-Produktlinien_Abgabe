package de.spllampe.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConfigurationValidator {
    
    private ConfigurationValidator() {

    }

    public static void validateOrThrow(LampConfiguration config) {
        Objects.requireNonNull(config, "config must not be null");

        List<String> errors = new ArrayList<>();

        if (config.getColorMode() == null) errors.add("colorMode must not be null");
        if (config.getDimmingMode() == null) errors.add("dimmingMode must not be null");
        if (config.getTimerMode() == null) errors.add("timerMode must not be null");

        ruleColorfulWithSteplessDimming(config, errors);
        ruleColorfulWithStepDimming(config, errors);
        ruleColorfulWithNoDimming(config, errors);

        if (!errors.isEmpty()) {
            throw new ConfigurationException(buildMessage(errors));
        }
    }
        
        

    private static void ruleColorfulWithNoDimming(LampConfiguration config, List<String> errors) {
        if (config.getColorMode() == LampConfiguration.ColorMode.Colorful
                && config.getDimmingMode() != LampConfiguration.DimmingMode.NO_DIMMING ) {
            errors.add("Colorful mode does only work without dimming.");
        }
    }

    private static void ruleColorfulWithStepDimming(LampConfiguration config, List<String> errors) {
        if (config.getColorMode() == LampConfiguration.ColorMode.Colorful
                && config.getDimmingMode() == LampConfiguration.DimmingMode.STEP) {
            errors.add("Colorful mode cannot use Step dimming.");
        }
    }

    private static void ruleColorfulWithSteplessDimming(LampConfiguration config, List<String> errors) {
        if (config.getColorMode() == LampConfiguration.ColorMode.Colorful
                && config.getDimmingMode() == LampConfiguration.DimmingMode.Stepless) {
            errors.add("Colorful mode cannot use Steplessdimming.");
        }
    }

        private static String buildMessage(List<String> errors) {
        StringBuilder sb = new StringBuilder("Invalid Lamp Configuration:\n");
        for (String error : errors) {
            sb.append("- ").append(error).append("\n");
        }
        return sb.toString();
    }
}