package GUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    // Just a single image, TODO: Generalize
    private ArrayList<BufferedImage> carImg = new ArrayList<>();   //Storing the images
    private ArrayList<Point> carPos = new ArrayList<>();            //assigning positions
    //BufferedImage volvoImage;
    // To keep track of a single car's position
    //Point volvoPoint = new Point();

    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300,300);

    // TODO: Make this general for all cars
    void moveit(int i, int x, int y){
        if ( i >= 0 && i < carPos.size()){
            Point current = carPos.get(i);
            current.x = x;
            current.y = y;
        }
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        addCarIMG();
        addPos();

    }
    private void addCarIMG(){
        try {
            carImg.add(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
            carImg.add(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")));
            carImg.add(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")));
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void addPos(){
        carPos.add(new Point(0, 0));
        carPos.add(new Point(0, 100));
        carPos.add(new Point(0, 200));
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < carImg.size(); i++){
            g.drawImage(carImg.get(i), carPos.get(i).x, carPos.get(i).y, null); // see javadoc for more info on the parameters
            g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
        }


    }
}
