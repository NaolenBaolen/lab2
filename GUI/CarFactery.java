package GUI;

import Vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class CarFactery implements ListCarsInmotion{
    private final List<Vehicle> vehicles = new ArrayList<>();

    @Override
    public List<Vehicle> getListCarsInmotion(){
        return vehicles;
    }

    @Override
    public void addVehicle(Vehicle vehicle){
        vehicles.add(vehicle);
    }

    @Override
    public void removeVehicle(Vehicle vehicle){
        vehicles.remove(vehicle);
    }
}
