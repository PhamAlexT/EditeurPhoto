package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import model.forms.Disc;
import model.forms.Rectangle;
import model.forms.BasicForm;
import model.forms.Stroke;

import java.util.ArrayList;

public class Layer extends Canvas {
    //Info
    private String name;
    private GraphicsContext gc;
    private ArrayList<BasicForm> basicForms = new ArrayList<BasicForm>();

    //Dessin
    private double beginX;
    private double beginY;
    private boolean drawingAllowed;
    private Color color;

    //Move
    private int toMove;
    private boolean enableToMove;

    public Layer(String s, int width, int height) {
        super(width, height);
        this.name = s;

        gc = this.getGraphicsContext2D();

        drawingAllowed = true;
        enableToMove = false;
    }

    public Layer(LayerInfo li) {
        super(li.width, li.height);
        gc = this.getGraphicsContext2D();
        this.name = li.name;
        this.basicForms = li.basicForms;
        for (BasicForm bf : this.basicForms) bf.fixColor();
        this.drawAllShape();
    }

    public void setDim(int x, int y) {
        this.setWidth(x);
        this.setHeight(y);
    }

    public String getName() {
        return this.name;
    }

    private void beginShape(MouseEvent e) {
        if (drawingAllowed) {
            beginX = e.getX();
            beginY = e.getY();
        }
    }

    private void drawingStroke(MouseEvent e) {
        //TODO : Dessiner avec les bonnes couleurs?

        clearLayer();
        drawAllShape(); //TODO

        gc.setStroke(Color.BLUE); // Ligne temporaire
        gc.strokeLine(beginX, beginY, e.getX(), e.getY());
    }

    private void endStroke(MouseEvent e) {
        basicForms.add(new Stroke(beginX, beginY, this.color, e.getX(), e.getY()));
        clearLayer();
        gc.setStroke(this.color);
        drawAllShape();
    }

    public void addStrokeListener() {
        this.setOnMousePressed(e -> beginShape(e));
        this.setOnMouseDragged(e -> drawingStroke(e));
        this.setOnMouseReleased(e -> endStroke(e));
    }

    private void drawingDisc(MouseEvent e) {
        gc.setStroke(Color.BLACK);
        clearLayer();
        drawAllShape();

        gc.setFill(Color.BLUE);
        gc.fillOval(this.beginX, this.beginY, Math.abs(this.beginX - e.getX()), Math.abs(this.beginX - e.getX()));
    }

    private void endDisc(MouseEvent e) {
        basicForms.add(new Disc(beginX, beginY, this.color, Math.abs(this.beginX - e.getX())));
        clearLayer();
        gc.setStroke(Color.BLACK);
        drawAllShape();
    }

    public void addDiscListener() {
        this.setOnMousePressed(e -> beginShape(e));
        this.setOnMouseDragged(e -> drawingDisc(e));
        this.setOnMouseReleased(e -> endDisc(e));
    }

    private void drawingRect(MouseEvent e) {
        // TODO Auto-generated method stub
        gc.setStroke(Color.BLACK);
        clearLayer();
        drawAllShape();

        gc.setFill(Color.BLUE);
        gc.fillRect(this.beginX, this.beginY, Math.abs(this.beginX - e.getX()), Math.abs(this.beginY - e.getY()));

    }

    private void endRect(MouseEvent e) {
        // TODO Auto-generated method stub
        basicForms.add(new Rectangle(beginX, beginY, this.color, e.getX(), e.getY()));
        clearLayer();
        gc.setStroke(Color.BLACK);
        drawAllShape();
    }

    public void addRectListener() {
        this.setOnMousePressed(e -> beginShape(e));
        this.setOnMouseDragged(e -> drawingRect(e));
        this.setOnMouseReleased(e -> endRect(e));
    }

    public ArrayList<BasicForm> getBasicForm() {
        return this.basicForms;
    }

    void drawAllShape() {

        for (BasicForm form : basicForms) {

            if (form instanceof Stroke) {
                gc.setStroke(form.getColor());
                gc.strokeLine(((Stroke) form).getBeginX(), ((Stroke) form).getBeginY(), ((Stroke) form).getEndX(), ((Stroke) form).getEndY());
            } else if (form instanceof Disc) {
                gc.setFill(form.getColor());
                gc.fillOval(form.getX(), form.getY(), ((Disc) form).getRay(), ((Disc) form).getRay());
            } else if (form instanceof Rectangle) {
                System.out.println(form.getColor());
                gc.setFill(form.getColor());
                gc.fillRect(form.getX(), form.getY(), ((Rectangle) form).getWidth(), ((Rectangle) form).getHeight());
            }

        }

    }

    void clearLayer() {
        gc.clearRect(0, 0, this.getWidth(), this.getHeight());
    }

    public void setColor(Color c) {
        this.color = c;
    }

    public void grabFigure(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();

        for (int i = 0; i < this.basicForms.size(); i++) {
            if (this.basicForms.get(i).isInside(x, y) && enableToMove == false) {
                toMove = i;
                enableToMove = true;
                System.out.println("Forme sélectionné");
            }
        }
    }

    private void moveFigure(MouseEvent e) {
        if (enableToMove) {
            clearLayer();
            for (int i = 0; i < this.basicForms.size(); i++)
                if (toMove != i) this.basicForms.get(i).draw(gc);

            this.basicForms.get(toMove).setX(e.getX());
            this.basicForms.get(toMove).setY(e.getY());
            this.basicForms.get(toMove).draw(gc);
        }
    }

    private void dropFigure(MouseEvent e) {
        if (enableToMove) {
            clearLayer();
            for (int i = 0; i < this.basicForms.size(); i++) {
                this.basicForms.get(i).draw(gc);
            }
            enableToMove = false;
            System.out.println("Forme desélectionné");
        }
    }

    public void addMoveListener(){
        System.out.println("Here");
        this.setOnMousePressed(e->this.grabFigure(e));
        this.setOnMouseDragged(e->this.moveFigure(e));
        this.setOnMouseReleased(e->this.dropFigure(e));
    }

    public LayerInfo getInfo() {
        return new LayerInfo(this.name, this.basicForms, this.getWidth(), this.getHeight());
    }


}
