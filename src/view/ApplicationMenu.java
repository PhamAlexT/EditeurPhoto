package view;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import model.Project;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ApplicationMenu extends MenuBar {
    public ApplicationMenu(){
        Menu fileMenu = new Menu("Fichier");
        Menu editMenu = new Menu("Editer");
        Menu selectionMenu = new Menu("Sélection");
        Menu settingsMenu = new Menu("Paramètres");
        Menu windowMenu = new Menu("Fenêtre");
        Menu helpMenu = new Menu("Aide");

        // Menu Fichier
        MenuItem newProject = new MenuItem("Nouveau");


        newProject.setOnAction(e->{

            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Images", "*.*"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png")
            );

            fc.setTitle("Nouveau projet à partir d'une image");
            File file = fc.showOpenDialog(this.getScene().getWindow());
            if (file != null){
                System.out.println(file.toURI().toString());
                Workspace wp = new Workspace(new Image(file.toURI().toString()));
                ImageView iw = wp.getIW();
                ((VBox) this.getScene().getRoot()).getChildren().add(iw);

            }
        });

        MenuItem saveProject = new MenuItem("Enregistrer");

        MenuItem saveProjectAs = new MenuItem("Enregistrer sous");

        fileMenu.getItems().addAll(newProject, saveProject,saveProjectAs);


        this.getMenus().addAll(fileMenu,editMenu,selectionMenu,settingsMenu,windowMenu,helpMenu);

    }
}
