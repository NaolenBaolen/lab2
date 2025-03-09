package GUI;

import Vehicles.CarMechanic;
import Vehicles.VolvoMechanic;

import java.util.ArrayList;

public class ActiveCarMechanics implements ListOfActiveCarMechanics {
    private final ArrayList<CarMechanic> listCarMechanic = new ArrayList<>();

    @Override
    public ArrayList<CarMechanic> getListOfCarMechanics() {
        return listCarMechanic;
    }

    @Override
    public void addCarMechanic(CarMechanic carMechanic) {
        listCarMechanic.add(carMechanic);
    }

    @Override
    public void removeCarMechanic(CarMechanic carMechanic) {
        listCarMechanic.remove(carMechanic);
    }

    public void createVolvoMechanic() {
        VolvoMechanic volvoshop = new VolvoMechanic();
        addCarMechanic(volvoshop);

    }
}
