package GUI;

import Vehicles.CarMechanic;
import Vehicles.Vehicle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel implements Observer{

    //List that handels Images and list that makes the that have the cars inmotion
    private ArrayList<BufferedImage> carImg = new ArrayList<>();//Storing the images
    private ArrayList<BufferedImage> carMechanicImg = new ArrayList<>();
    private CarModel listCars;
    private ActiveCarMechanics listCarMechanic;

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
    }

    @Override
    public void update(CarModel cars){
        carImg.clear();
        carMechanicImg.clear();
        addIMG(cars, listCarMechanic);
        repaint();
    }

    public void setListViewCarsAndCarMechanic(CarModel listCars, ActiveCarMechanics listCarMechanic){
        this.listCars = listCars;
        this. listCarMechanic = listCarMechanic;
        addIMG(listCars, listCarMechanic);
    }

    private void addIMG(CarModel listCars, ActiveCarMechanics listCarMechanic){
        try {
            for(Vehicle car : listCars.getListCarsInmotion()){
                carImg.add(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/" + car.getModelName() + ".jpg")));
            }
            for(CarMechanic carMechanic : listCarMechanic.getListOfCarMechanics()) {
                carMechanicImg.add(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/" + carMechanic.getShopName() + ".jpg")));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < carImg.size(); i++){
            BufferedImage carImage = carImg.get(i);
            g.drawImage(carImage, (int) listCars.getListCarsInmotion().get(i).getPosition().getX(), (int) listCars.getListCarsInmotion().get(i).getPosition().getY(), null); // see javadoc for more info on the parameters
        }
        for(int j = 0; j< carMechanicImg.size(); j++) {
            g.drawImage(carMechanicImg.get(j), (int) listCarMechanic.getListOfCarMechanics().get(j).getPosition().getX(), (int) listCarMechanic.getListOfCarMechanics().get(j).getPosition().getY(), null);
        }
    }
}
