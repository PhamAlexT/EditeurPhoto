package model;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import model.forms.BasicForm;

import java.util.ArrayList;

public class Layer extends Canvas {
    private String name;
    private GraphicsContext gc;
    private ArrayList<BasicForm> basicForm;
    
    public Layer(String s, int width, int height){
        super(width, height);
        this.name = s;
        
        gc = this.getGraphicsContext2D();
        
        createLayerHandler();

    }
    
    public void setDim(int x, int y) {
    	this.setWidth(x);
    	this.setHeight(y);
    }

    public String getName(){
        return this.name;
    }

    public void createLayerHandler(){
        //System.out.println("Layer Handler Created");
        this.addEventHandler(MouseEvent.MOUSE_PRESSED,
                e -> {
                    gc.fillOval(e.getX() - 10, e.getY() - 10, 20, 20);
                });
    }



}
