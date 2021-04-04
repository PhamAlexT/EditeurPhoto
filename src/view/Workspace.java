package view;




import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import model.Project;

public class Workspace {
    Project p;
    Image img;
    Pane pane;
    ImageView iw;
    double beginX;
    double beginY;

    public Workspace(Image im){
        this.img = im;
        iw = new ImageView(im);

        iw.setOnMousePressed(e->mouseTranslation(e));
        iw.setOnMouseReleased(e-> mouseTranslation2(e));
    }

    public ImageView getIW(){
        return this.iw;
    }


    void mouseTranslation(MouseEvent e){

    }

    void mouseTranslation2(MouseEvent e){

    }

}
