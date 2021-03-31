package view;




import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import model.Project;
public class Workspace {
    Project p;
    Image img;
    Pane pane;
    ImageView iw;
    public Workspace(Image im){
        this.img = im;
        iw = new ImageView(im);
    }

    public ImageView getIW(){
        return this.iw;
    }
}
