package gui.controller;

import java.util.Calendar;
import java.util.Date;

import storage.ModelTask;
import javafx.collections.ObservableList;

public class TimelineViewManager {
	
	TimelineViewController controller;
	ObservableList<ModelTask> allList;
	
	Date thisMonday;
	Date thisSunday;
	
	Date nextMonday;
	Date nextSunday;
	
	public TimelineViewManager(){
		setMonday();
		setSunday();
	}

	private void setMonday() {
		Calendar mondayCal = Calendar.getInstance();
		mondayCal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		mondayCal.set(Calendar.HOUR_OF_DAY, 0);
		mondayCal.set(Calendar.MINUTE, 0);
		mondayCal.set(Calendar.SECOND, 0);

		thisMonday = mondayCal.getTime();
		
		//make calendar next week
		mondayCal.roll(Calendar.WEEK_OF_MONTH, true);
		nextMonday = mondayCal.getTime();
	}
	
	private void setSunday() {
		Calendar sundayCal = Calendar.getInstance();
		sundayCal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		sundayCal.set(Calendar.HOUR_OF_DAY, 23);
		sundayCal.set(Calendar.MINUTE, 59);
		sundayCal.set(Calendar.SECOND, 59);

		thisSunday = sundayCal.getTime();
		
		sundayCal.roll(Calendar.WEEK_OF_MONTH, true);
		nextSunday = sundayCal.getTime();
	}

	public void setTimelineViewController(TimelineViewController timelineViewController) {
		controller = timelineViewController;
	}

	public void setAllList(ObservableList<ModelTask> allList) {
		this.allList = allList;
		updateTentative();
	}
	
	public void updateTentative(){
		for(ModelTask task:allList){
			if(isThisWeek(task) && hasTimePeriod(task)){
				System.out.println("task: " + task.getEvent());
				controller.addConfirmedPeriod(task.getStartTime(), task.getEndTime());
			}
		}
	}

	private boolean isThisWeek(ModelTask task) {
		Date day = task.getStartDate();
		if(day == null){
			return false;
		}
		return day.compareTo(thisMonday) >=0 && day.compareTo(thisSunday) <= 0;
	}

	private boolean hasTimePeriod(ModelTask task) {
		return task.getTimeStringProperty().getValue().contains("-");
	}
	
}
