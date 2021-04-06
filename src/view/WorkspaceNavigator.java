package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;

import java.text.DecimalFormat;
import java.text.ParsePosition;

public class WorkspaceNavigator {

    private ScrollPane scrollPane;
    private Slider slider;
    private Workspace workspace;
    private TextField scaleFactor;

    public WorkspaceNavigator(Workspace wp){
        this.workspace = wp;

        initScrollPane();
        initScaling();
    }

    public void initScrollPane(){
        scrollPane = new ScrollPane(workspace.getGroup());
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
    }

    public void initScaling(){
        slider = new Slider(0,5,1);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(0.25f);
        slider.setBlockIncrement(0.1f);

        //Pour scale avec une valeur numérique
        scaleFactor = new TextField(Double.toString(slider.getValue()));
        //TODO:Pour permettre seulement les valeurs doubles


        //Si on change le slider, on change le Texfield affichant le facteur de scaling
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {

                //workspace.getStackPane().setScaleX(new_val.doubleValue());
                //workspace.getStackPane().setScaleY(new_val.doubleValue());
                scaleFactor.textProperty().setValue(Double.toString(new_val.doubleValue()));
            }
        });
        //TODO: Actualisation à la saisie
        scaleFactor.textProperty().addListener((observable, oldValue, newValue) -> {
            workspace.getStackPane().setScaleX(slider.getValue());
            workspace.getStackPane().setScaleY(slider.getValue());

        });
    }

    public ScrollPane getScrollPane(){
        return scrollPane;
    }

    public Slider getSlider() {
        return slider;
    }

    public TextField getScaleFactor(){
        return this.scaleFactor;
    }
}
