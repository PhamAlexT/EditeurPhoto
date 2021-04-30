package model.forms;

import javafx.scene.paint.Color;

public class Disc extends BasicForm{

    private double ray;

    public Disc(double x, double y, Color c,double r){
        super(x,y,c);
        this.ray = r;
    }

    @Override
    public String toString() {
        return "Rectangle{} at x=" + this.x + " and y=" + this.y;
    }

    public double getRay(){
        return ray;
    }

}
