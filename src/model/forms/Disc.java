package model.forms;

public class Disc extends BasicForm{

    private double ray;

    public Disc(double x, double y,double r){
        super(x,y);
        this.ray = r;
    }

    @Override
    public String toString() {
        return "Rectangle{} at x=" + this.x + " and y=" + this.y;
    }

}
