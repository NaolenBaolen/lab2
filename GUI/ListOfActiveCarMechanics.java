package GUI;

import Vehicles.CarMechanic;

import java.util.List;

public interface ListOfActiveCarMechanics {
    List<CarMechanic> getListOfCarMechanics();
    void addCarMechanic(CarMechanic carMechanic);
    void removeCarMechanic(CarMechanic carMechanic);
}
