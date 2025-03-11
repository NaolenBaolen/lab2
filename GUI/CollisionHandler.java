package GUI;

import Vehicles.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class CollisionHandler {

    public CollisionHandler(){}

    //checks and takes care of collision with boarder or workshop
    public void handleCollision(Vehicle car, ActiveCarMechanics carMechanics, CarModel listCars){
        checkWorkshopCollision(car, carMechanics, listCars);
        if (isOutOfBounds(car.getPosition())) handleBorderCollision(car);
    }

    //Check if volvo is in proximity and loads it
    private void checkWorkshopCollision (Vehicle vehicle, ActiveCarMechanics carMechanics, CarModel listCars){
        for(int i = 0; i< carMechanics.getListOfCarMechanics().size(); i++) {
            CarMechanic shop = carMechanics.getListOfCarMechanics().get(i);
            //Looks into which car types can be loaded into the different shops
            if (shop.getClass().getGenericSuperclass() instanceof ParameterizedType) {
                Type acceptedCarTyps = ((ParameterizedType) shop.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                if (acceptedCarTyps instanceof Class<?> && ((Class<?>) acceptedCarTyps).isInstance(vehicle)) {
                    //Checks if the car is at the shop so it can be loaded if so it loads the car.
                    if(isColliding(vehicle.getPosition(), shop.getPosition())){
                        shop.load(vehicle);
                        listCars.removeVehicleCollision(vehicle);
                        System.out.print(vehicle.getModelName() + " loaded\n");
                    }
                }
            }
        }
    }

    //Check if vehicle is at boarder and turns it around
    private void handleBorderCollision (Vehicle vehicle){
        vehicle.stopEngine();
        vehicle.turnLeft();
        vehicle.turnLeft();
        vehicle.getPosition().setPosition(
                Math.max(0, Math.min(vehicle.getPosition().getX(), 800)),
                Math.max(0, Math.min(vehicle.getPosition().getY(), 499)));

        vehicle.startEngine();
    }
    //bestämmer när de klassas som collision
    private boolean isColliding(Position vehiclePos, Position workshopPos){
        return vehiclePos.distance(workshopPos) < 50;
    }

    private boolean isOutOfBounds(Position pos){
        return pos.getX() < 0 || pos.getX() > 800 || pos.getY() < 0 || pos.getY() > 499 ;
    }
}
