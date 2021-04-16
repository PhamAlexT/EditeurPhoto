package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;

import java.text.DecimalFormat;
import java.text.ParsePosition;

public class WorkspaceNavigator {

	private ScrollPane scrollPane;
	private Slider scaler;
	private Workspace workspace;
	private TextField scaleFactor;

	public WorkspaceNavigator(Workspace wp) {
		this.workspace = wp;

		createScrollPane();
		createScaler();
		handleScaler();
	}

	public void createScrollPane() {
		scrollPane = new ScrollPane(workspace.getGroup());
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		scrollPane.setFitToWidth(true);
		scrollPane.setFitToHeight(true);
	}

	public void createScaler() {
		scaler = new Slider(0, 5, 1);
		scaler.setShowTickLabels(true);
		scaler.setMajorTickUnit(0.25f);
		scaler.setBlockIncrement(0.1f);

		// Pour scale avec une valeur numerique
		scaleFactor = new TextField(Double.toString(scaler.getValue()));

	}

	private void handleScaler() {
		scaler.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				scaleFactor.textProperty().setValue(Double.toString(new_val.doubleValue()));
			}
		});
		
		// Si on change le slider, on change le Texfield affichant le facteur de scaling
		// TODO: Actualisation à la saisie
		scaleFactor.textProperty().addListener((observable, oldValue, newValue) -> {
			workspace.getStackPane().setScaleX(scaler.getValue());
			workspace.getStackPane().setScaleY(scaler.getValue());
		});
	}

	public ScrollPane getScrollPane() {
		return scrollPane;
	}

	public Slider getSlider() {
		return scaler;
	}

	public TextField getScaleFactor() {
		return this.scaleFactor;
	}
}
