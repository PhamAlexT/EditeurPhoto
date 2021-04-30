package model;

import model.forms.BasicForm;

import java.io.Serializable;
import java.util.ArrayList;

public class LayerInfo implements Serializable {
    String name;
    ArrayList<BasicForm> basicForms;
    double width;
    double height;

    public LayerInfo(String name, ArrayList<BasicForm> basicForms, double width, double height){
        this.name = name;
        this.basicForms = basicForms;
        this.width = width;
        this.height = height;
    }
}
