package gui.controller.view;

/**
 * @author A0116018R
 * 
 * Unused: not fully developed
 * 
 * This class manipulates the components in tutorialView.
 * User input is set to this commandLine on start-up.
 */

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class TutorialViewController {
	private AnchorPane tutorialView_;
	private AnchorPane overallView_;

	private boolean isWaitForInput_;
	private int currentStage_;

	private final static int STAGE_ADD_1 = 1;
	private final static int STAGE_ADD_2 = 2;

	@FXML
	private Label label1;
	@FXML
	private Label label2;
	@FXML
	private Label label3;
	@FXML
	private Label label4;

	@FXML
	private TextField commandLine;

	@FXML
	private void handleKeyPressed(KeyEvent e){
		String input;
		if(e.getCode() == KeyCode.ENTER){
			input = commandLine.getText();
			commandLine.clear();

			if(input.equalsIgnoreCase("quit")){
				quitTutorial();
			}
			else if(isWaitForInput_){
				if(isCorrectInput(input)){
					isWaitForInput_ = false;
					currentStage_++;
					runNextStage(input);
				}
				else{
					runRepeatInstructions();
				}
			}
			else{
				//do nothing as tutorial is being shown
			}
		}
	}

	@FXML
	private void initialize() {	
		//on startup, set user input into this commandLine
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				commandLine.requestFocus();
			}
		});
		isWaitForInput_ = false;
		currentStage_ = STAGE_ADD_1;
		runAdd1();
	}

	public void setTutorialView(AnchorPane tutorialView) {
		this.tutorialView_ = tutorialView;
	}

	public void setOverallView(AnchorPane overallView){
		this.overallView_ = overallView;
	}


	private boolean isCorrectInput(String input) {
		switch(currentStage_){
		case STAGE_ADD_1:
			return input.equalsIgnoreCase("add world domination");
		default:
		}
		return false;
	}

	private void runNextStage(String input) {
		System.out.println("run next stage: " + currentStage_);
		switch(currentStage_){
		case STAGE_ADD_1:
			runAdd1();
			break;
		case STAGE_ADD_2:
			runAdd2();
			break;
		default:
			quitTutorial();
		}		
	}

	private void runAdd1(){
		label1.setText("Hello, you seem a bit lost.");
		label2.setText("Don't panic, I'm here to help.");
		label3.setText("I'm here to help a busy soul like you manage your piorities");
		label4.setText("Try typing: add world domination");
		isWaitForInput_ = true;
	}

	private void runAdd2(){
		label1.setText("");
		label2.setText("");
		label3.setText("");
		label4.setText("");
		tutorialView_.getChildren().get(0).setOpacity(0.0);
	}

	private void runRepeatInstructions() {
		System.out.println("repeating instructions");
	}
	

	private void quitTutorial() {
		overallView_.getChildren().remove(tutorialView_);
	}
	

}
