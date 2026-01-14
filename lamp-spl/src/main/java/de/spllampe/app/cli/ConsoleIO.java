package de.spllampe.app.cli;

import java.util.Scanner;

public final class ConsoleIO implements CliIO {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void print(String s) {
        System.out.print(s);
    }

    @Override
    public void println(String s) {
        System.out.println(s);
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }
}
