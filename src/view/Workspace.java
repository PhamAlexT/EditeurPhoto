package view;


import javafx.beans.Observable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import model.Layer;

import java.util.ArrayList;
import java.util.Stack;

public class Workspace {
    Main main;//Idée : à la place du main passer le root en param
    
    WorkspaceNavigator navigationInterface;
    Group group; // Pour mettre tout les layers;
    StackPane stackPane;
    ChoiceBox<String> choiceBox;
    
    ArrayList<Layer> layers;
    

    public Workspace(Main main, Image image) {
        this.main = main;

        ImageView imageView = new ImageView(image);
        stackPane = new StackPane(imageView);

        group = new Group(stackPane);
        
        choiceBox = new ChoiceBox<String>();

        layers = new ArrayList<Layer>();
        addNewLayer();
        
        group.getChildren().add(layers.get(0));
        layers.get(0).toFront();
        
        navigationInterface = new WorkspaceNavigator(this);

        addToMain();
    }

    
    public void addToMain() {
        System.out.println("Adding Graphical elements to the main view");
        
        main.addNodeCenter(navigationInterface.getScrollPane()); //On pourrait utiliser un setCenter sur le borderpane root

        HBox bottomBar = new HBox(new Label("Zoom"), navigationInterface.getSlider(), navigationInterface.getScaleFactor(), choiceBox);
		main.addNodeBottom(bottomBar, 0, 0); //On pourrait utiliser un setBottom sur le borderpane root
        
    }

    public void addNewLayer() {
        String name = "Calque " + (layers.size() + 1);
        int width = (int)group.maxWidth(1000);
        int height = (int)group.maxHeight(1000);
        
		Layer newLayer = new Layer(name, width, height);
        
        layers.add(newLayer);
        
        updateChoiceBox(newLayer);

    }


	private void updateChoiceBox(Layer newLayer) {
		choiceBox.getItems().add(newLayer.getName()); //Calque selection
        choiceBox.setValue(newLayer.getName());
	}

    public StackPane getStackPane() {
        return stackPane;
    }

    public Group getGroup() {
        return group;
    }
}
