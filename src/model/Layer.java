package model;

import javafx.event.EventHandler;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import model.forms.Disc;
import model.forms.Rectangle;
import model.forms.BasicForm;
import model.forms.Stroke;

import java.util.ArrayList;

public class Layer extends Canvas {
    private String name;
    private GraphicsContext gc;
    private ArrayList<BasicForm> basicForms = new ArrayList<BasicForm>();
    private double beginX;
    private double beginY;
    private boolean drawingAllowed;
    private Color color;

    public Layer(String s, int width, int height){
        super(width, height);
        this.name = s;
        
        gc = this.getGraphicsContext2D();

        drawingAllowed = true;

    }



    public void setDim(int x, int y) {
    	this.setWidth(x);
    	this.setHeight(y);
    }

    public String getName(){
        return this.name;
    }

    private void beginShape(MouseEvent e){
        if (drawingAllowed){
            beginX = e.getX();
            beginY = e.getY();
        }
    }

    private void drawingStroke(MouseEvent e){
        //TODO : Dessiner avec les bonnes couleurs?
        gc.setStroke(this.color);

        clearLayer();
        drawAllShape(); //TODO

        gc.setStroke(Color.BLUE); // Ligne temporaire
        gc.strokeLine(beginX,beginY,e.getX(),e.getY());
    }

    private void endStroke(MouseEvent e){
        basicForms.add(new Stroke(beginX,beginY,e.getX(),e.getY()));
        clearLayer();
        gc.setStroke(Color.BLACK);
        drawAllShape();
    }

    public void addStrokeListener(){
        this.setOnMousePressed(e->beginShape(e));
        this.setOnMouseDragged(e->drawingStroke(e));
        this.setOnMouseReleased(e->endStroke(e));
    }

    private void drawingDisc(MouseEvent e){
        gc.setStroke(Color.BLACK);
        clearLayer();
        drawAllShape();

        gc.setFill(Color.BLUE);
        gc.fillOval(this.beginX,this.beginY,Math.abs(this.beginX-e.getX()),Math.abs(this.beginX-e.getX()));
    }

    private void endDisc(MouseEvent e){
        basicForms.add(new Disc(beginX,beginY,Math.abs(this.beginX-e.getX())));
        clearLayer();
        gc.setStroke(Color.BLACK);
        drawAllShape();
    }

    public void addDiscListener(){
        this.setOnMousePressed(e->beginShape(e));
        this.setOnMouseDragged(e->drawingDisc(e));
        this.setOnMouseReleased(e->endDisc(e));
    }


	public ArrayList<BasicForm> getBasicForm() {
    	return this.basicForms;
	}

	void drawAllShape(){
        int compteurC = 0;
        int compteurL = 0;
        for (BasicForm form:basicForms){

            if (form instanceof Stroke){
                compteurL++;
                gc.strokeLine(((Stroke) form).getBeginX(),((Stroke) form).getBeginY(),((Stroke) form).getEndX(),((Stroke) form).getEndY());
            }

            else if (form instanceof Disc){
                compteurC++;
                gc.fillOval(this.beginX,this.beginY, ((Disc) form).getRay(),((Disc) form).getRay());
            }

        }
        System.out.println("Boucle de dessin: " + compteurL + " de cercles");
        System.out.println("Boucle de dessin: " + compteurC + " de cercles");
    }

    void clearLayer(){
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0,this.getWidth(),this.getHeight());
    }

    public void setColor(Color c){
        this.color = c;
    }


}
