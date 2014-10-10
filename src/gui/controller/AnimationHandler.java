package gui.controller;

import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

	private boolean isPlaying;

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

		slideInTable = new TranslateTransition(Duration.seconds(0.2), tableView);
		slideInTable.setToX(0);
		slideOutTable = new TranslateTransition(Duration.seconds(0.2), tableView);
		slideOutTable.setToX(700);

		slideInToday = new TranslateTransition(Duration.seconds(0.2), todayView);
		slideInToday.setToX(0);
		slideOutToday = new TranslateTransition(Duration.seconds(0.2), todayView);
		slideOutToday.setToX(700);

		slideInTentative = new TranslateTransition(Duration.seconds(0.2), tentativeView);
		slideInTentative.setToX(0);
		slideOutTentative = new TranslateTransition(Duration.seconds(0.2), tentativeView);
		slideOutTentative.setToX(700);

		slideInHelper = new TranslateTransition(Duration.seconds(0.2), helperView);
		slideInHelper.setToX(0);
		slideOutHelper = new TranslateTransition(Duration.seconds(0.2), helperView);
		slideOutHelper.setToX(-700);

		slideInTable.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				isPlaying = false;
			}
		});
		slideOutTable.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				isPlaying = false;
			}
		});

		slideInToday.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				isPlaying = false;
			}
		});
		slideOutToday.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				isPlaying = false;
			}
		});
		slideInTentative.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				isPlaying = false;
			}
		});
		slideOutTentative.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				isPlaying = false;
			}
		});

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
			isPlaying = true;

			slideInTable.play();
			slideOutToday.play();
			slideOutTentative.play();

			isFocusToday = false;
			isFocusTable = true;
		}

		if(isFocusTentative){
			isPlaying = true;

			slideInTable.play();
			slideOutTentative.play();
			slideOutToday.play();

			isFocusTentative = false;
			isFocusTable = true;
		}

			viewIndex = TABLE_INDEX;
	}

	public void showTodayView(){
		if(isFocusTable){
			isPlaying = true;

			slideOutTable.play();
			slideInToday.play();
			slideOutTentative.play();

			isFocusTable = false;
			isFocusToday = true;
		}

		if(isFocusTentative){
			isPlaying = true;

			slideInToday.play();
			slideOutTentative.play();
			slideOutTable.play();

			isFocusTentative = false;
			isFocusToday = true;
		}

			viewIndex = TODAY_INDEX;
	}

	public void showTentativeView() {
		if(isFocusToday){
			isPlaying = true;

			slideInTentative.play();
			slideOutToday.play();
			slideOutTable.play();

			isFocusToday = false;
			isFocusTentative = true;
		}
		if(isFocusTable){
			isPlaying = true;

			slideInTentative.play();
			slideOutTable.play();
			slideOutToday.play();

			isFocusTable = false;
			isFocusTentative = true;
		}

			viewIndex = TENTATIVE_INDEX;
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
			viewIndex = TABLE_INDEX;
		}
	}

	public void revertView(){
		if(isFocusHelper){
			slideOutHelper.play();
			slideInToday.play();

			isFocusToday = true;
			isFocusHelper = false;
			viewIndex = TODAY_INDEX;
		}		

	}

	public void animateRight() {
		viewIndex++;
		if(viewIndex > NUMBER_OF_VIEWS - 1){
			viewIndex = NUMBER_OF_VIEWS - 1;
		} else {
			shiftViewToIndex();
		}
	}

	public void animateLeft() {
		viewIndex--;
		if(viewIndex < 0){
			viewIndex = 0;
		} else {
			shiftViewToIndex();
		}
	}

	private void shiftViewToIndex() {

		switch((viewIndex + NUMBER_OF_VIEWS) % NUMBER_OF_VIEWS) {
		case TODAY_INDEX :
			showTodayView();
			break;
		case TABLE_INDEX :
			showTableView();
			break;
		case TENTATIVE_INDEX :
			showTentativeView();
			break;			
		default :
			System.out.println("lol");;

		}		
	}

}
