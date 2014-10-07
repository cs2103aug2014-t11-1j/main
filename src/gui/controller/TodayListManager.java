package gui.controller;

import java.util.Calendar;
import java.util.Date;

import storage.ModelTask;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TodayListManager {

	ObservableList<ModelTask> todayList;
	Date yesterday;
	Date tomorrow;

	TodayListManager(){
		setYesterday();
		setTomorrow();
	}

	protected ObservableList<ModelTask> getTodayList(
			ObservableList<ModelTask> allList){

		todayList = FXCollections.observableArrayList();

		for(ModelTask task : allList){
			if(task.getStartDate() != null){
				if(task.getStartDate().compareTo(yesterday) > 0 && task.getStartDate().compareTo(tomorrow) < 0 && !task.isDone()){
					todayList.add(task);
				}
			}
		}

		todayList.sort(new ModelTaskStartDateComparator());
		if(todayList.size() > 5){
			for(int i = 5; i < todayList.size(); i++){
				todayList.remove(5);
			}
		}
		return todayList;
	}
	
	protected void resetDate(){
		setYesterday();
		setTomorrow();
	}
	
	private void setYesterday() {
		Calendar yesterdayCal = Calendar.getInstance();
		yesterdayCal.set(Calendar.DAY_OF_YEAR, yesterdayCal.get(Calendar.DAY_OF_YEAR) - 1);
		yesterdayCal.set(Calendar.HOUR_OF_DAY, 23);
		yesterdayCal.set(Calendar.MINUTE, 59);
		yesterdayCal.set(Calendar.SECOND, 59);

		yesterday = yesterdayCal.getTime();
	}
	
	private void setTomorrow() {
		Calendar tomorrowCal = Calendar.getInstance();
		tomorrowCal.set(Calendar.HOUR_OF_DAY, 23);
		tomorrowCal.set(Calendar.MINUTE, 59);
		tomorrowCal.set(Calendar.SECOND, 59);

		tomorrow = tomorrowCal.getTime();
	}


}
