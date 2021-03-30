package controller;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class ApplicationMenu extends MenuBar {
    public ApplicationMenu(){
        Menu fileMenu = new Menu("Fichier");
        Menu editMenu = new Menu("Editer");
        Menu selectionMenu = new Menu("Sélection");
        Menu settingsMenu = new Menu("Paramètres");
        Menu windowMenu = new Menu("Fenêtre");
        Menu helpMenu = new Menu("Aide");

        this.getMenus().addAll(fileMenu,editMenu,selectionMenu,settingsMenu,windowMenu,helpMenu);

    }
}
