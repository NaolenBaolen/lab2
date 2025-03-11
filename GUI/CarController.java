package GUI;

import Vehicles.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarController implements CarActionButtonListner{

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());
    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    private CarModel listCars;
    //Creates and handels carMechanics
    private ActiveCarMechanics listCarMechaincs;
    private CollisionHandler collisionHandler;

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();
        cc.listCars = new CarModel();
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

        cc.collisionHandler = new CollisionHandler();
        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0");
        cc.listCars.addObserver(cc.frame.drawPanel);
        cc.frame.setCarAction(cc);
        cc.frame.drawPanel.setListViewCarsAndCarMechanic(cc.listCars, cc.listCarMechaincs);
        // Start the timer
        cc.timer.start();
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for(int i = listCars.getListCarsInmotion().size() -1 ; i >= 0 ; i-- ) {
                Vehicle car = listCars.getListCarsInmotion().get(i);
                car.move();
                collisionHandler.handleCollision(car, listCarMechaincs, listCars);
            }
            listCars.uppdateObservers(listCars);
        }
    }


    // Call controls
    @Override
    public void gas(int amount) {listCars.gas(amount);}
    @Override
    public void brake(int amount){listCars.brake(amount);}
    @Override
    public void turboOn(){listCars.turboOn();}
    @Override
    public void turboOff(){listCars.turboOff();}
    @Override
    public void liftBed(){listCars.liftBed();}
    @Override
    public void lowerBed(){listCars.lowerBed();}
    @Override
    public void startAll(){listCars.startAll();}
    @Override
    public void stopAll(){listCars.stopAll();}
    @Override
    public void addCar(){listCars.addVehicle();}
    @Override
    public void removeCar(){listCars.removeVehicle();}
}
