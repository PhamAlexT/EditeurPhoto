package view;

import controller.menu.FileMenuController;
import controller.FilterController;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

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

        MenuItem printing = new MenuItem("Imprimer");
        printing.setOnAction(e->fileHandler("printing"));

        MenuItem export = new MenuItem("Exporter");
        export.setOnAction(e->fileHandler("export"));

        fileMenu.getItems().addAll(newProject, openProject, saveProject,saveProjectAs,printing,export);

      //Filtres
        new FilterController().getFilters().forEach(filter -> {
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
            case "printing":
                listener.printImage();
                break;
            case "export":
                listener.exportImage();
                break;
        }
    }

}
