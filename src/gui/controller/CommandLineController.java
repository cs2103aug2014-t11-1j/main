package gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class CommandLineController{
	@FXML
	private TextField commandLine;
	
	private LogicFacadeDummy logicFacade;
	
	public CommandLineController(){
		System.out.println("cmd constructor");
	}
	
	@FXML
	private void initialize() {
		System.out.println("cmd initilising");
	}
	
	@FXML
	private void handleKeyPressed(KeyEvent e){
		if(e.getCode() == KeyCode.ENTER){
			String input = commandLine.getText();
			commandLine.clear();
			//logicFacade.getFeedback(input);
		}
	}
	
	public void printStuff(){
		System.out.println("pringting cmd");
	}

	public void setLogicFacade(LogicFacadeDummy logicFacade) {
		this.logicFacade = logicFacade;	
	}
	
	
}
