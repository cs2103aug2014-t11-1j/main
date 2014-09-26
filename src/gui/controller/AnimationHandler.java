package gui.controller;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.util.Duration;

/**
 * This class is used to handle
 * custom animations for the controller.
 * 
 * * Author: smallson
 */

public class AnimationHandler {
	
	@FXML
	private Parent tableView;
	@FXML
	private Parent todayView;
	
	TranslateTransition slideInTable;
	TranslateTransition slideOutTable;
	
	TranslateTransition slideInToday;
	TranslateTransition slideOutToday;
	
	private static AnimationHandler ah = new AnimationHandler();

	private AnimationHandler(){
	}

	public static AnimationHandler getInstance(){
		return ah;
	}
	
	public void initialize(Parent tableView, Parent todayView){
		
		this.tableView = tableView;
		this.todayView = todayView;
		TranslateTransition slideOutInit = new TranslateTransition(Duration.millis(100), tableView);
		slideOutInit.setByX(700);
		slideOutInit.play();
		
		slideInTable = new TranslateTransition(Duration.seconds(0.5), tableView);
		slideInTable.setByX(-700);
		slideOutTable = new TranslateTransition(Duration.seconds(0.5), tableView);
		slideOutTable.setByX(700);
		
		slideInToday = new TranslateTransition(Duration.seconds(0.5), todayView);
		slideInToday.setByX(-700);
		slideOutToday = new TranslateTransition(Duration.seconds(0.5), todayView);
		slideOutToday.setByX(700);
	}
	
	public void animateLeft(){
		slideInTable.play();
		slideOutToday.play();
	}
	
	public void animateRight(){
		slideOutTable.play();
		slideInToday.play();
	}

}
