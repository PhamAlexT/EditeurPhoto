package model.forms;

public class Rectangle extends BasicForm {
    double width;
    double height;

    public Rectangle(double beginX, double beginY) {
        super(beginX, beginY);
    }
    
    public void setEndPoint(double endX, double endY) {
    	this.width = endX - this.x;
    	this.height = endY - this.y;
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
