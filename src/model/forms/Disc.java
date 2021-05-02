package model.forms;

import javafx.scene.canvas.GraphicsContext;
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

    @Override
    public boolean isInside(double x, double y) {
        double xcenter = this.x + ray;
        double ycenter = this.y - ray;
        double b1 = Math.abs(x-xcenter);
        double b2 = Math.abs(y-ycenter);
        return Math.sqrt(Math.pow(b1,2) + Math.pow(b2,2)) <= ray;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(this.color);
        gc.fillOval(this.x,this.y,this.ray,this.ray);
    }

    public double getRay(){
        return ray;
    }

}
