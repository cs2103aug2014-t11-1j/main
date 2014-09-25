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
		return startDate1.compareTo(startDate2);
	}

}
