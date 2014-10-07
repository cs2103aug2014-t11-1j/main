package gui.controller;

/**
 * This class manipulates the components in tutorialView.
 * User input is set to current commandLine on start-up.
 * 
 * Author: Krystal
 */

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class TutorialViewController {
	private AnchorPane tutorialView;
	private AnchorPane overallView;

	private boolean isWaitForInput;
	private int currentStage;

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
				overallView.getChildren().remove(tutorialView);
			}
			else if(isWaitForInput){
				if(isCorrectInput(input)){
					isWaitForInput = false;
					currentStage++;
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
		isWaitForInput = false;
		currentStage = STAGE_ADD_1;
		runAdd1();
	}

	public void setTutorialView(AnchorPane tutorialView) {
		this.tutorialView = tutorialView;
	}

	public void setOverallView(AnchorPane overallView){
		this.overallView = overallView;
	}

	private void runAdd1(){
		label1.setText("Hello, you seem a bit lost.");
		label2.setText("Don't panic, I'm here to help.");
		label3.setText("I'm here to help a busy soul like you manage your piorities");
		label4.setText("Try typing: add world domination");
		isWaitForInput = true;
	}

	private boolean isCorrectInput(String input) {
		switch(currentStage){
		case STAGE_ADD_1:
			return input.equalsIgnoreCase("add world domination");
		default:
		}
		return false;
	}

	private void runNextStage(String input) {
		System.out.println("run next stage: " + currentStage);
		label1.setText("");
		label2.setText("");
		label3.setText("");
		label4.setText("");
	}

	private void runRepeatInstructions() {
		System.out.println("repeating instructions");
	}

}
