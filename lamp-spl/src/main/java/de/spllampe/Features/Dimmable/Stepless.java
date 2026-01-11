package de.spllampe.Features.Dimmable;

public class Stepless {
    
    private boolean dimmable;
    public void steplessdimmable (boolean dimmable){
        this.dimmable = dimmable;
    }
    public boolean isDimmable(){
        return dimmable;
    }
    public String getDimmtype(){
        return "stepless";
    }
}
