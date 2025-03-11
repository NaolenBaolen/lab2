package GUI;

import Vehicles.Saab95;
import Vehicles.ScaniaV2;
import Vehicles.Vehicle;
import Vehicles.Volvo240;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class CarFactery implements ListCarsInmotion, Observable{
    private final ArrayList<Vehicle> vehicles = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();

    @Override
    public ArrayList<Vehicle> getListCarsInmotion(){
        return vehicles;
    }

    @Override
    public void addVehicle(){
        if(vehicles.size() < 5) {//Only 9 cars allowed at the same time
            createRandomVehicle(); //Add a random car after creating it
        }
        else{
            System.out.println("Max cars in motion reached");
        }
    }

    @Override
    public void removeVehicle(Vehicle vehicle){
        if(!vehicles.isEmpty()){
            vehicles.remove(vehicle);
        }
        else{
            System.out.print("No cars to remove, all cars are removed");
        }
    }

    //Factory methods for creating different vehicles

    public void createVolvo(){
        Vehicle volvo = new Volvo240();
        vehicles.add(volvo);
    }

    public void createSaab(){
        Vehicle saab = new Saab95();
        vehicles.add(saab);
    }

    public void createScania(){
        Vehicle scania = new ScaniaV2();
        vehicles.add(scania);
    }

    public void createRandomVehicle(){
        List<Runnable> createMethods = List.of(
                this::createVolvo,
                this::createSaab,
                this::createScania
        );
        createMethods.get(ThreadLocalRandom.current().nextInt(createMethods.size())).run();
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
    public void uppdateObservers(CarFactery listCars){
        for(Observer observer : observers){
            observer.update(listCars);
        }
    }
}
