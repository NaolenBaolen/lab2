package GUI;

import Vehicles.CarMechanic;
import Vehicles.Vehicle;

import java.util.ArrayList;


public interface DrawDataProvider {
    ArrayList<Vehicle> getCars();
    ArrayList<CarMechanic> getMechanics();
}
