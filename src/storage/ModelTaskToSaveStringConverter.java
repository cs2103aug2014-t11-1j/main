package storage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ModelTaskToSaveStringConverter {

    SimpleDateFormat dateFormatter,timeFormatter;
	
	
	ModelTask task;
	String saveString;

	public ModelTaskToSaveStringConverter(ModelTask newTask) {
		task = newTask;
	}

	public String toSave() {
		dateFormatter = new SimpleDateFormat("dd/MM/yy");
		timeFormatter = new SimpleDateFormat("HHmm");
			
	
		Date startDate = task.getStartDate();
		Date endDate = task.getEndDate();
		Date startTime = task.getStartTime();
		Date endTime = task.getEndTime();
		String event = task.getEvent();
		boolean isDone = task.isDone();
		
		String saveString =event + ";" + startDate + ";" + endDate + ";" + startTime + ";" + endTime + ";"
				+ isDone;
		return saveString;
	}

	public String getSaveString(){
		return saveString;
	}
	
	
}
