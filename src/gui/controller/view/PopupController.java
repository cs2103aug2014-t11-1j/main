package gui.controller.view;
/**
 * @author A0116018R
 * Unused: not fully developed.
 * This class is the controller for 
 * the popup view.
 */
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PopupController {
	@FXML
	private Label text;
	
	private Stage popupStage_;
	private double xOffset_;
	private double yOffset_;
	
	@FXML
    private void initialize() {
		System.out.println("initilising popup");
    }

	public void setPopupStage(Stage popupStage) {
		this.popupStage_ = popupStage;
		popupStage.getScene().setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset_ = event.getSceneX();
				yOffset_ = event.getSceneY();
			}
		});
		popupStage.getScene().setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				popupStage.setX(event.getScreenX() - xOffset_);
				popupStage.setY(event.getScreenY() - yOffset_);
			}
		});
	} 
	
	@FXML
	private void handleExit(){
		popupStage_.close();
	}

}
