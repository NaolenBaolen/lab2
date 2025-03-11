package GUI;

import Vehicles.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CarModel implements ListCarsInmotion, Observable{
    private final ArrayList<Vehicle> vehicles = new ArrayList<>();
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public ArrayList<Vehicle> getListCarsInmotion(){
        return vehicles;
    }

    public void gas(int gasAmount){
        vehicles.forEach(vehicle -> vehicle.gas(((double)gasAmount)/100));
    }

    public void brake(int brakeAmount){
        vehicles.forEach(vehicle -> vehicle.brake(((double)brakeAmount)/100));
    }

    public void turboOn(){
        vehicles.forEach(vehicle -> {if(vehicle instanceof Turbo){((Turbo)vehicle).setTurboOn();}});
    }

    public void turboOff(){
        vehicles.forEach(vehicle -> {if(vehicle instanceof Turbo){((Turbo)vehicle).setTurboOff();}});
    }

    public void liftBed(){
        vehicles.forEach(vehicle -> {if(vehicle instanceof truckBed){((truckBed)vehicle).raiseBed();}});
    }

    public void lowerBed(){
        vehicles.forEach(vehicle -> {if(vehicle instanceof truckBed){((truckBed)vehicle).lowerBed();}});
    }

    public void startAll(){
        vehicles.forEach(Vehicle::startEngine);
    }

    public void stopAll(){
        vehicles.forEach(Vehicle::stopEngine);
    }

    @Override
    public void addVehicle(){
        if(vehicles.size() < 5) {
            createRandomVehicle(); //Add a random car
        }
        else{
            System.out.println("Max cars in motion reached");
        }
        uppdateObservers(this);
    }

    @Override
    public void removeVehicle(){
        if(!vehicles.isEmpty()){
            Vehicle vehicle = vehicles.get(new Random().nextInt(vehicles.size()));
            vehicles.remove(vehicle);
        }
        else{
            throw new IllegalArgumentException("No cars to remove, all cars are removed");
        }
        uppdateObservers(this);
    }

    public void removeVehicleCollision(Vehicle vehicle){
        vehicles.remove(vehicle);
        uppdateObservers(this);
    }

    //Factory methods for creating different vehicles
    public void createVolvo(){
        vehicles.add(CarFactory.createVolvo());
    }

    public void createSaab(){
        vehicles.add(CarFactory.createSaab());
    }

    public void createScania(){
        vehicles.add(CarFactory.createScania());
    }

    public void createRandomVehicle(){
        List<Runnable> createMethods = List.of(
                this::createVolvo,
                this::createSaab,
                this::createScania
        );
        createMethods.get(new Random().nextInt(createMethods.size())).run();
    }

    @Override
    public void addObserver(Observer observer){
        observers.add(observer);
    }
    @Override
    public void removeObserver(Observer observer){
        observers.remove(observer);
    }
    @Override
    public void uppdateObservers(CarModel listCars){
        for(Observer observer : observers){
            observer.update(listCars);
        }
    }
}
