package Vehicles;

import java.awt.*;

public class Scania extends Vehicle implements truckBed {
    private  double bedAngle;
    private boolean bedRaised;

    public Scania() {
        super(2, 120, Color.black, "Scania", Weight.MEDIUM);
        this.bedAngle = 0;
        this.bedRaised = false;
    }

    public double getBedAngle(){return bedAngle;}

    @Override
    public double speedFactor() {
        return getEnginePower() * 0.01;
    }

    @Override
    public void raiseBed() {
        raiseBed(70);
    }

    @Override
    public void lowerBed() {
        lowerBed(70);
    }

    public void raiseBed(double amount){
        if(isMoving()){
            System.out.print("Can not raise while moving");
        }else{
            bedAngle = Math.min(bedAngle + amount, 70);
            bedRaised = bedAngle > 0;
            System.out.print("ready to laod");
        }
    }

    public void lowerBed(double amount){
        if(isMoving()){
            System.out.print("Can not lower while moving");
        } else{
            bedAngle = Math.max(bedAngle - amount , 0);
            bedRaised = bedAngle > 0;
        }
    }

    @Override
    public void move(){
        if(bedRaised){
          setCurrentSpeed(0);   // dunder silvertejp --------
        }
        super.move();
    }

}
