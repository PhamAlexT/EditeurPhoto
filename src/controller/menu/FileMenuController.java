package controller.menu;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import model.Layer;
import model.Project;
import view.ApplicationMenu;
import view.Workspace;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class FileMenuController {
	
	private ApplicationMenu am;
	private Workspace ws;

	private FileOutputStream fop;

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
        fc.setTitle("Ouvrir un projet");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Projet BPEP (*.bpep)","*.bpep"));
        
        File file = fc.showOpenDialog(am.getScene().getWindow());
        if (file != null){
            //TODO: Décomposer les étapes de re-construction
            // Renseigner fop
            // Reconstruire les layers
            Project p = Project.fileToProject(file);

        }
    }
    

    public void saveProject(){
        if (fop == null) saveProjectAs();
        else {
            //TODO: Effacer le contenu du fichier et tout re-écrire
        }
    }


    public void saveProjectAs(){
        FileChooser fc = new FileChooser();
        fc.setTitle("Enregistrer sous");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Projet BPEP (*.bpep)","*.bpep"));
        
        File file = fc.showSaveDialog(am.getScene().getWindow());

        Image img = this.ws.getImgSource();
        int compteur = 0;
        if (file != null) {
            try {
                fop = new FileOutputStream(file);

                ObjectOutputStream oos = new ObjectOutputStream(fop);

                //Ecrire l'image
                ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", oos);

                //Ecrire les arrays de BasicForm des différents calques
                for (Layer l : this.ws.getLayers()) {
                    oos.writeObject(l.getBasicForm());
                    compteur++;
                }

                oos.close();

                System.out.println("Enregistrement terminé avec " + compteur + " layers.");
            } catch (Exception e) {
                System.out.println(e.getClass().getName());
            }
        }
    }
    
    
    public void changeImage(Image img) {
    	ws.changeImage(img);
    }
    
    public void setDrawMode(String newMode) {
    	ws.setDrawMode(newMode);
    }
    
    public Image getImage() {
    	return ws.getImgSource();
    }
    
}
