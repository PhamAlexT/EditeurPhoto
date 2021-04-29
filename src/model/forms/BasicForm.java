package model.forms;

import java.io.Serializable;

import javafx.scene.paint.Color;

public abstract class BasicForm implements Serializable {
    double x;
    double y;
    Color color;

    public BasicForm(double x, double y, Color c) {
        this.x = x;
        this.y = y;
        this.color = c;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    public Color getColor() {
    	return color;
    }

    public abstract String toString();
}
