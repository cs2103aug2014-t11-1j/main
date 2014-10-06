package gui.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PopupController {
	@FXML
	private Label text;
	private Stage popupStage;
	private double xOffset;
	private double yOffset;
	
	@FXML
    private void initialize() {
		System.out.println("initilising popup");
    }

	public void setPopupStage(Stage popupStage) {
		this.popupStage = popupStage;
		popupStage.getScene().setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});
		popupStage.getScene().setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				popupStage.setX(event.getScreenX() - xOffset);
				popupStage.setY(event.getScreenY() - yOffset);
			}
		});
	} 
	
	@FXML
	private void handleExit(){
		popupStage.close();
	}

}
