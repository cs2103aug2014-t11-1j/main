package gui.controller;

import com.ModelTask;

import javafx.collections.ObservableList;

public class TentativeViewManager {
	
	TimelineViewController controller;
	ObservableList<ModelTask> allList;
	
	public TentativeViewManager(){
		
	}

	public void setTentativeViewController(TimelineViewController tentativeViewController) {
		controller = tentativeViewController;
	}

	public void setAllList(ObservableList<ModelTask> allList) {
		this.allList = allList;
		updateTentative();
	}
	
	public void updateTentative(){
		for(ModelTask task:allList){
			if(hasTimePeriod(task)){
				System.out.println("task: " + task.getPosition());
				controller.addConfirmedPeriod(task.getStartTime(), task.getEndTime());
			}
		}
	}

	private boolean hasTimePeriod(ModelTask task) {
		return task.getTimeStringProperty().getValue().contains("-");
	}
	
}
