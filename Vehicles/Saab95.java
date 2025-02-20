package Vehicles;

import java.awt.*;

public class Saab95 extends Vehicle {

    private boolean turboOn;
    
    public Saab95() {
        super(2, 125, Color.red, "Saab95", Weight.LIGHT);
        turboOn = false;
    }
    public void setTurboOn(){
	    turboOn = true;
    }

    public void setTurboOff(){
	    turboOn = false;
    }

    @Override
    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }
}
