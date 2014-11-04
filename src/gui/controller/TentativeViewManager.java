package gui.controller;

import gui.controller.view.TimelineViewController;
import storage.ModelTask;
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
				controller.addPeriod(task.getStartTime(), task.getEndTime(), 1);
			}
		}
	}

	private boolean hasTimePeriod(ModelTask task) {
		return task.getTimeStringProperty().getValue().contains("-");
	}
	
}
