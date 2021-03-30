package model.forms;

public class Rectangle extends BasicForm{

    public Rectangle(double x, double y) {
        super(x, y);
    }

    @Override
    public String toString() {
        return "Rectangle{} at x=" + this.x + " and y=" + this.y ;
    }
}
