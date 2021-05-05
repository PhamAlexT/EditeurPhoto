package view;


import controller.FilterController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Layer;
import model.LayerInfo;
import model.filters.ComplexFilter;
import model.filters.Filter;
import model.filters.SimpleFilter;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Workspace {
    BorderPane root;//Idée : à la place du main passer le root en param

    WorkspaceNavigator navigationInterface;
    Group group; // Pour mettre tout les layers;
    StackPane stackPane;
    Group buffer; // Used when modification
    private ChoiceBox<String> choiceBox;
    private ImageView imageView = new ImageView();
    private Image imgSource;
    private ArrayList<Layer> layers;
    private Button addLayer;
    private ColorPicker colorPicker;

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

        Button move = new Button("Sélection et déplacement");


        container.getChildren().add(shapes);
        move.setOnAction(e->this.getCurrentLayer().addMoveListener());

        container.getChildren().add(new Label("Couleur"));
        container.getChildren().add(move);
        move.setMaxWidth(Double.MAX_VALUE);

        colorPicker = new ColorPicker();
        colorPicker.valueProperty().addListener(e -> transmitColorToCurrentLayer());
        colorPicker.setValue(Color.BLACK);
        container.getChildren().addAll(colorPicker);

        VBox listOfFilers = new VBox();

        //VBox paramsFilter = new VBox(new Label("Appliquer sur toutes l'images"));
        container.getChildren().addAll(new Label("Filtres"), listOfFilers);

        FilterController fc = new FilterController(this);

        for (Filter f : fc.getFilters()) {
            if (f.getName().equals("Inverser")) {
                listOfFilers.getChildren().add(new Label("Filtre simple"));
            } else if (f.getName().equals("Noir et blanc")) {
                listOfFilers.getChildren().add(new Label("Filtres réglables"));
            }

            Button b = new Button(f.getName());

            listOfFilers.getChildren().add(b);
            b.setMaxWidth(Double.MAX_VALUE);
            if (f instanceof SimpleFilter) {
                b.setOnAction(e ->
                {
                    changeImage(((SimpleFilter) f).apply(this.imageView.getImage()));
                    //changeImage( (((SimpleFilter) f).apply(this.imgSource)));
                });
            } else if (f instanceof ComplexFilter) {
                b.setOnAction(e -> {
                    Image backup = this.imgSource;
                    String title = "Paramètres du filtre: " + f.getName();

                    Slider s = new Slider(((ComplexFilter) f).getVmin(), ((ComplexFilter) f).getVmax(), ((ComplexFilter) f).getAverage());
                    s.setShowTickLabels(true);
                    s.setMajorTickUnit(0.25f);
                    s.setBlockIncrement(0.1f);

                    s.valueProperty().addListener(ev -> {
                        changeImage(((ComplexFilter) f).apply(this.imageView.getImage(), s.getValue()));
                    });

                    Button validate = new Button("Valider");
                    Button cancel = new Button("Annuler");
                    HBox buttonChoice = new HBox(cancel, validate);

                    VBox layoutWindow = new VBox(new Label("Paramètre du filtre:"));
                    layoutWindow.getChildren().addAll(s, buttonChoice);

                    Scene scene = new Scene(layoutWindow);
                    Stage newWindow = new Stage();
                    newWindow.setTitle(title);
                    newWindow.setScene(scene);
                    newWindow.initModality(Modality.WINDOW_MODAL);
                    newWindow.initOwner(this.root.getScene().getWindow());
                    newWindow.setHeight(120);
                    newWindow.setWidth(400);
                    newWindow.show();
                    Stage stageToClose = (Stage) validate.getScene().getWindow();

                    validate.setOnAction(ev -> {
                        stageToClose.close();
                    });

                    cancel.setOnAction(ev -> {
                        this.imgSource = backup;
                        stageToClose.close();
                    });
                });
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
