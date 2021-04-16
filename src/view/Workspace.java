package view;


import javafx.beans.Observable;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
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
    private ChoiceBox<String> choiceBox;
    private ImageView imageView = new ImageView();
    private Image imgSource;
    private ArrayList<Layer> layers;
    private Button addLayer;
    

    public Workspace(BorderPane mainView) {
        this.root = mainView;
        
        choiceBox = new ChoiceBox<String>();
        
        addLayer = new Button("Add Layer");
        addLayer.setOnAction(e -> addNewLayer());

    }

    
    public void addToMain() {
        System.out.println("Adding Graphical elements to the main view");
        
        root.setCenter(navigationInterface.getScrollPane()); //On pourrait utiliser un setCenter sur le borderpane root

        HBox bottomBar = new HBox(new Label("Zoom"), navigationInterface.getSlider(), navigationInterface.getScaleFactor(), choiceBox, addLayer);
		root.setBottom(bottomBar); //On pourrait utiliser un setBottom sur le borderpane root
        
    }

    public void addNewLayer() {
        String name = "Calque " + (layers.size() + 1);
        int width = (int)group.maxWidth(1000);
        int height = (int)group.maxHeight(1000);
        
		Layer newLayer = new Layer(name, width, height);
        
        layers.add(newLayer);
        
        updateChoiceBox(newLayer);
        addLayer2View();

    }

    
    private void addLayer2View() {
    	int newLayerPos = layers.size() - 1;
    	Layer newLayer = layers.get(newLayerPos);
    	
		group.getChildren().add(newLayer); //To change for when we have more layer
        layers.get(newLayerPos).toFront(); //To change for when we have more layer
	}
    
    
	private void updateChoiceBox(Layer newLayer) {
		choiceBox.getItems().add(newLayer.getName()); //Calque selection
        choiceBox.setValue(newLayer.getName());
	}
	
	
	public void setImage(Image image) {
		imgSource = image;
		imageView.setImage(image);
        stackPane = new StackPane(imageView);
        
        group = new Group(stackPane);
        
        layers = new ArrayList<Layer>();
        addNewLayer();
        
        navigationInterface = new WorkspaceNavigator(this);
        
        addToMain();
	}

	
    public StackPane getStackPane() {
        return stackPane;
    }

    
    public Group getGroup() {
        return group;
    }
    
    
    public void changeImage(Image img) {
    	this.imageView.setImage(img);
    }
    
    
    public Image getImage() {
    	return this.imgSource;
    }
}
