package de.spllampe.app;


import de.spllampe.app.cli.CliWizard;
import de.spllampe.app.cli.ConsoleIO;
import de.spllampe.core.LampConfiguration;

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
        io.println(config.toDisplayString());
    }
}
