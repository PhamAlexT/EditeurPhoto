package controller.menu;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import model.Project;
import view.ApplicationMenu;
import view.Workspace;

import java.io.File;

public class FileMenuController {
	
	private ApplicationMenu am;
	private Workspace ws;
	
    public FileMenuController(ApplicationMenu am, Workspace ws) {
    		this.am = am;
    		this.ws = ws;
    }
    

    public void newFile(){
    	
        FileChooser fc = new FileChooser();
        
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        
        fc.setTitle("Nouveau projet Ã  partir d'une image");
        
        File file = fc.showOpenDialog(this.am.getScene().getWindow());
        if (file != null){
            System.out.println("On crÃ©er un projet et on affiche la vue");
            Image img = new Image(file.toURI().toString());
            ws.setImage(img);
        }
    }
    

    public void openProject(){
    	
        FileChooser fc = new FileChooser();
        
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Projet","*.extension"));
        
        fc.setTitle("Ouvrir un projet");
        
        File file = fc.showOpenDialog(am.getScene().getWindow());
        if (file != null){
            Project p = Project.fileToProject(file);

        }
    }
    

    public void saveProject(){
        //TODO: Compléter
    }


    public void saveProjectAs(){
        FileChooser fc = new FileChooser();
        
        fc.setTitle("Enregistrer sous");
        
        File file = fc.showSaveDialog(am.getScene().getWindow());
    }
    
    
    public void changeImage(Image img) {
    	ws.changeImage(img);
    }
    
    public Image getImage() {
    	return ws.getImage();
    }
    
}
