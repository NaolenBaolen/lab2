package GUI;

import Vehicles.Saab95;
import Vehicles.ScaniaV2;
import Vehicles.Vehicle;
import Vehicles.Volvo240;

import java.util.ArrayList;


public class CarFactery implements ListCarsInmotion{
    private final ArrayList<Vehicle> vehicles = new ArrayList<>();

    @Override
    public ArrayList<Vehicle> getListCarsInmotion(){
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

    //Factory methods for creating different vehicles

    public void createVolvo(){
        Vehicle volvo = new Volvo240();
        vehicles.add(volvo);
    }

    public Vehicle createSaab(){
        Vehicle saab = new Saab95();
        vehicles.add(saab);
        return saab;
    }

    public Vehicle createScania(){
        Vehicle scania = new ScaniaV2();
        vehicles.add(scania);
        return scania;
    }
}
