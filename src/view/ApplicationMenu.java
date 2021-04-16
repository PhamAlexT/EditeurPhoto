package view;

import controller.menu.FileMenuController;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;

public class ApplicationMenu extends MenuBar {
    Main main;
    public ApplicationMenu(Main main){
        this.main = main;

        Menu fileMenu = new Menu("Fichier");
        Menu editMenu = new Menu("Editer");
        Menu selectionMenu = new Menu("SÃ©lection");
        Menu settingsMenu = new Menu("ParamÃ¨tres");
        Menu formMenu = new Menu("Formes Géométriques");
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
        
        
        //Menu Formes Géométriques
        ToggleGroup toggleGroup = new ToggleGroup();
        
        CheckMenuItem line = new CheckMenuItem("Ligne");
        //line.setToggleGroup(toggleGroup);
        
        CheckMenuItem rectangle = new CheckMenuItem("Rectangle");
        //rectangle.setToggleGroup(toggleGroup);
        
        CheckMenuItem cercle = new CheckMenuItem("Cercle");
        //cercle.setToggleGroup(toggleGroup);
        
        
        formMenu.getItems().addAll(line, rectangle, cercle);

        this.getMenus().addAll(fileMenu,editMenu,selectionMenu,settingsMenu,formMenu,windowMenu,helpMenu);

    }

    public Main getMain(){
        return main;
    }

}
