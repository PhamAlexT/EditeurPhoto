package view;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    Scene scene;
    BorderPane root;
    GraphicsContext gc;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setMaximized(true);

        root = new BorderPane();
        root.setTop(new ApplicationMenu(this));

        StackPane spl = new StackPane(new Button("Test"));
        StackPane spr = new StackPane();

        root.setLeft(spl);
        root.setRight(spr);

        primaryStage.setTitle("Photo Editor");

        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void addNodeCenter(Node n){
        root.setCenter(n);
    }

    public void addNodeBottom(Node n, Pos pos){
        root.setBottom(n);
    }
}
