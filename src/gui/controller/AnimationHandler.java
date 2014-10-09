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

	private static final int NUMBER_OF_VIEWS = 3;
	private static final int TODAY_INDEX = 0;
	private static final int TABLE_INDEX = 1;
	private static final int TENTATIVE_INDEX = 2;
	private static Integer viewIndex;

	private static AnimationHandler ah = new AnimationHandler();

	private AnimationHandler(){
	}

	public static AnimationHandler getInstance(){
		return ah;
	}

	public void initialize(Parent tableView, Parent todayView, Parent helperView, Parent tentativeView){

		viewIndex = TODAY_INDEX;

		TranslateTransition slideOutTableInit = new TranslateTransition(Duration.millis(100), tableView);
		slideOutTableInit.setToX(700);
		slideOutTableInit.play();
		TranslateTransition slideOutTentativeInit = new TranslateTransition(Duration.millis(100), tentativeView);
		slideOutTentativeInit.setToX(700);
		slideOutTentativeInit.play();
		TranslateTransition slideOutHelperInit = new TranslateTransition(Duration.millis(100), helperView);
		slideOutHelperInit.setToX(-700);
		slideOutHelperInit.play();

		isFocusTable = false;
		isFocusHelper = false;
		isFocusTentative = false;
		isFocusToday = true;

		slideInTable = new TranslateTransition(Duration.seconds(0.15), tableView);
		slideInTable.setToX(0);
		slideOutTable = new TranslateTransition(Duration.seconds(0.15), tableView);
		slideOutTable.setToX(700);

		slideInToday = new TranslateTransition(Duration.seconds(0.15), todayView);
		slideInToday.setToX(0);
		slideOutToday = new TranslateTransition(Duration.seconds(0.15), todayView);
		slideOutToday.setToX(700);

		slideInTentative = new TranslateTransition(Duration.seconds(0.15), tentativeView);
		slideInTentative.setToX(0);
		slideOutTentative = new TranslateTransition(Duration.seconds(0.15), tentativeView);
		slideOutTentative.setToX(700);

		slideInHelper = new TranslateTransition(Duration.seconds(0.25), helperView);
		slideInHelper.setToX(0);
		slideOutHelper = new TranslateTransition(Duration.seconds(0.2), helperView);
		slideOutHelper.setToX(-700);

	}
	
	public int getViewIndex(){
		return viewIndex;
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

	//	viewIndex = TABLE_INDEX;
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

	//	viewIndex = TODAY_INDEX;
	}

	public void showTentativeView() {
		if(isFocusToday){
			slideInTentative.play();
			slideOutToday.play();

			isFocusToday = false;
			isFocusTentative = true;
		}
		if(isFocusTable){
			slideInTentative.play();
			slideOutTable.play();

			isFocusTable = false;
			isFocusTentative = true;
		}

	//	viewIndex = TENTATIVE_INDEX;
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

	//	viewIndex = TABLE_INDEX;
	}

	public void revertView(){
		if(isFocusHelper){
			slideOutHelper.play();
			slideInToday.play();

			isFocusToday = true;
			isFocusHelper = false;
		}		

	//	viewIndex = TODAY_INDEX;
	}

	public void animateRight() {
		System.out.println(viewIndex);
		viewIndex++;
		shiftViewToIndex();
	}

	public void animateLeft() {
		System.out.println(viewIndex);
		viewIndex--;
		shiftViewToIndex();
	}

	private void shiftViewToIndex() {

		switch((viewIndex + NUMBER_OF_VIEWS) % NUMBER_OF_VIEWS) {
		case TODAY_INDEX :
			showTodayView();
			viewIndex = 0;
			break;
		case TABLE_INDEX :
			showTableView();
			viewIndex = 1;
			break;
		case TENTATIVE_INDEX :
			showTentativeView();
			viewIndex = 2;
			break;			
		default :
			System.out.println("lol");;
			
		}		
	}
	
	
}
