package view;


import controller.FilterController;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Layer;
import model.LayerInfo;
import model.filters.Filter;
import model.filters.SimpleFilter;

import java.util.ArrayList;
import java.util.NoSuchElementException;

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
    private ColorPicker colorPicker;
    Group buffer; // Used when modification

    public Workspace(BorderPane mainView) {
        this.root = mainView;

        choiceBox = new ChoiceBox<String>();

        addLayer = new Button("Add Layer");
        addLayer.setOnAction(e -> addNewLayer());

    }

    public void addToMain() {
        System.out.println("Adding Graphical elements to the main view");

        root.setCenter(navigationInterface.getScrollPane());

        HBox bottomBar = new HBox(new Label("Zoom"), navigationInterface.getSlider(), navigationInterface.getScaleFactor(), choiceBox, addLayer);
        root.setBottom(bottomBar);

        addToMainLeftPanel();
    }

    public void addToMainLeftPanel() {
        VBox container = new VBox();

        container.getChildren().add(new Label("Dessin"));
        HBox shapes = new HBox();

        Button stroke = new Button("Stroke");
        stroke.setOnAction(e -> this.getCurrentLayer().addStrokeListener());

        Button rectangle = new Button("Rectangle");
        rectangle.setOnAction(e -> this.getCurrentLayer().addRectListener());

        Button disc = new Button("Cercle");
        disc.setOnAction(e -> this.getCurrentLayer().addDiscListener());

        shapes.getChildren().addAll(stroke, rectangle, disc);
        container.getChildren().add(shapes);

        container.getChildren().add(new Label("Couleur"));
        colorPicker = new ColorPicker();
        colorPicker.valueProperty().addListener(e -> transmitColorToCurrentLayer());
        colorPicker.setValue(Color.BLACK);
        container.getChildren().addAll(colorPicker);

        VBox listOfFilers = new VBox();
        container.getChildren().addAll(new Label("Filtres" ),listOfFilers);
        FilterController fc = new FilterController(this);

        for (Filter f: fc.getFilters()){
            Button b = new Button(f.getName());
            listOfFilers.getChildren().add(b);
            System.out.println();
            if (f instanceof SimpleFilter){
                System.out.println(f.getName());
                b.setOnAction(e->
                        {
                        changeImage( (((SimpleFilter) f).apply(this.imgSource)));
                        });
            } else {
                System.out.println("TODO");
            }
        }

        root.setLeft(container);
    }

    public void addNewLayer() {
        String name = "Calque " + (layers.size() + 1);
        int width = (int) group.maxWidth(1000);
        int height = (int) group.maxHeight(1000);

        Layer newLayer = new Layer(name, width, height);

        layers.add(newLayer);

        updateChoiceBox(newLayer);
        addLayer2View();
    }


    private void addLayer2View() {
        int newLayerPos = layers.size() - 1;
        Layer newLayer = layers.get(newLayerPos);

        group.getChildren().add(newLayer);
        layers.get(newLayerPos).toFront();
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

        choiceBox.setOnAction(e -> {

            int selectedIndex = choiceBox.getSelectionModel().getSelectedIndex();
            System.out.println("Index: " + selectedIndex);
            layers.get(selectedIndex).toFront();
            ;
        });

        navigationInterface = new WorkspaceNavigator(this);

        addToMain();
    }

    public void setDrawMode(String newMode) {
        Layer currentLayer = layers.get(layers.size() - 1);
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

    public Image getImgSource() {
        return this.imgSource;
    }

    public ArrayList<Layer> getLayers() {
        return layers;
    }

    public Layer getCurrentLayer() {
        for (Layer l : layers) {
            if (l.getName() == choiceBox.getValue()) return l;
        }
        throw new NoSuchElementException();
    }

    private void transmitColorToCurrentLayer() {
        this.getCurrentLayer().setColor(colorPicker.getValue());
    }

    public void openingProject(Image img, ArrayList<LayerInfo> array) {
        imgSource = img;
        imageView.setImage(img);

        stackPane = new StackPane(imageView);
        group = new Group(stackPane);

        layers = new ArrayList<Layer>();
        choiceBox = new ChoiceBox<>();

        for (LayerInfo li : array) {
            Layer l = new Layer(li);
            layers.add(l);
            choiceBox.getItems().add(l.getName());
            choiceBox.setValue(l.getName());
            System.out.println("Nom du layer: " + l.getName());
        }

        group.getChildren().addAll(layers);

        choiceBox.setOnAction(e -> {
            System.out.println("Added: " + getCurrentLayer().getName());
            getCurrentLayer().toFront();
            ;
        });

        navigationInterface = new WorkspaceNavigator(this);

        addToMain();

    }

}
