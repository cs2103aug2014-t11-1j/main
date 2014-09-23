package gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class CommandLineController{
	@FXML
	private TextField commandLine;
	
	public CommandLineController(){
		
	}
	
	@FXML
	private void handleKeyPressed(KeyEvent e){
		if(e.getCode() == KeyCode.ENTER){
			String input = commandLine.getText();
			commandLine.clear();
		}
	}
	
	
}
