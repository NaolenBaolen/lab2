package GUI;

import Vehicles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController implements CarActionButtonListner{
    // member fields:
    private static CarMechanic<Volvo240> volvoShop = new Volvo240Mechanic();
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    CarFactery listCars;

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();
        cc.listCars = new CarFactery();
        //cc.listCars.createVolvo();
        cc.listCars.createSaab();
        cc.listCars.createScania();

//        volvo.getPosition().setPosition(0, 0);

//        saab.getPosition().setPosition(0, 100);
//        saab.setX(0);
//        saab.setY(100);

//        scania.getPosition().setPosition(0, 200);
//        scania.setX(0);
//        scania.setY(200);

//        cc.listCars.addVehicle(volvo);
//        cc.listCars.addVehicle(saab);
//        cc.listCars.addVehicle(scania);
        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0");
        cc.frame.setCarAction(cc);

        cc.frame.drawPanel.setListCars(cc.listCars.getListCarsInmotion());
        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            for(int i = 0; i < listCars.getListCarsInmotion().size(); i++) {
                Vehicle car = listCars.getListCarsInmotion().get(i);
                car.move();

                //TODO: not best implementation, can probably use setPosition instead
                int x = (int) Math.round(car.getPosition().getX());
                int y = (int) Math.round(car.getPosition().getY());

                workshopCollision(car);          //den laddar volvon. Men den repaintar volvo om o om igen...

                if (outOfBounds(x, y)) {
                    collisionHandling(car);
                }
                // repaint() calls the paintComponent method of the panel
            }
            frame.drawPanel.repaint();
        }
        private boolean outOfBounds(double x, double y){
            return x < 0 || x > 800 || y < 0 || y > 499 ;
        }
    }
    //kollar om vehicle är instance of volvo, och i så fall kollar volvos position mot workshops
    private void workshopCollision (Vehicle vehicle){
        if(vehicle instanceof Volvo240){
            Point workshopPos = frame.drawPanel.volvoWorkshopPoint;
            Point vehiclePos = new Point((int) vehicle.getPosition().getX(), (int) vehicle.getPosition().getY());

            if(isColliding(vehiclePos, workshopPos)){
                volvoShop.load((Volvo240) vehicle);
                vehicle.getPosition().setPosition(workshopPos.x, workshopPos.y);
                System.out.print("Volvo240 loaded");
            }
        }
    }
    //bestämmer när de klassas som collision
    private boolean isColliding(Point vehiclePos, Point workshopPos){
        int minDist = 50;
        return vehiclePos.distance(workshopPos) < minDist;
    }

    private void collisionHandling (Vehicle vehicle){
        vehicle.stopEngine();
        vehicle.turnLeft();
        vehicle.turnLeft();
        //TODO HEELL NO
        vehicle.getPosition().setPosition(Math.max(0, Math.min(vehicle.getPosition().getX(), 800)),
                                                    Math.max(0, Math.min(vehicle.getPosition().getY(), 499)));
//        vehicle.setX(Math.max(0, Math.min(vehicle.getX(), 800)));
//        vehicle.setY(Math.max(0, Math.min(vehicle.getY(), 500)));
        vehicle.startEngine();
    }

    // Call controls
    @Override
    public void gas(int amount) {double gas = ((double) amount) / 100;for (Vehicle car : listCars.getListCarsInmotion()) {car.gas(gas);}}
    @Override
    public void brake(int amount){double brake = ((double) amount) / 100;for (Vehicle car : listCars.getListCarsInmotion()){car.brake(brake);}}
    @Override
    public void turboOn(){for (Vehicle car : listCars.getListCarsInmotion()){if(car instanceof Turbo){((Turbo)car).setTurboOn();}}}
    @Override
    public void turboOff(){for (Vehicle car : listCars.getListCarsInmotion()){if (car instanceof Turbo){((Turbo) car).setTurboOff();}}}
    @Override
    public void liftBed(){for (Vehicle car : listCars.getListCarsInmotion()){if (car instanceof truckBed){((truckBed)car).raiseBed();}}}
    @Override
    public void lowerBed(){for (Vehicle car : listCars.getListCarsInmotion()){if( car instanceof truckBed){((truckBed)car).lowerBed();}}}
    @Override
    public void startAll(){for (Vehicle car: listCars.getListCarsInmotion()){car.startEngine();}}
    @Override
    public void stopAll(){for(Vehicle car: listCars.getListCarsInmotion()){car.stopEngine();}}

}
