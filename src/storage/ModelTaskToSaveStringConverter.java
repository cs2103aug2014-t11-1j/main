package storage;
/** 
 * 
 * @author zhang
 *this class is used to convert ModelTask to a save string for saving into text file
 */
//import java.text.SimpleDateFormat;
//import java.util.Date;

public class ModelTaskToSaveStringConverter {

//    SimpleDateFormat dateFormatter,timeFormatter;

	String saveString;

	public ModelTaskToSaveStringConverter() {
		
	}
	
	public String toSave(ModelTask task) {
//		dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
//		timeFormatter = new SimpleDateFormat("HHmm");
//			
		String event = task.getEvent();
		String startDate = task.getStartDateString();
		String endDate = task.getEndDateString();
		String startTime = task.getStartTimeString();
		String endTime = task.getEndTimeString();
		String deadLine = task.getDeadLineString();
		boolean isDone = task.isDone();
                boolean isUrgent = task.isUrgent();
		
		
		String saveString =event + ";" + startDate + ";" + endDate + ";" 
		+ startTime + ";" +endTime + ";" + deadLine +";" + isDone+ ";" + isUrgent;
		return saveString;
	}

	public String getSaveString(){
		return saveString;
	}
	
	
}
