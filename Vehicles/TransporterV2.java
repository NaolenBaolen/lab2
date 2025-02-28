package Vehicles;

import java.awt.*;
import java.util.LinkedList;

public class TransporterV2 extends Vehicle implements truckBed, Loadable<Vehicle>{
    private LinkedList<Vehicle> vehicleCollection;
    private boolean bedRaised;
    private static final int maxCapacity = 10;
    private static final int minProxi = 3;

    public TransporterV2() {
        super(2, 150, Color.RED, "Volvo_VAH", Weight.MEDIUM);

        this.bedRaised = false;
        this.vehicleCollection = new LinkedList<>();
    }

    public boolean isBedRaised(){return bedRaised;}               //no usage, do we need?
    public int collectionSize(){return vehicleCollection.size();} //---||---
    public boolean collectionContains(Vehicle vehicle){return vehicleCollection.contains(vehicle);} //---||---

    @Override
    public double speedFactor() {
        return getEnginePower() * 0.01;
    }

    @Override
    public void load(Vehicle vehicle) {
        if(canLoad(vehicle)){
            vehicleCollection.add(vehicle);
            updatePosition(vehicle);
        }else{
            System.out.print("loading requirements not met.");
        }
    }

    @Override
    public void unload() {
        if(canUnload()){
            Vehicle vehicle = vehicleCollection.removeLast();
            vehicle.position.setPosition(this.position.getX() - 1,this.position.getY()); //TODO is this the best??
        } else{
            System.out.print("Unloading requirements not met.");
        }

    }

    @Override
    public void raiseBed() {
        if(isMoving()){
            System.out.print("No can do");
        }else{
            bedRaised = true;
        }
    }

    @Override
    public void lowerBed() {
        if(isMoving()){
            System.out.print("No can do");
        }else{
            bedRaised = false;
        }
    }

    @Override
    public void move(){
        super.move();

        for(Vehicle vehicle : vehicleCollection) {
            updatePosition(vehicle);
        }
    }

    //TODO: Probably doesnt need to be in a helper method
    private void updatePosition(Vehicle vehicle){
        vehicle.position.setPosition(this.position.getX(), this.position.getY());
    }

    //loading requiremnts
    private boolean canLoad(Vehicle vehicle){
        return (bedRaised && !full() && inRange(vehicle.position) && vehicle.getWeight() == Weight.LIGHT);
    }

    private boolean canUnload(){
        return (bedRaised && !(vehicleCollection.isEmpty()));
    }

    private boolean full(){
        return vehicleCollection.size() >= maxCapacity;
    }

    //TODO: Make sure this works, it should
    private boolean inRange(Position vehiclePos){return this.position.distance(vehiclePos) <= minProxi;}

//    private boolean inProximity(Vehicle vehicle){return distToTransport(vehicle) <= minProxi;}

//    private double distToTransport(Vehicle vehicle){
//        double Ax = (this.getX() - vehicle.getX());
//        double Ay = (this.getY() - vehicle.getY());
//
//        return Math.sqrt((Ax * Ax) + (Ay * Ay));
//    }
}
