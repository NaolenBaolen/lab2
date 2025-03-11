package GUI;

import Vehicles.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController implements CarActionButtonListner{

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());
    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    private CarFactery listCars;
    //Creates and handels carMechanics
    private ActiveCarMechanics listCarMechaincs;
    private CollisionHandler collisionHandler;

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();
        cc.listCars = new CarFactery();
        cc.listCars.createVolvo();
        cc.listCars.createSaab();
        cc.listCars.createScania();

        cc.listCarMechaincs = new ActiveCarMechanics();
        cc.listCarMechaincs.createVolvoMechanic();

        for(int i = 0; i < cc.listCars.getListCarsInmotion().size(); i++){
            cc.listCars.getListCarsInmotion().get(i).getPosition().setPosition(0, i*100);
        }
        for(int j = 0; j < cc.listCarMechaincs.getListOfCarMechanics().size(); j++){
            cc.listCarMechaincs.getListOfCarMechanics().get(j).getPosition().setPosition(j*100, 300);
        }

        cc.collisionHandler = new CollisionHandler(cc.listCarMechaincs);
        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0");
        cc.listCars.addObserver(cc.frame.drawPanel);
        cc.frame.setCarAction(cc);
        cc.frame.drawPanel.setListViewCarsAndCarMechanic(cc.listCars, cc.listCarMechaincs);
        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for(Vehicle car : listCars.getListCarsInmotion()) {
                car.move(); //if car.getCurrentSpeed > 0 else continue; ????
                collisionHandler.handleCollision(car);
            }
            listCars.uppdateObservers(listCars);//notifyObservers();
        }
    }


    // Call controls
    //TODO in gas maybe if(car instance of truckBed) continue?? so we can gas all cars even if a truck has bed raised
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
    @Override
    public void addCar(){listCars.addVehicle();}
    @Override
    public void removeCar(){listCars.removeVehicle(listCars.getListCarsInmotion().get(ThreadLocalRandom.current().nextInt(listCars.getListCarsInmotion().size())));}
}
