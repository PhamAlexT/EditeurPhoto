package view;


import controller.menu.FileMenuController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    Scene scene;
    BorderPane root;
    GridPane bottomArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)  {
        primaryStage.setMaximized(true);

        root = new BorderPane();
        Workspace ws = new Workspace(root);
        ApplicationMenu am = new ApplicationMenu(this);
        FileMenuController fm = new FileMenuController(am, ws);
        am.addListener(fm);
        root.setTop(am);

        StackPane spr = new StackPane();

        root.setRight(spr);

        bottomArea = new GridPane();

        root.setBottom(bottomArea);

        primaryStage.setTitle("Photo Editor");
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
