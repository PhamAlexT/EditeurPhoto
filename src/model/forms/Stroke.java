package model.forms;

public class Stroke extends BasicForm {

    private double endX;
    private double endY;

    public Stroke(double x1, double y1, double x2, double y2) {
        super(x1, y1);
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
}
