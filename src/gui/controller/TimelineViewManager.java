package gui.controller;
/**
 * @author A0116018R
 * Unused: not fully developed.
 * 
 * This class manages the timelineViewController
 *  and tells it what time period to highlight
 *  in what colour.
 */
import gui.controller.view.TimelineViewController;

import java.util.Calendar;
import java.util.Date;

import com.ModelTask;

import javafx.collections.ObservableList;

public class TimelineViewManager {
	
	TimelineViewController controller_;
	ObservableList<ModelTask> allList_;
	
	Date thisMonday_;
	Date thisSunday_;
	
	Date nextMonday_;
	Date nextSunday_;
	
	public TimelineViewManager(){
		setMonday();
		setSunday();
	}

	public void setTimelineViewController(TimelineViewController timelineViewController) {
		controller_ = timelineViewController;
	}

	public void setAllList(ObservableList<ModelTask> allList) {
		this.allList_ = allList;
		updateTentative();
	}
	
	public void updateTentative(){
		//TODO:set all to green
		
		//set normal tasks
		for(ModelTask task:allList_){
			if(isThisWeek(task) && hasTimePeriod(task)){
				controller_.addPeriod(task.getStartTime(), task.getEndTime(), TimelineViewController.COLOUR_ORANGE);
			}
		}
	}
	
	private void setMonday() {
		Calendar mondayCal = Calendar.getInstance();
		mondayCal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		mondayCal.set(Calendar.HOUR_OF_DAY, 0);
		mondayCal.set(Calendar.MINUTE, 0);
		mondayCal.set(Calendar.SECOND, 0);

		thisMonday_ = mondayCal.getTime();
		
		//make calendar next week
		mondayCal.roll(Calendar.WEEK_OF_MONTH, true);
		nextMonday_ = mondayCal.getTime();
	}
	
	private void setSunday() {
		Calendar sundayCal = Calendar.getInstance();
		sundayCal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		sundayCal.set(Calendar.HOUR_OF_DAY, 23);
		sundayCal.set(Calendar.MINUTE, 59);
		sundayCal.set(Calendar.SECOND, 59);

		thisSunday_ = sundayCal.getTime();
		
		//make calendar next week
		sundayCal.roll(Calendar.WEEK_OF_MONTH, true);
		nextSunday_ = sundayCal.getTime();
	}

	private boolean isThisWeek(ModelTask task) {
		Date day = task.getStartDate();
		if(day == null){
			return false;
		}
		return day.compareTo(thisMonday_) >=0 && day.compareTo(thisSunday_) <= 0;
	}

	private boolean hasTimePeriod(ModelTask task) {
		return task.getTimeStringProperty().getValue().contains("-");
	}
	
}
