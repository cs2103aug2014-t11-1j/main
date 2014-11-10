package gui.controller;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * This class is used to handle
 * custom animations for the controller.
 * 
 * * Author: smallson
 */

//@author A0116211B
public class AnimationHandler {

	@FXML
	private AnchorPane tableAnchor;
	@FXML
	private AnchorPane todayAnchor;
	@FXML
	private AnchorPane helperAnchor;
	@FXML
	private AnchorPane tentativeAnchor;
	@FXML
	private AnchorPane timelineAnchor;

	private boolean isFocusTable;
	private boolean isFocusToday;
	private boolean isFocusHelper;
	private boolean isFocusTentative;
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
	
	TranslateTransition slideInTentative;
	TranslateTransition slideOutTentative;

	private static final int NUMBER_OF_VIEWS = 2;
	private static final int TODAY_INDEX = 0;
	private static final int TABLE_INDEX = 1;
	private static final int TIMELINE_INDEX = 2;
	private static final int TENTATIVE_INDEX = 3;
	private static Integer viewIndex;

	private static AnimationHandler ah = new AnimationHandler();

	private AnimationHandler(){
	}

	public static AnimationHandler getInstance(){
		return ah;
	}

	public void initialize(AnchorPane tableAnchor, AnchorPane todayAnchor, AnchorPane helperAnchor){

		viewIndex = TODAY_INDEX;

		TranslateTransition slideOutTableInit = new TranslateTransition(Duration.millis(100), tableAnchor);
		slideOutTableInit.setToX(700);
		slideOutTableInit.play();
		TranslateTransition slideOutTentativeInit = new TranslateTransition(Duration.millis(100), tentativeAnchor);
		slideOutTentativeInit.setToX(700);
		slideOutTentativeInit.play();
		TranslateTransition slideOutTimelineInit = new TranslateTransition(Duration.millis(100), timelineAnchor);
		slideOutTimelineInit.setToX(700);
		slideOutTimelineInit.play();
		TranslateTransition slideOutHelperInit = new TranslateTransition(Duration.millis(100), helperAnchor);
		slideOutHelperInit.setToX(700);
		slideOutHelperInit.play();

		isFocusTable = false;
		isFocusHelper = false;
		isFocusTentative = false;
		isFocusTimeline = false;
		isFocusToday = true;

		slideInTable = new TranslateTransition(Duration.seconds(0.2), tableAnchor);
		slideInTable.setToX(0);
		slideOutTable = new TranslateTransition(Duration.seconds(0.2), tableAnchor);
		slideOutTable.setToX(700);

		slideInToday = new TranslateTransition(Duration.seconds(0.2), todayAnchor);
		slideInToday.setToX(0);
		slideOutToday = new TranslateTransition(Duration.seconds(0.2), todayAnchor);
		slideOutToday.setToX(700);
		
		slideInTentative = new TranslateTransition(Duration.seconds(0.2), tentativeAnchor);
		slideInTentative.setToX(0);
		slideOutTentative = new TranslateTransition(Duration.seconds(0.2), tentativeAnchor);
		slideOutTentative.setToX(700);

		slideInTimeline = new TranslateTransition(Duration.seconds(0.2), timelineAnchor);
		slideInTimeline.setToX(0);
		slideOutTimeline = new TranslateTransition(Duration.seconds(0.2), timelineAnchor);
		slideOutTimeline.setToX(700);

		slideInHelper = new TranslateTransition(Duration.seconds(0.2), helperAnchor);
		slideInHelper.setToX(0);
		slideOutHelper = new TranslateTransition(Duration.seconds(0.2), helperAnchor);
		slideOutHelper.setToX(700);


		

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
		
		if(isFocusTentative){
			slideInTable.play();
			slideOutTentative.play();
			
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
		
		if(isFocusTentative){
			slideInToday.play();
			slideOutTentative.play();
			
			isFocusTentative = false;
			isFocusToday = true;
		}

		viewIndex = TODAY_INDEX;
	}
	
	public void showTentativeView(){
		if(isFocusToday){
			slideOutToday.play();
			slideInTentative.play();
			
			isFocusToday = false;
			isFocusTentative = true;
		}
		
		if(isFocusTable){
			slideOutTable.play();
			slideInTentative.play();
			
			isFocusTable = false;
			isFocusTentative = true;
		}
		
		if(isFocusTimeline){
			slideOutTimeline.play();
			slideInTentative.play();
			
			isFocusTable = false;
			isFocusTentative = true;
		}
		
		viewIndex = TENTATIVE_INDEX;
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
		if(isFocusTentative){
			slideInTimeline.play();
			slideOutTentative.play();
			
			isFocusTentative = false;
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
		
		if(isFocusTentative){
			slideOutTentative.play();
			slideInHelper.play();

			isFocusTentative = false;
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
//		case TENTATIVE_INDEX :
//			showTentativeView();
//			break;
		case TIMELINE_INDEX :
			showTimelineView();
			break;			
		default :
			System.out.println("lol");
		}		
	}
	
	public void fadeLabel(Label tfOutput){
		FadeTransition fadeTransition
		= new FadeTransition(Duration.millis(3200), tfOutput);
		fadeTransition.setFromValue(1.0);
		fadeTransition.setToValue(0.0);
		fadeTransition.play();
	}

}
