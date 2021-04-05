package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;

public class WorkspaceNavigator {

    private ScrollPane scrollPane;
    private Slider slider;
    private Workspace workspace;

    public WorkspaceNavigator(Workspace wp){
        this.workspace = wp;

        initScrollPane();
        initSlider();
    }

    public void initScrollPane(){
        scrollPane = new ScrollPane(workspace.getGroup());
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
    }

    public void initSlider(){
        slider = new Slider(0,5,1);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(0.25f);
        slider.setBlockIncrement(0.1f);

        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {

                workspace.getStackPane().setScaleX(new_val.doubleValue());
                workspace.getStackPane().setScaleY(new_val.doubleValue());
            }
        });
    }

    public ScrollPane getScrollPane(){
        return scrollPane;
    }

    public Slider getSlider() {
        return slider;
    }
}
