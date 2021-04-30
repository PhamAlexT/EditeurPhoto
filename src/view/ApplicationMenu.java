package view;

import controller.menu.FileMenuController;
import controller.menu.FilterMenu;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;

public class ApplicationMenu extends MenuBar {
    
	FileMenuController listener;
    
    public ApplicationMenu(Main main){

        Menu fileMenu = new Menu("Fichier");
        Menu editMenu = new Menu("Editer");
        Menu filtersMenu = new Menu("Filtres");
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

      //Filtres
        new FilterMenu().getFilters().forEach(filter -> {
            MenuItem item = new MenuItem(filter.getName());
            /*
            item.setOnAction(e->{
                listener.changeImage(filter.apply(listener.getImage()));
            });
             */
            filtersMenu.getItems().add(item);
        });

        this.getMenus().addAll(fileMenu,editMenu,filtersMenu,helpMenu);

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
