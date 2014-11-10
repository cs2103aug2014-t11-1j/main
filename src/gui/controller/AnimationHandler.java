package gui.controller;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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

	private boolean isFocusTable_;
	private boolean isFocusToday_;
	private boolean isFocusHelper_;
	private boolean isFocusTentative_;
	private boolean isFocusTimeline_;

	private boolean isPlaying;

	TranslateTransition slideInTable_;
	TranslateTransition slideOutTable_;

	TranslateTransition slideInToday_;
	TranslateTransition slideOutToday_;

	TranslateTransition slideInHelper_;
	TranslateTransition slideOutHelper_;	

	TranslateTransition slideInTimeline_;
	TranslateTransition slideOutTimeline_;
	
	TranslateTransition slideInTentative_;
	TranslateTransition slideOutTentative_;

	private static final int NUMBER_OF_VIEWS = 2;
	private static final int TODAY_INDEX = 0;
	private static final int TABLE_INDEX = 1;
	private static final int TIMELINE_INDEX = 2;
	private static final int TENTATIVE_INDEX = 3;
	private static Integer viewIndex;

	private static AnimationHandler ah_ = new AnimationHandler();

	private AnimationHandler(){
	}

	public static AnimationHandler getInstance(){
		return ah_;
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

		isFocusTable_ = false;
		isFocusHelper_ = false;
		isFocusTentative_ = false;
		isFocusTimeline_ = false;
		isFocusToday_ = true;

		slideInTable_ = new TranslateTransition(Duration.seconds(0.2), tableAnchor);
		slideInTable_.setToX(0);
		slideOutTable_ = new TranslateTransition(Duration.seconds(0.2), tableAnchor);
		slideOutTable_.setToX(700);

		slideInToday_ = new TranslateTransition(Duration.seconds(0.2), todayAnchor);
		slideInToday_.setToX(0);
		slideOutToday_ = new TranslateTransition(Duration.seconds(0.2), todayAnchor);
		slideOutToday_.setToX(700);
		
		slideInTentative_ = new TranslateTransition(Duration.seconds(0.2), tentativeAnchor);
		slideInTentative_.setToX(0);
		slideOutTentative_ = new TranslateTransition(Duration.seconds(0.2), tentativeAnchor);
		slideOutTentative_.setToX(700);

		slideInTimeline_ = new TranslateTransition(Duration.seconds(0.2), timelineAnchor);
		slideInTimeline_.setToX(0);
		slideOutTimeline_ = new TranslateTransition(Duration.seconds(0.2), timelineAnchor);
		slideOutTimeline_.setToX(700);

		slideInHelper_ = new TranslateTransition(Duration.seconds(0.2), helperAnchor);
		slideInHelper_.setToX(0);
		slideOutHelper_ = new TranslateTransition(Duration.seconds(0.2), helperAnchor);
		slideOutHelper_.setToX(700);


		

	}

	public int getViewIndex(){
		return viewIndex;
	}

	public boolean getIsFocusTable(){
		return isFocusTable_;
	}

	public boolean getIsFocusToday(){
		return isFocusToday_;
	}

	public void showTimeline(){
		if(isFocusTable_){
			slideOutTable_.play();
		}
		else{
			slideOutToday_.play();
		}
	}

	public void showTableView(){
		if(isFocusToday_){
			isPlaying = true;

			slideInTable_.play();
			slideOutToday_.play();
			slideOutTimeline_.play();

			isFocusToday_ = false;
			isFocusTable_ = true;
		}

		if(isFocusTimeline_){
			isPlaying = true;

			slideInTable_.play();
			slideOutTimeline_.play();
			slideOutToday_.play();

			isFocusTimeline_ = false;
			isFocusTable_ = true;
		}
		
		if(isFocusTentative_){
			slideInTable_.play();
			slideOutTentative_.play();
			
			isFocusTentative_ = false;
			isFocusTable_ = true;
		}

		viewIndex = TABLE_INDEX;
	}

	public void showTodayView(){
		if(isFocusTable_){
			isPlaying = true;

			slideOutTable_.play();
			slideInToday_.play();
			slideOutTimeline_.play();

			isFocusTable_ = false;
			isFocusToday_ = true;
		}

		if(isFocusTimeline_){
			isPlaying = true;

			slideInToday_.play();
			slideOutTimeline_.play();
			slideOutTable_.play();

			isFocusTimeline_ = false;
			isFocusToday_ = true;
		}
		
		if(isFocusTentative_){
			slideInToday_.play();
			slideOutTentative_.play();
			
			isFocusTentative_ = false;
			isFocusToday_ = true;
		}

		viewIndex = TODAY_INDEX;
	}
	
	public void showTentativeView(){
		if(isFocusToday_){
			slideOutToday_.play();
			slideInTentative_.play();
			
			isFocusToday_ = false;
			isFocusTentative_ = true;
		}
		
		if(isFocusTable_){
			slideOutTable_.play();
			slideInTentative_.play();
			
			isFocusTable_ = false;
			isFocusTentative_ = true;
		}
		
		if(isFocusTimeline_){
			slideOutTimeline_.play();
			slideInTentative_.play();
			
			isFocusTable_ = false;
			isFocusTentative_ = true;
		}
		
		viewIndex = TENTATIVE_INDEX;
	}

	public void showTimelineView() {
		if(isFocusToday_){
			isPlaying = true;

			slideInTimeline_.play();
			slideOutToday_.play();
			slideOutTable_.play();

			isFocusToday_ = false;
			isFocusTimeline_ = true;
		}
		if(isFocusTable_){
			isPlaying = true;

			slideInTimeline_.play();
			slideOutTable_.play();
			slideOutToday_.play();

			isFocusTable_ = false;
			isFocusTimeline_ = true;
		}
		if(isFocusTentative_){
			slideInTimeline_.play();
			slideOutTentative_.play();
			
			isFocusTentative_ = false;
			isFocusTimeline_ = true;
		}

		viewIndex = TIMELINE_INDEX;
	}

	public void displayHelper(){
		if(isFocusToday_){
			slideOutToday_.play();
			slideInHelper_.play();

			isFocusToday_ = false;
			isFocusHelper_ = true;
		}

		if(isFocusTable_){
			slideOutTable_.play();
			slideInHelper_.play();

			isFocusTable_ = false;
			isFocusHelper_ = true;
		}
		
		if(isFocusTentative_){
			slideOutTentative_.play();
			slideInHelper_.play();

			isFocusTentative_ = false;
			isFocusHelper_ = true;
		}

		if(isFocusTimeline_){
			slideOutTimeline_.play();
			slideInHelper_.play();

			isFocusTimeline_ = false;
			isFocusHelper_ = true;
		}
	}

	public void removeHelper(){
		if(isFocusHelper_){
			slideOutHelper_.play();
			slideInTable_.play();

			isFocusTable_ = true;
			isFocusHelper_ = false;
			viewIndex = TABLE_INDEX;
		}
	}

	public void revertView(){
		if(isFocusHelper_){
			slideOutHelper_.play();
			slideInToday_.play();

			isFocusToday_ = true;
			isFocusHelper_ = false;
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
