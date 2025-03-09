package GUI;

import Vehicles.CarMechanic;
import Vehicles.Vehicle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    // Just a single image, TODO: Generalize
    private ArrayList<BufferedImage> carImg = new ArrayList<>();//Storing the images
    private ArrayList<Vehicle> listCars;

    private ArrayList<BufferedImage> carMechanicImg = new ArrayList<>();
    private ArrayList<CarMechanic> listCarMechanic;


    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
    }

    public void setListViewCarsAndCarMechanic(ArrayList<Vehicle> listCars, ArrayList<CarMechanic> listCarMechanic){
        this.listCars = listCars;
        this. listCarMechanic = listCarMechanic;
        addCarIMG();
    }

    private void addCarIMG(){
        try {
            for(Vehicle car : listCars){
                carImg.add(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/" + car.getModelName() + ".jpg")));
            }
            for(CarMechanic carMechanic : listCarMechanic) {
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
            Vehicle car = listCars.get(i);
            g.drawImage(carImg.get(i), (int) car.getPosition().getX(), (int) car.getPosition().getY(), null); // see javadoc for more info on the parameters
        }
        for(int j = 0; j< carMechanicImg.size(); j++) {
            g.drawImage(carMechanicImg.get(j), (int) listCarMechanic.get(j).getPosition().getX(), (int) listCarMechanic.get(j).getPosition().getY(), null);
        }
    }
}
