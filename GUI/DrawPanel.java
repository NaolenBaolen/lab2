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

    private CarMechanic mechanic;

    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(0,300);

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
    }

    public void setListCars(ArrayList<Vehicle> listCars){
        this.listCars = listCars;
        addCarIMG();
    }

    public void setWorkshop(CarMechanic mechanic){
        this.mechanic = mechanic;
    }

    private void addCarIMG(){
        try {
            for(Vehicle car : listCars){
                carImg.add(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/" + car.getModelName() + ".jpg")));
            }
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < carImg.size(); i++){
            Vehicle car = listCars.get(i);
            g.drawImage(carImg.get(i), (int) car.getPosition().getX(), (int) car.getPosition().getY(), null); // see javadoc for more info on the parameters
        }
            g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
    }
}
