package gui.controller;

import java.util.Stack;

import javafx.application.Platform;
import javafx.scene.control.TextField;

/**
 * This class gives the command
 * line utility for the up and
 * down key presses.
 * 
 * * Author: smallson
 */
//@author A0116211B
public class CommandLineUtility {
	
	private Stack<String> previousInput_;
	private Stack<String> forwardInput_;
	
	private TextField commandLine_;
	
	private static CommandLineUtility clu_ = new CommandLineUtility();

	private CommandLineUtility(){
	}

	public static CommandLineUtility getInstance(){
		return clu_;
	}
	
	public void forwardToPrevious(){
		while(!forwardInput_.isEmpty()){
			previousInput_.push(forwardInput_.pop());
		}
	}

	public void initialize(TextField commandLine) {
		this.commandLine_ = commandLine;
		previousInput_ = new Stack<String>();
		forwardInput_ = new Stack<String>();
	}
	
	public void displayPreviousInput(){
		if(!previousInput_.isEmpty()){
			forwardInput_.push(previousInput_.peek());
			commandLine_.setText(previousInput_.pop());
			Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    commandLine_.selectAll();
                }
			});
		}
	}
	
	public void displayForwardInput(){
		if(!forwardInput_.isEmpty()){
			previousInput_.push(forwardInput_.peek());
			commandLine_.setText(forwardInput_.pop());
			Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    commandLine_.selectAll();
                }
			});
		}
	}
	
	public void pushInput(String input){
		previousInput_.push(input);
	}

}
