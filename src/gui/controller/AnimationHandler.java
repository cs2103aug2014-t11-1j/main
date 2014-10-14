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
	private Parent timelineView;

	private boolean isFocusTable;
	private boolean isFocusToday;
	private boolean isFocusHelper;
	private boolean isFocusTimeline;

	private boolean isPlaying;

	TranslateTransition slideInTable;
	TranslateTransition slideOutTable;

	TranslateTransition slideInToday;
	TranslateTransition slideOutToday;

	TranslateTransition slideInHelper;
	TranslateTransition slideOutHelper;	

	TranslateTransition slideInTimeline;
	TranslateTransition slideOutTimeline;

	private static final int NUMBER_OF_VIEWS = 3;
	private static final int TODAY_INDEX = 0;
	private static final int TABLE_INDEX = 1;
	private static final int TIMELINE_INDEX = 2;
	private static Integer viewIndex;

	private static AnimationHandler ah = new AnimationHandler();

	private AnimationHandler(){
	}

	public static AnimationHandler getInstance(){
		return ah;
	}

	public void initialize(Parent tableView, Parent todayView, Parent helperView, Parent timelineView){

		viewIndex = TODAY_INDEX;

		TranslateTransition slideOutTableInit = new TranslateTransition(Duration.millis(100), tableView);
		slideOutTableInit.setToX(700);
		slideOutTableInit.play();
		TranslateTransition slideOutTimelineInit = new TranslateTransition(Duration.millis(100), timelineView);
		slideOutTimelineInit.setToX(700);
		slideOutTimelineInit.play();
		TranslateTransition slideOutHelperInit = new TranslateTransition(Duration.millis(100), helperView);
		slideOutHelperInit.setToX(700);
		slideOutHelperInit.play();

		isFocusTable = false;
		isFocusHelper = false;
		isFocusTimeline = false;
		isFocusToday = true;

		slideInTable = new TranslateTransition(Duration.seconds(0.2), tableView);
		slideInTable.setToX(0);
		slideOutTable = new TranslateTransition(Duration.seconds(0.2), tableView);
		slideOutTable.setToX(700);

		slideInToday = new TranslateTransition(Duration.seconds(0.2), todayView);
		slideInToday.setToX(0);
		slideOutToday = new TranslateTransition(Duration.seconds(0.2), todayView);
		slideOutToday.setToX(700);

		slideInTimeline = new TranslateTransition(Duration.seconds(0.2), timelineView);
		slideInTimeline.setToX(0);
		slideOutTimeline = new TranslateTransition(Duration.seconds(0.2), timelineView);
		slideOutTimeline.setToX(700);

		slideInHelper = new TranslateTransition(Duration.seconds(0.2), helperView);
		slideInHelper.setToX(0);
		slideOutHelper = new TranslateTransition(Duration.seconds(0.2), helperView);
		slideOutHelper.setToX(700);

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
		slideInTimeline.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				isPlaying = false;
			}
		});
		slideOutTimeline.setOnFinished(new EventHandler<ActionEvent>(){

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

	public void showTimeline(){
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
			slideOutTimeline.play();

			isFocusToday = false;
			isFocusTable = true;
		}

		if(isFocusTimeline){
			isPlaying = true;

			slideInTable.play();
			slideOutTimeline.play();
			slideOutToday.play();

			isFocusTimeline = false;
			isFocusTable = true;
		}

			viewIndex = TABLE_INDEX;
	}

	public void showTodayView(){
		if(isFocusTable){
			isPlaying = true;

			slideOutTable.play();
			slideInToday.play();
			slideOutTimeline.play();

			isFocusTable = false;
			isFocusToday = true;
		}

		if(isFocusTimeline){
			isPlaying = true;

			slideInToday.play();
			slideOutTimeline.play();
			slideOutTable.play();

			isFocusTimeline = false;
			isFocusToday = true;
		}

			viewIndex = TODAY_INDEX;
	}

	public void showTimelineView() {
		if(isFocusToday){
			isPlaying = true;

			slideInTimeline.play();
			slideOutToday.play();
			slideOutTable.play();

			isFocusToday = false;
			isFocusTimeline = true;
		}
		if(isFocusTable){
			isPlaying = true;

			slideInTimeline.play();
			slideOutTable.play();
			slideOutToday.play();

			isFocusTable = false;
			isFocusTimeline = true;
		}

			viewIndex = TIMELINE_INDEX;
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

		if(isFocusTimeline){
			slideOutTimeline.play();
			slideInHelper.play();

			isFocusTimeline = false;
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
		case TIMELINE_INDEX :
			showTimelineView();
			break;			
		default :
			System.out.println("lol");;

		}		
	}

}
