package gui.controller;
/**
 * @author A0116018R
 * This class manages the observable list in TodayView.
 * It chooses up to 5 tasks for display from a 
 * given list.
 * 
 * The given list should be an observableList of ModelTasks.
 * It will return an observableList of ModelTasks for use.
 */
import java.util.Calendar;
import java.util.Date;

import com.ModelTask;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TodayListManager {

	ObservableList<ModelTask> todayList_;
	Date yesterday_;
	Date tomorrow_;
	
	/**
	 * Constructs the todayListManager with
	 * today initialised.
	 */
	public TodayListManager(){
		setYesterday();
		setTomorrow();
	}
	
	/**
	 * This class takes an observableList of ModelTasks.
	 * And returns an ObservableList of ModelTasks 
	 * of up to 5 tasks due that day.
	 * @param ObservableList<ModelTask> allList
	 * @return ObservableList<ModelTask> todayList
	 */
	public ObservableList<ModelTask> getTodayList(
			ObservableList<ModelTask> allList){

		todayList_ = FXCollections.observableArrayList();

		for(ModelTask task : allList){
			if(task.getStartDate() != null){
				if(task.getStartDate().compareTo(yesterday_) > 0 && task.getStartDate().compareTo(tomorrow_) < 0 && !task.isDone()){
					todayList_.add(task);
				}
			}
		}

		todayList_.sort(new ModelTaskStartDateComparator());
		if(todayList_.size() > 5){
			int num = todayList_.size() - 5;
			for(int i = 0; i < num; i++){
				todayList_.remove(5);
			}
		}
		return todayList_;
	}
	
	/**
	 * This allows TodayListManager to reset the dates.
	 * Afterwards, getTodayList should be called to get the updated list.
	 */
	public void resetDate(){
		setYesterday();
		setTomorrow();
	}
	
	private void setYesterday() {
		Calendar yesterdayCal = Calendar.getInstance();
		yesterdayCal.set(Calendar.DAY_OF_YEAR, yesterdayCal.get(Calendar.DAY_OF_YEAR) - 1);
		yesterdayCal.set(Calendar.HOUR_OF_DAY, 23);
		yesterdayCal.set(Calendar.MINUTE, 59);
		yesterdayCal.set(Calendar.SECOND, 59);

		yesterday_ = yesterdayCal.getTime();
	}
	
	private void setTomorrow() {
		Calendar tomorrowCal = Calendar.getInstance();
		tomorrowCal.set(Calendar.HOUR_OF_DAY, 23);
		tomorrowCal.set(Calendar.MINUTE, 59);
		tomorrowCal.set(Calendar.SECOND, 59);

		tomorrow_ = tomorrowCal.getTime();
	}


}
