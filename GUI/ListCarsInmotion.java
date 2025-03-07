package GUI;

import Vehicles.Vehicle;

import java.util.List;

public interface ListCarsInmotion {
    List<Vehicle> getListCarsInmotion();
    void addVehicle(Vehicle vehicle);
    void removeVehicle(Vehicle vehicle);
}
