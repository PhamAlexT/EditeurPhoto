package model.forms;

import javafx.scene.paint.Color;

public class Rectangle extends BasicForm {
    double width;
    double height;

    public Rectangle(double beginX, double beginY , Color c, double endX, double endY) {
        super(beginX, beginY, c);
        setDim(endX, endY);
    }
    
    public void setDim(double endX, double endY) {
    	this.width = Math.abs(endX - this.x);
    	this.height = Math.abs(endY - this.y);
	}
    
    public double getWidth() {
    	return width;
    }
    
    public double getHeight() {
    	return height;
    }

    @Override
    public String toString() {
        return "Rectangle{} at x=" + this.x + " and y=" + this.y;
    }
}
