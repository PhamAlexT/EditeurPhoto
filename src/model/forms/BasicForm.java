package model.forms;

import java.io.Serializable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/*
Les objets JavaFX ne sont pas Serializable! Donc nous avons eu un problème avec la couleur
Solution: https://stackoverflow.com/questions/36748358/saving-color-as-state-in-a-javafx-application
Nous avons copié la classe car elle fait ce que l'on souhaite.
 */

public abstract class BasicForm implements Serializable {
    double x;
    double y;
    transient Color color;
    SerializableColor sc;

    public BasicForm(double x, double y, Color c) {
        this.x = x;
        this.y = y;
        this.color = c;
        this.sc = new SerializableColor(this.color);
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

    public void fixColor(){
        this.color = this.sc.getFXColor();
    }

    public abstract boolean isInside(double x, double y);

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public abstract void draw(GraphicsContext gc);

    public class SerializableColor implements Serializable
    {
        private double red;
        private double green;
        private double blue;
        private double alpha;
        public SerializableColor(Color color)
        {
            this.red = color.getRed();
            this.green = color.getGreen();
            this.blue = color.getBlue();
            this.alpha = color.getOpacity();
        }
        public SerializableColor(double red, double green, double blue, double alpha)
        {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.alpha = alpha;
        }
        public Color getFXColor()
        {
            return new Color(red, green, blue, alpha);
        }
    }
}
