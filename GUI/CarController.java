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

public class CarController {
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
    ArrayList<Vehicle> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        Vehicle volvo = new Volvo240();
        Vehicle saab = new Saab95();
        Vehicle scania = new ScaniaV2();


        volvo.setX(0);
        volvo.setY(0);

        saab.setX(0);
        saab.setY(100);

        scania.setX(0);
        scania.setY(200);

        cc.cars.add(volvo);
        cc.cars.add(saab);
        cc.cars.add(scania);
        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            for(int i = 0; i < cars.size(); i++) {
                Vehicle car = cars.get(i);
                car.move();
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());

                workshopCollision(car);          //den laddar volvon. Men den repaintar volvo om o om igen...

                if (outOfBounds(x, y)) {
                    collisionHandling(car);
                }
                frame.drawPanel.moveit(i, (int) Math.round(car.getX()), (int) Math.round(car.getY()));
                // repaint() calls the paintComponent method of the panel
            }
            frame.drawPanel.repaint();
        }
    }
    //kollar om vehicle är instance of volvo, och i så fall kollar volvos position mot workshops
    private void workshopCollision (Vehicle vehicle){
        if(vehicle instanceof Volvo240){
            Point workshopPos = frame.drawPanel.volvoWorkshopPoint;
            Point vehiclePos = new Point((int) vehicle.getX(), (int) vehicle.getY());

            if(isColliding(vehiclePos, workshopPos)){
                volvoShop.load((Volvo240) vehicle);
                vehicle.setX(workshopPos.x);
                vehicle.setY(workshopPos.y);
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
        vehicle.setX(Math.max(0, Math.min(vehicle.getX(), 800)));
        vehicle.setY(Math.max(0, Math.min(vehicle.getY(), 500)));
        vehicle.startEngine();
    }
    private boolean outOfBounds(double x, double y){
        return x < 0 || x > 800 || y < 0 || y > 500 ;
    }

    // Call controls
    void gas(int amount) {double gas = ((double) amount) / 100;for (Vehicle car : cars) {car.gas(gas);}}
    void brake(int amount){double brake = ((double) amount) / 100;for (Vehicle car : cars){car.brake(brake);}}
    void turboOn(){for (Vehicle car : cars){if(car instanceof Saab95){((Saab95)car).setTurboOn();}}}
    void turboOff(){for (Vehicle car : cars){if (car instanceof Saab95){((Saab95) car).setTurboOff();}}}
    void liftBed(){for (Vehicle car : cars){if (car instanceof truckBed){((truckBed)car).raiseBed();}}}
    void lowerBed(){for (Vehicle car : cars){if( car instanceof truckBed){((truckBed)car).lowerBed();}}}
    void startAll(){for (Vehicle car: cars){car.startEngine();}}
    void stopAll(){for(Vehicle car: cars){car.stopEngine();}}
}
