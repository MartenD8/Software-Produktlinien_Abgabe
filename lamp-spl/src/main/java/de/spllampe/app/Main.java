package de.spllampe.app;


import de.spllampe.app.cli.CliWizard;
import de.spllampe.app.cli.ConsoleIO;
import de.spllampe.core.LampConfiguration;
import de.spllampe.core.LampFactory;
import de.spllampe.core.Lamp;

public class Main 
{
    public static void main(String[] args) {
        ConsoleIO io = new ConsoleIO();
        CliWizard wizard = new CliWizard(io);

        LampConfiguration config = wizard.run();

        // Config auslesen und dann Lamp-Objekt erstellen und Features an machen.
        //Am Ende nicht config ausgeben sondern das Lamp-Objekt 

        io.println("");
        io.println("Konfiguration erstellt:");
        //io.println(config.toDisplayString());
        Lamp lamp = LampFactory.createLamp(config);
        lamp.printCapabilities();
    }
}
