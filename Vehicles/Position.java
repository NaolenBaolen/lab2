package Vehicles;

public class Position {

    private double x;
    private double y;

    public Position(double x, double y){
        this.x = x;
        this.y = y;
    }


    //get and set individual coordinates
    public double getX(){return x;}
    public double getY(){return y;}

    public void setX(double amount){this.x = amount;}
    public void setY(double amount){this.y = amount;}


    //set complete position
    public void setPosition(double x, double y){
        setX(x);
        setY(y);
    }

    //get distance between 2 positions
    public double distance(Position other){
        double Ax = (this.x - other.x);
        double Ay = (this.y - other.y);

        return Math.sqrt((Ax * Ax) + (Ay * Ay));
    }

    public void move (double dx, double dy){
        this.x += dx;
        this.y += dy;
    }

}
