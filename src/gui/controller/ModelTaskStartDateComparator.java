package gui.controller;

import java.util.Comparator;
import java.util.Date;

import storage.ModelTask;

public class ModelTaskStartDateComparator implements
		Comparator<ModelTask> {

	@Override
	public int compare(ModelTask task1, ModelTask task2) {
		Date startDate1 = task1.getStartDate();
		Date startDate2 = task2.getStartDate();
		
		if(startDate1 == null && startDate2 != null){
			return -1;
		}
		else if(startDate2 == null && startDate1 != null){
			return 1;
		}
		else if(startDate2 == null && startDate1 == null){
			return 0;
		}
		else{
			return startDate1.compareTo(startDate2);
		}
	}

}
