package model;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

public class Project {
    Image img;
    ArrayList<Layer> listOfLayer;


    public Project(){

    }


    public void toFile(String path){

    }

    public static Project fileToProject(File file){
        Project createdProject = new Project();




        return createdProject;
    }
}
