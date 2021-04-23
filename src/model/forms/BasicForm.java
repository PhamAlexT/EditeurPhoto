package model.forms;

import java.io.Serializable;

public abstract class BasicForm implements Serializable {
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
