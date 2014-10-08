package gui.controller;

import storage.ModelTask;
import javafx.collections.ObservableList;

public class TentativeViewManager {
	
	TentativeViewController controller;
	ObservableList<ModelTask> allList;
	
	public TentativeViewManager(){
		
	}

	public void setTentativeViewController(TentativeViewController tentativeViewController) {
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
