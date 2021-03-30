package model.forms;

public abstract class BasicForm {
    double x;
    double y;

    public BasicForm(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public abstract String toString();
}
