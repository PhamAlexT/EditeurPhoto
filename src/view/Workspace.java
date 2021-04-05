package view;




import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.util.Stack;

public class Workspace {
    Main main;
    Image image;
    ImageView imageView;
    Group group; // Pour mettre tout les layers;
    StackPane stackPane;

    WorkspaceNavigator navigationInterface;

    public Workspace(Main main,Image image){
        this.main = main;
        this.image = image;

        imageView = new ImageView(image);
        stackPane = new StackPane(imageView);
        stackPane.setAlignment(imageView,Pos.CENTER);
        group = new Group(stackPane);
        navigationInterface = new WorkspaceNavigator(this);

        linkToMain();
    }

    //TODO: Lier les différents outils de navigation à la fenêtre principale
    public void linkToMain(){
        System.out.println("On a link");
        main.addNodeCenter(navigationInterface.getScrollPane());
        main.addNodeBottom(navigationInterface.getSlider(),null);
    }

    public StackPane getStackPane() {
        return stackPane;
    }

    public Group getGroup(){
        return group;
    }
}
