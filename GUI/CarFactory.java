package GUI;
import Vehicles.*;

public class CarFactory {
    //Factory methods for creating different vehicles
    public static Vehicle createVolvo(){
        return new Volvo240();
    }

    public static Vehicle createSaab(){
        return new Saab95();
    }

    public static Vehicle createScania(){
        return new Scania();
    }
}
