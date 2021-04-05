package view;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.stage.Screen;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    Scene scene;
    BorderPane pane;
    GraphicsContext gc;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setMaximized(true);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        System.out.println(primaryScreenBounds.getHeight());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());

        pane = new BorderPane();
        pane.setTop(new ApplicationMenu(this));

        StackPane spl = new StackPane();
        StackPane spr = new StackPane();

        pane.setLeft(spl);
        pane.setRight(spr);

        System.out.println(primaryStage.getWidth());
        spr.getChildren().add(new Button("Testr"));
        spl.getChildren().add(new Button("Testl"));
        primaryStage.setTitle("Photo Editor");

        StackPane p = new StackPane();

        p.setPrefSize(1600,1000); //set a default size for your stackpane
        Image img = new Image("https://upload.wikimedia.org/wikipedia/commons/c/c7/Code_Geass_logo.jpg"); //create an image
        ImageView v = new ImageView(img); //create an imageView and pass the image

        p.getChildren().add(v); //add imageView to stackPane
        p.setAlignment(v,Pos.CENTER);



        ScrollPane scrollImgViewer = new ScrollPane();
        scrollImgViewer.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollImgViewer.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollImgViewer.setContent(p);
        pane.setCenter(scrollImgViewer);

        GridPane bottomBar = new GridPane();
        Slider scaling = new Slider(0,1,0.5);
        scaling.setShowTickLabels(true);
        scaling.setMajorTickUnit(0.25f);
        scaling.setBlockIncrement(0.1f);
        bottomBar.getChildren().add(scaling);
        bottomBar.setAlignment(Pos.CENTER_RIGHT);
        pane.setBottom(bottomBar);
        scaling.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                v.setScaleX(new_val.doubleValue());
                v.setScaleY(new_val.doubleValue());
            }
        });



        scene = new Scene(pane);
        primaryStage.setScene(scene);


        primaryStage.show();


    }

    public BorderPane getPane() {
        return pane;
    }
}
