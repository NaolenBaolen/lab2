package GUI;

import Vehicles.*;

public class CollisionHandler {
    private CarMechanic<Volvo240> volvoShop;

    public CollisionHandler(CarMechanic<Volvo240> volvoShop){
        this.volvoShop = volvoShop;
    }

    //checks and takes care of collision with boarder or workshop
    public void handleCollision(Vehicle car){
        if (car instanceof Volvo240) checkWorkshopCollision(car);
        if (isOutOfBounds(car.getPosition())) handleBorderCollision(car);
    }

    //Check if volvo is in proximity and loads it
    private void checkWorkshopCollision (Vehicle vehicle){
        if(isColliding(vehicle.getPosition(), volvoShop.getPosition())){
            volvoShop.load((Volvo240) vehicle);
            //listCars.removeVehicle(vehicle); TODO detta fakkar upp för listan uppdateras inte i drawpanel, Observer-pattern
            vehicle.getPosition().setPosition(volvoShop.getPosition().getX(), volvoShop.getPosition().getY()); //remove instead of locking position
            System.out.print("Volvo240 loaded");
        }
    }

    //Check if vehicle is at boarder and turns it around
    private void handleBorderCollision (Vehicle vehicle){
        vehicle.stopEngine();
        vehicle.turnLeft();
        vehicle.turnLeft();
        //TODO HEELL NO
        vehicle.getPosition().setPosition(
                Math.max(0, Math.min(vehicle.getPosition().getX(), 800)),
                Math.max(0, Math.min(vehicle.getPosition().getY(), 499)));

        vehicle.startEngine();
        System.out.print(vehicle.getDirection());
    }
    //bestämmer när de klassas som collision
    private boolean isColliding(Position vehiclePos, Position workshopPos){
        return vehiclePos.distance(workshopPos) < 50;
    }

    private boolean isOutOfBounds(Position pos){
        return pos.getX() < 0 || pos.getX() > 800 || pos.getY() < 0 || pos.getY() > 499 ;
    }
}
