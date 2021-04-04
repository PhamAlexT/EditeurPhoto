package view;

import controller.menu.FileMenuController;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class ApplicationMenu extends MenuBar {
    Main main;
    public ApplicationMenu(Main main){
        this.main = main;

        Menu fileMenu = new Menu("Fichier");
        Menu editMenu = new Menu("Editer");
        Menu selectionMenu = new Menu("Sélection");
        Menu settingsMenu = new Menu("Paramètres");
        Menu windowMenu = new Menu("Fenêtre");
        Menu helpMenu = new Menu("Aide");

        FileMenuController fm = new FileMenuController(this);
        // Menu Fichier
        MenuItem newProject = new MenuItem("Nouveau");
        newProject.setOnAction(e->fm.newFile());

        MenuItem openProject = new MenuItem("Ouvrir");
        openProject.setOnAction(e->fm.openProject());

        MenuItem saveProject = new MenuItem("Enregistrer");
        saveProject.setOnAction(e->fm.saveProject());

        MenuItem saveProjectAs = new MenuItem("Enregistrer sous");
        saveProjectAs.setOnAction(e->fm.saveProjectAs());

        fileMenu.getItems().addAll(newProject, openProject, saveProject,saveProjectAs);


        this.getMenus().addAll(fileMenu,editMenu,selectionMenu,settingsMenu,windowMenu,helpMenu);

    }

    public Main getMain(){
        return main;
    }

}
