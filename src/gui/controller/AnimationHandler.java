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
	@FXML
	private Parent tentativeView;
	
	private boolean isFocusTable;
	private boolean isFocusToday;
	private boolean isFocusHelper;
	private boolean isFocusTentative;
	
	TranslateTransition slideInTable;
	TranslateTransition slideOutTable;
	
	TranslateTransition slideInToday;
	TranslateTransition slideOutToday;
	
	TranslateTransition slideInHelper;
	TranslateTransition slideOutHelper;	
	
	TranslateTransition slideInTentative;
	TranslateTransition slideOutTentative;
	
	private static AnimationHandler ah = new AnimationHandler();

	private AnimationHandler(){
	}

	public static AnimationHandler getInstance(){
		return ah;
	}
	
	public void initialize(Parent tableView, Parent todayView, Parent helperView, Parent tentativeView){
		
		TranslateTransition slideOutTableInit = new TranslateTransition(Duration.millis(100), tableView);
		slideOutTableInit.setToX(700);
		slideOutTableInit.play();
		TranslateTransition slideOutHelperInit = new TranslateTransition(Duration.millis(100), helperView);
		slideOutHelperInit.setToX(-700);
		slideOutHelperInit.play();
		TranslateTransition slideOutTentativeInit = new TranslateTransition(Duration.millis(100), tentativeView);
		slideOutTentativeInit.setToX(-700);
		slideOutTentativeInit.play();
		
		
		isFocusTable = false;
		isFocusHelper = false;
		isFocusTentative = false;
		isFocusToday = true;
		
		slideInTable = new TranslateTransition(Duration.seconds(0.25), tableView);
		slideInTable.setToX(0);
		slideOutTable = new TranslateTransition(Duration.seconds(0.2), tableView);
		slideOutTable.setToX(700);
		
		slideInToday = new TranslateTransition(Duration.seconds(0.25), todayView);
		slideInToday.setToX(0);
		slideOutToday = new TranslateTransition(Duration.seconds(0.2), todayView);
		slideOutToday.setToX(700);
		
		slideInTentative = new TranslateTransition(Duration.seconds(0.25), tentativeView);
		slideInTentative.setToX(0);
		slideOutTentative = new TranslateTransition(Duration.seconds(0.2), tentativeView);
		slideOutTentative.setToX(700);
		
		slideInHelper = new TranslateTransition(Duration.seconds(0.25), helperView);
		slideInHelper.setToX(0);
		slideOutHelper = new TranslateTransition(Duration.seconds(0.2), helperView);
		slideOutHelper.setToX(-700);
		
	}
	
	public boolean getIsFocusTable(){
		return isFocusTable;
	}
	
	public boolean getIsFocusToday(){
		return isFocusToday;
	}
	
	public void showTentative(){
		if(isFocusTable){
			slideOutTable.play();
		}
		else{
			slideOutToday.play();
		}
	}
	
	public void showTableView(){
		if(isFocusToday){
			slideInTable.play();
			slideOutToday.play();

			isFocusToday = false;
			isFocusTable = true;
		}
		
		if(isFocusTentative){
			slideInTable.play();
			slideOutTentative.play();

			isFocusTentative = false;
			isFocusTable = true;
		}
	}

	public void showTodayView(){
		if(isFocusTable){
			slideOutTable.play();
			slideInToday.play();

			isFocusTable = false;
			isFocusToday = true;
		}
		
		if(isFocusTentative){
			slideInToday.play();
			slideOutTentative.play();

			isFocusTentative = false;
			isFocusToday = true;
		}
	}
	
	public void showTentativeView() {
		if(isFocusToday){
			slideInTentative.play();
			slideOutToday.play();

			isFocusToday = false;
			isFocusTentative = true;
		}
		if(isFocusTable){
			slideOutTable.play();
			slideInTentative.play();

			isFocusTable = false;
			isFocusTentative = true;
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
		
		if(isFocusTentative){
			slideOutTentative.play();
			slideInHelper.play();
			
			isFocusTentative = false;
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
