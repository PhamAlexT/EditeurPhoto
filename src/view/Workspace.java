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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import model.Layer;

import java.util.ArrayList;
import java.util.Stack;

public class Workspace {
    BorderPane root;//Idée : à la place du main passer le root en param
    
    WorkspaceNavigator navigationInterface;
    Group group; // Pour mettre tout les layers;
    StackPane stackPane;
    ChoiceBox<String> choiceBox;
    
    ArrayList<Layer> layers;
    

    public Workspace(BorderPane mainView) {
        this.root = mainView;
        
        choiceBox = new ChoiceBox<String>();

    }

    
    public void addToMain() {
        System.out.println("Adding Graphical elements to the main view");
        
        root.setCenter(navigationInterface.getScrollPane()); //On pourrait utiliser un setCenter sur le borderpane root

        HBox bottomBar = new HBox(new Label("Zoom"), navigationInterface.getSlider(), navigationInterface.getScaleFactor(), choiceBox);
		root.setBottom(bottomBar); //On pourrait utiliser un setBottom sur le borderpane root
        
    }

    
    //That's more of a model function, have to look wheter this can be moved.

    public void addNewLayer() {
        String name = "Calque " + (layers.size() + 1);
        int width = (int)group.maxWidth(1000);
        int height = (int)group.maxHeight(1000);
        
		Layer newLayer = new Layer(name, width, height);
        
        layers.add(newLayer);
        
        updateChoiceBox(newLayer);
        addLayer2View();

    }

    // This should be extracted with the above function into a controller. (Not all the function but there should at least be an update view function in the controlelr)
    // When a new calque is created the controller update the view.
	private void updateChoiceBox(Layer newLayer) {
		choiceBox.getItems().add(newLayer.getName()); //Calque selection
        choiceBox.setValue(newLayer.getName());
	}
	
	
	public void setImage(Image image) {
		ImageView imageView = new ImageView(image);
        stackPane = new StackPane(imageView);
        
        group = new Group(stackPane);
        
        layers = new ArrayList<Layer>();
        addNewLayer();
        
        navigationInterface = new WorkspaceNavigator(this);
        
        addToMain();
	}


	private void addLayer2View() {
		group.getChildren().add(layers.get(0)); //To change for when we have more layer
        layers.get(0).toFront(); //To change for when we have more layer
	}

	
    public StackPane getStackPane() {
        return stackPane;
    }

    
    public Group getGroup() {
        return group;
    }
}
