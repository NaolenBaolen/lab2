package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 * TODO: Write more actionListeners and wire the rest of the buttons
 **/

public class CarView extends JFrame{
    private static final int X = 800;
    private static final int Y = 800;
    private CarActionButtonListner carActions;

    //Panels
    DrawPanel drawPanel;
    private JPanel controlPanel;
    private JPanel gasPanel;

    //Labels
    private JLabel gasLabel;

    //Spinners
    private JSpinner gasBrakeSpinner;
    private int gasBrakeAmount = 0;

    //Buttons
    private JButton gasButton;
    private JButton brakeButton;
    private JButton turboOnButton;
    private JButton turboOffButton;
    private JButton liftBedButton;
    private JButton lowerBedButton;
    private JButton startButton;
    private JButton stopButton;
    private JButton addCarButton;
    private JButton removeCarButton;

    // Constructor
    public CarView(String framename){
        initComponents(framename);
    }

    public void setCarAction(CarActionButtonListner carActions) {
        this.carActions = carActions;
    }

    // Sets everything in place and fits everything
    private void initComponents(String title) {
        setupFrame(title);
        setupPanels();
        setupControls();
        setupEventListeners();
        finalze();
    }
    private void setupFrame(String title){
        this.setTitle(title);
        this.setPreferredSize(new Dimension(X,Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
    }

    private void setupPanels(){
        drawPanel = new DrawPanel(X,Y -240);
        this.add(drawPanel);

        //Gas panel
        gasLabel = new JLabel("Gas/brake amount");
        SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 100 , 1);
        gasBrakeSpinner = new JSpinner(spinnerModel);

        gasPanel = new JPanel(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasBrakeSpinner, BorderLayout.PAGE_END);
        this.add(gasPanel);

        //Control panel
        controlPanel = new JPanel(new GridLayout(2, 4));
        controlPanel.setPreferredSize(new Dimension((X/2)+4, 200));
        controlPanel.setBackground(Color.CYAN);
        this.add(controlPanel);
    }

    private void setupControls(){
        //Buttons
        gasButton = new JButton("Gas");
        brakeButton = new JButton("Brake");
        turboOnButton = new JButton("Turbo on");
        turboOffButton = new JButton("Turbo off");
        liftBedButton = new JButton("Raise bed");
        lowerBedButton = new JButton("Lower bed");
        startButton = new JButton("Start all cars");
        stopButton = new JButton("Stop all cars");
        addCarButton = new JButton("Add car");
        removeCarButton = new JButton("Remove car");

        //add buttons to control panel
        controlPanel.add(gasButton,0);
        controlPanel.add(turboOnButton,1);
        controlPanel.add(liftBedButton,2);
        controlPanel.add(brakeButton,3);
        controlPanel.add(turboOffButton,4);
        controlPanel.add(lowerBedButton,5);

        addCarButton.setBackground(new Color(138, 43, 226));
        addCarButton.setPreferredSize(new Dimension(X/5-23,200));
        this.add(addCarButton);
        removeCarButton.setBackground(Color.ORANGE);
        removeCarButton.setPreferredSize(new Dimension(X/5-23,200));
        this.add(removeCarButton);
        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(X/5-23,200));
        this.add(startButton);

        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(X/5-23,200));
        this.add(stopButton);
    }

    private void setupEventListeners(){
        //Spinner listeners
        gasBrakeSpinner.addChangeListener(e -> gasBrakeAmount = (int) ((JSpinner) e.getSource()).getValue());

        //button listeners
        gasButton.addActionListener(e -> {if (carActions != null){carActions.gas(gasBrakeAmount);}});
        brakeButton.addActionListener(e -> {if (carActions != null){carActions.brake(gasBrakeAmount);}});
        turboOnButton.addActionListener(e -> {if (carActions != null){carActions.turboOn();}});
        turboOffButton.addActionListener(e -> {if (carActions != null){carActions.turboOff();}});
        liftBedButton.addActionListener(e -> {if (carActions != null){carActions.liftBed();}});
        lowerBedButton.addActionListener(e -> {if (carActions != null){carActions.lowerBed();}});
        startButton.addActionListener(e -> {if (carActions != null){carActions.startAll();}});
        stopButton.addActionListener(e -> {if (carActions != null){carActions.stopAll();}});
        addCarButton.addActionListener(e -> {if(carActions != null){carActions.addCar();}});
        removeCarButton.addActionListener(e-> {if(carActions != null){carActions.removeCar();}});
    }

    private void finalze(){
        this.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}