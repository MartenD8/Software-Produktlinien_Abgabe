package de.spllampe.app;


import de.spllampe.app.cli.*;
import de.spllampe.core.LampConfiguration;
import de.spllampe.core.LampFactory;
import de.spllampe.core.ConfigurationValidator;
import de.spllampe.core.Lamp;
import de.spllampe.core.ConfigurationException;

public class Main 
{
    public static void main(String[] args) {
        ConsoleIO io = new ConsoleIO();
        boolean UI = args.length == 0 || !"wizard".equalsIgnoreCase(args[0]);

        if (UI){
            new CliApp(io).run();
            return;
        }
        
        CliWizard wizard = new CliWizard(io);

        LampConfiguration config = wizard.run();

        try {
            ConfigurationValidator.validateOrThrow(config);
            io.println("");
        io.println("Konfiguration erstellt:");
        //io.println(config.toDisplayString());
        Lamp lamp = LampFactory.createLamp(config);
        lamp.printCapabilities();
        } catch (ConfigurationException e) {
            io.println("Fehler: " + e.getMessage());
        }
        
    }
}
