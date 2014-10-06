package gui.controller;

import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
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
	@FXML
	private Parent helperView;
	
	private boolean isFocusTable;
	private boolean isFocusToday;
	private boolean isFocusHelper;
	
	TranslateTransition slideInTable;
	TranslateTransition slideOutTable;
	
	TranslateTransition slideInToday;
	TranslateTransition slideOutToday;
	
	TranslateTransition slideInHelper;
	TranslateTransition slideOutHelper;
	
	private static AnimationHandler ah = new AnimationHandler();

	private AnimationHandler(){
	}

	public static AnimationHandler getInstance(){
		return ah;
	}
	
	public void initialize(Parent tableView, Parent todayView, Parent helperView){
		
		this.tableView = tableView;
		this.todayView = todayView;
		TranslateTransition slideOutTableInit = new TranslateTransition(Duration.millis(100), tableView);
		slideOutTableInit.setToX(700);
		slideOutTableInit.play();
		TranslateTransition slideOutHelperInit = new TranslateTransition(Duration.millis(100), helperView);
		slideOutHelperInit.setToX(700);
		slideOutHelperInit.play();
		
		isFocusTable = false;
		isFocusHelper = false;
		isFocusToday = true;
		
		slideInTable = new TranslateTransition(Duration.seconds(0.35), tableView);
		slideInTable.setToX(0);
		slideOutTable = new TranslateTransition(Duration.seconds(0.2), tableView);
		slideOutTable.setToX(700);
		
		slideInToday = new TranslateTransition(Duration.seconds(0.35), todayView);
		slideInToday.setToX(0);
		slideOutToday = new TranslateTransition(Duration.seconds(0.2), todayView);
		slideOutToday.setToX(700);
		
		slideInHelper = new TranslateTransition(Duration.seconds(0.25), helperView);
		slideInHelper.setToX(0);
		slideOutHelper = new TranslateTransition(Duration.seconds(0.2), helperView);
		slideOutHelper.setToX(700);
		
	}
	
	public boolean getIsFocusTable(){
		return isFocusTable;
	}
	
	public boolean getIsFocusToday(){
		return isFocusToday;
	}

	public void animateLeft(){
		if(isFocusToday){
			slideInTable.play();
			slideOutToday.play();

			isFocusToday = false;
			isFocusTable = true;
		}
	}

	public void animateRight(){
		if(isFocusTable){
			slideOutTable.play();
			slideInToday.play();

			isFocusTable = false;
			isFocusToday = true;
		}
	}
	
	public void displayHelper(){
		if(isFocusToday){
			slideOutToday.play();
			slideInHelper.play();
			
			isFocusToday = false;
			isFocusHelper = true;
		}
		
		if(isFocusTable){
			slideOutTable.play();
			slideInHelper.play();
			
			isFocusTable = false;
			isFocusHelper = true;
		}
	}
	
	public void removeHelper(){
		if(isFocusHelper){
			slideOutHelper.play();
			slideInTable.play();
			
			isFocusTable = true;
			isFocusHelper = false;
		}
	}
	
	public void revertView(){
		if(isFocusHelper){
			slideOutHelper.play();
			slideInToday.play();
			
			isFocusToday = true;
			isFocusHelper = false;
		}
	}
}
