package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JPanel brakePanel;

    //Labels
    private JLabel gasLabel;
    private JLabel brakeLabel;

    //Spinners
    private JSpinner gasSpinner;
    private JSpinner brakeSpinner;
    private int gasAmount = 0;
    private int brakeAmount = 0;

    //Buttons
    private JButton gasButton;
    private JButton brakeButton;
    private JButton turboOnButton;
    private JButton turboOffButton;
    private JButton liftBedButton;
    private JButton lowerBedButton;
    private JButton startButton;
    private JButton stopButton;

    // Constructor
    public CarView(String framename){
        initComponents(framename);
    }

    public void setCarAction(CarActionButtonListner carActions) {
        this.carActions = carActions;
    }

    // Sets everything in place and fits everything
    // TODO: Take a good look and make sure you understand how these methods and components work
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

        controlPanel = new JPanel(new GridLayout(2, 4));
        controlPanel.setPreferredSize(new Dimension((X/2)+4, 200));
        controlPanel.setBackground(Color.CYAN);
        this.add(controlPanel);
    }

    private void setupControls(){
        //Setup spinner
        SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 100 , 1);
        gasSpinner = new JSpinner(spinnerModel);
        brakeSpinner = new JSpinner(spinnerModel);

        //Labels
        gasLabel = new JLabel("Amount of gas");
        brakeLabel = new JLabel("Amount of brake");

        //Buttons
        gasButton = new JButton("Gas");
        brakeButton = new JButton("Brake");
        turboOnButton = new JButton("Turbo on");
        turboOffButton = new JButton("Turbo off");
        liftBedButton = new JButton("Raise bed");
        lowerBedButton = new JButton("Lower bed");
        startButton = new JButton("Start all cars");
        stopButton = new JButton("Stop all cars");

        //Gas panel
        gasPanel = new JPanel(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);
        this.add(gasPanel);

        //Brake panel
        brakePanel = new JPanel(new BorderLayout());
        brakePanel.add(brakeLabel,BorderLayout.PAGE_START);
        brakePanel.add(brakeSpinner, BorderLayout.PAGE_END);
        this.add(brakePanel);

        //add buttons to control panel
        controlPanel.add(gasButton);
        controlPanel.add(turboOnButton);
        controlPanel.add(liftBedButton);
        controlPanel.add(brakeButton);
        controlPanel.add(turboOffButton);
        controlPanel.add(lowerBedButton);

        //Start and stop buttons
        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(X/5-15,200));
        this.add(startButton);

        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(X/5-15,200));
        this.add(stopButton);
    }

    private void setupEventListeners(){
        //Spinner listeners
        gasSpinner.addChangeListener(e -> gasAmount = (int) ((JSpinner) e.getSource()).getValue());
        brakeSpinner.addChangeListener(e -> brakeAmount = (int) ((JSpinner) e.getSource()).getValue());

        //button listeners
        gasButton.addActionListener(e -> {if (carActions != null){carActions.gas(gasAmount);}});
        brakeButton.addActionListener(e -> {if (carActions != null){carActions.brake(brakeAmount);}});
        turboOnButton.addActionListener(e -> {if (carActions != null){carActions.turboOn();}});
        turboOffButton.addActionListener(e -> {if (carActions != null){carActions.turboOff();}});
        liftBedButton.addActionListener(e -> {if (carActions != null){carActions.liftBed();}});
        lowerBedButton.addActionListener(e -> {if (carActions != null){carActions.lowerBed();}});
        startButton.addActionListener(e -> {if (carActions != null){carActions.startAll();}});
        stopButton.addActionListener(e -> {if (carActions != null){carActions.stopAll();}});
    }

    private void finalze(){
        this.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}