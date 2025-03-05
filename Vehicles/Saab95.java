package Vehicles;

import java.awt.*;

public class Saab95 extends Vehicle implements Turbo{

    private boolean turboOn;
    
    public Saab95() {
        super(2, 125, Color.red, "Saab95", Weight.LIGHT);
        turboOn = false;
    }
    @Override
    public void setTurboOn(){
	    turboOn = true;
    }

    @Override
    public void setTurboOff(){
	    turboOn = false;
    }

    @Override
    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 5;
        return getEnginePower() * 0.01 * turbo;
    }
}
