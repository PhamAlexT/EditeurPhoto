package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    Scene scene;
    BorderPane pane;
    GraphicsContext gc;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setMaximized(true);

        pane = new BorderPane();
        pane.setTop(new ApplicationMenu(this));
        //pane.setCenter(new StackPane());

        primaryStage.setTitle("Photo Editor");

        scene = new Scene(pane);
        primaryStage.setScene(scene);

        System.out.println(scene.toString());
        primaryStage.show();


    }

    public BorderPane getPane(){
        return pane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
