package view;

import controller.ApplicationMenu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    Scene scene;
    GraphicsContext gc;

    @Override
    public void start(Stage primaryStage) throws Exception{
        MenuBar menuBar = new ApplicationMenu();

        VBox vbox = new VBox(menuBar);


        primaryStage.setTitle("Hello World");




        scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
