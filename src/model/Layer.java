package model;

import javafx.event.EventHandler;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import model.forms.Rectangle;
import model.forms.BasicForm;

import java.util.ArrayList;

public class Layer extends Canvas {
    private String name;
    private GraphicsContext gc;
    private ArrayList<Object> basicForms = new ArrayList<Object>();
    private String drawMode;
    
    public Layer(String s, int width, int height){
        super(width, height);
        this.name = s;
        
        gc = this.getGraphicsContext2D();
        
        drawMode = "default";
        
        createLayerHandler();

    }
    
    public void setDim(int x, int y) {
    	this.setWidth(x);
    	this.setHeight(y);
    }

    public String getName(){
        return this.name;
    }
    
    public void setDrawMode(String newMode) {
    	this.drawMode = newMode;
    }

    public void createLayerHandler(){
        //System.out.println("Layer Handler Created");
    	this.addEventHandler(MouseEvent.MOUSE_DRAGGED, 
    			e ->{
    				if(drawMode == "default")
    					gc.fillOval(e.getX() - 10, e.getY() - 10, 20, 20);
    				if(drawMode == "rectangle") {
    					Rectangle rec = (Rectangle) basicForms.get(basicForms.size() - 1);
    					gc.clearRect(0, 0, this.getWidth(), this.getHeight());
    					rec.setEndPoint(e.getX(), e.getY());
    					gc.fillRect(rec.getX(), rec.getY(), rec.getWidth(), rec.getHeight());
    				}
    				for(Object o : basicForms) {
						if(o instanceof Rectangle) {
							gc.fillRect(((Rectangle) o).getX(), ((Rectangle) o).getY(), ((Rectangle) o).getWidth(), ((Rectangle) o).getHeight());
						}
						
					}
    			});
        this.addEventHandler(MouseEvent.MOUSE_PRESSED,
                e -> {  
                	System.out.println();
                	if (drawMode == "rectangle") {
                		basicForms.add( new Rectangle(e.getX(), e.getY()));
                	}
                	
                	else if (drawMode == "circle") {
                		
                	}
                	
                	else if (drawMode == "line") {
                		
                	}
                	else {
                		gc.setFill(Color.PINK);
                		gc.fillOval(e.getX() - 10, e.getY() - 10, 20, 20);                		
                	}
                		
                    
                });
    }


	public ArrayList<Object> getBasicForm() {
    	return this.basicForms;
	}
}
