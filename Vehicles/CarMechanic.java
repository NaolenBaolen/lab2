package Vehicles;

import java.util.LinkedList;

public abstract class CarMechanic<T extends Vehicle> implements Loadable<T> {
    private final int maxCap;
    private LinkedList<T> vehicleIn;
    private final Position position;
    private final String shopName;

    public CarMechanic(int maxCap, String shopName){
        this.maxCap = maxCap;
        this.vehicleIn = new LinkedList<>();
        this.position = new Position(0, 0);
        this.shopName = shopName;
    }

    public Position getPosition(){return position;}
    public String getShopName() {return shopName;}
    public int getSize(){return vehicleIn.size();}
    public boolean shopContains(T vehicle){
        return vehicleIn.contains(vehicle);
    }

    @Override
    public void load(T vehicle) {
        if(!full()){
            vehicleIn.add(vehicle);
        }else{
            System.out.print("shop full"); //TODO try catch statement
        }

    }

    @Override
    public void unload() {
        if(!vehicleIn.isEmpty()){
            Vehicle vehicle = vehicleIn.remove();
        }else{
            System.out.print("No vehicles in right now.");
        }
    }


    private boolean full(){
        return  vehicleIn.size() >= maxCap;
    }
}
