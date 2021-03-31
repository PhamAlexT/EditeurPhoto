package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    Scene scene;
    GraphicsContext gc;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setMaximized(true);
        VBox vbox = new VBox(new ApplicationMenu());

        primaryStage.setTitle("Hello World");

        scene = new Scene(vbox);
        primaryStage.setScene(scene);

        System.out.println(scene.toString());
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
