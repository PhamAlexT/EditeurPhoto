package model.forms;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Stroke extends BasicForm {

    private double endX;
    private double endY;

    public Stroke(double x1, double y1, Color c,double x2, double y2) {
        super(x1, y1, c);
        endX = x2;
        endY = y2;

    }

    public double getBeginX() {
        return this.x;
    }

    public double getBeginY() {
        return this.y;
    }

    public double getEndX() {
        return endX;
    }

    public double getEndY() {
        return endY;
    }
    

    @Override
    public String toString() {
        return "Stroke from {} x=" + this.x + " and y=" + this.y + " to x=" + this.endX + " and y= " + this.endY;
    }

    @Override
    public boolean isInside(double x, double y) {
        double a = (endY - this.y)/(endX-this.x);
        double b = this.y - (a * this.x);
        return a*x+b <= Math.pow(10,-2);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(this.color);
        gc.strokeLine(this.x,this.y,this.endX,this.endY);
    }
}
