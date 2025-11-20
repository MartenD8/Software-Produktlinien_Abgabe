package de.spllampe.Main;

import java.security.PrivateKey;

public class Lamp {
    private boolean isOn;
    private String color;
    private boolean dimable;
    private boolean dimtype;
    private boolean timeable;



    public void turnOn();
    public void turnOff();
    public boolean isOn();
    public abstract String getColor();
    public abstract String setColor();
    public abstract boolean getDimable();
    public abstract boolean setDimable();
    public abstract boolean getDimtype();
    public abstract boolean getDimtype();
    public abstract boolean getTimeable();
    public abstract boolean getTimeable();

    public String getColor(){
        return Color;
    }

    public void setColor (String color){
        this.color = color;
    }



}
