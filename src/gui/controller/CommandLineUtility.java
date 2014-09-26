package gui.controller;

import java.util.Stack;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

/**
 * This class gives the command
 * line utility for the up and
 * down key presses.
 * 
 * * Author: smallson
 */

public class CommandLineUtility {
	
	private Stack<String> previousInput;
	private Stack<String> forwardInput;
	
	@FXML
	private TextField commandLine;
	
	private static CommandLineUtility clu = new CommandLineUtility();

	private CommandLineUtility(){
	}

	public static CommandLineUtility getInstance(){
		return clu;
	}
	
	public void forwardToPrevious(){
		while(!forwardInput.isEmpty()){
			previousInput.push(forwardInput.pop());
		}
	}

	public void initialize(TextField commandLine) {
		this.commandLine = commandLine;
		previousInput = new Stack<String>();
		forwardInput = new Stack<String>();
	}
	
	public void displayPreviousInput(){
		if(!previousInput.isEmpty()){
			forwardInput.push(previousInput.peek());
			commandLine.setText(previousInput.pop());
			Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    commandLine.selectAll();
                }
			});
		}
	}
	
	public void displayForwardInput(){
		if(!forwardInput.isEmpty()){
			previousInput.push(forwardInput.peek());
			commandLine.setText(forwardInput.pop());
			Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    commandLine.selectAll();
                }
			});
		}
	}
	
	public void pushInput(String input){
		previousInput.push(input);
	}

}
