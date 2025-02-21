package GUI;

import Vehicles.Saab95;
import Vehicles.ScaniaV2;
import Vehicles.Vehicle;
import Vehicles.Volvo240;

import javax.swing.*;
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

        cc.cars.add(new Volvo240());

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
            for (Vehicle car : cars) {
                car.move();
                double x =  car.getX();
                double y =  car.getY();
                if(outOfBounds(x, y)){
                    car.stopEngine();
                    car.turnLeft();
                    car.turnLeft();
                    car.setX(Math.max(0, Math.min(x, 800)));
                    car.setY(Math.max(0, Math.min(y, 200)));
                    car.startEngine();
                    System.out.print(car.getDirection());

                }
                frame.drawPanel.moveit( (int) Math.round(car.getX()), (int) Math.round(car.getY()));
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    private boolean outOfBounds(double x, double y){
        return x < 0 || x > 800 || y < 0 || y > 300 ;
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle car : cars
                ) {
            car.gas(gas);
        }
    }

    void brake(int amount){
        double brake = ((double) amount) / 100;
        for (Vehicle car : cars){
            car.brake(brake);
        }
    }

    void turboOn(){
        for (Vehicle car : cars){
            if(car instanceof Saab95){
                ((Saab95)car).setTurboOn();
            }
        }
    }
    void turboOff(){
        for (Vehicle car : cars){
            if (car instanceof Saab95){
                ((Saab95) car).setTurboOff();
            }
        }
    }
    void liftBed(){
        for (Vehicle car : cars){
            if (car instanceof ScaniaV2){
                ((ScaniaV2)car).raiseBed();
            }
        }
    }
    void lowerBed(){
        for (Vehicle car : cars){
            if( car instanceof ScaniaV2){
                ((ScaniaV2)car).lowerBed();
            }
        }
    }
    void startAll(){
        for (Vehicle car: cars){
            car.startEngine();
        }
    }
    void stopAll(){
        for(Vehicle car: cars){
            car.stopEngine();
        }
    }
}
