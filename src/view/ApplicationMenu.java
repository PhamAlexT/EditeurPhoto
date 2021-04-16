package view;

import controller.menu.FileMenuController;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;

public class ApplicationMenu extends MenuBar {
    
	FileMenuController listener;
    
    public ApplicationMenu(Main main){

        Menu fileMenu = new Menu("Fichier");
        Menu editMenu = new Menu("Editer");
        Menu selectionMenu = new Menu("Sélection");
        Menu settingsMenu = new Menu("Paramètres");
        Menu formMenu = new Menu("Formes G�om�triques");
        Menu windowMenu = new Menu("Fen�tre");
        Menu helpMenu = new Menu("Aide");

        
        
    	
        // Menu Fichier
        MenuItem newProject = new MenuItem("Nouveau");
        newProject.setOnAction(e->fileHandler("newFile"));

        MenuItem openProject = new MenuItem("Ouvrir");
        openProject.setOnAction(e->fileHandler("openProject"));

        MenuItem saveProject = new MenuItem("Enregistrer");
        saveProject.setOnAction(e->fileHandler("saveProject"));

        MenuItem saveProjectAs = new MenuItem("Enregistrer sous");
        saveProjectAs.setOnAction(e->fileHandler("saveProjectAs"));
        
        fileMenu.getItems().addAll(newProject, openProject, saveProject,saveProjectAs);
        
        
        //Menu Formes G�om�triques
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
    
    public void addListener(FileMenuController list) {
    	this.listener = list;
    }
    
    private void fileHandler(String action) {
    	switch(action) {
    		case "newFile":
    			listener.newFile();
    			break;
    		case "openProject":
    			listener.openProject();
    			break;
    		case "saveProject":
    			listener.saveProject();
    			break;
    		case "saveProjectAs":
    			listener.saveProjectAs();
    			break;
    	}
    }

}
