package storage;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class will take in a Task and position
 * and return a ModelTask
 * 
 * @author Krystal
 * 
 */

public class TaskConverter {
	
	private static TaskConverter taskConverter = new TaskConverter();
	private static SimpleDateFormat dateGetter;
	
	private final static int START_DATE = 1;
	private final static int DEADLINE_DATE = 2;
	private final static int END_DATE = 3;
	private final static int NO_DATE = 4;
	
	private final static int NO_NULL_DATE_INPUTS = 1;
	private final static int END_DATE_INPUT_NULL = 2;
	private final static int BOTH_NULL = 3;
	
	private final static int NO_NULL_TIME_INPUTS = 0;
	private final static int END_TIME_INPUT_NULL = 1;
	private final static int BOTH_TIME_INPUT_NULL = 2;
	
	private final static int START_TIME = 1;
	private final static int END_TIME = 2;
	private final static int NO_TIME = 3;
	
	private TaskConverter(){
		dateGetter = new SimpleDateFormat("dd/MM/yyyy");
	}
	
	public static TaskConverter getInstance(){
		return taskConverter;
	}
	
	public ModelTask convert(Task task, int position){
		Date startDate = null;
		Date endDate = null;
		Date startTime = null;
		Date endTime = null;

		int numOfNullDateInputs = numOfDateNull(task);
		
		if(numOfNullDateInputs == NO_NULL_DATE_INPUTS || numOfNullDateInputs == END_DATE_INPUT_NULL){
			int whichStartDate = findStartDate(task);
			startDate = getDate(task, whichStartDate);
			
			if(numOfNullDateInputs == NO_NULL_DATE_INPUTS){
				int whichEndDate = findEndDate(task);
				endDate = getDate(task, whichEndDate);
			}
		}
		else{
			//numOfNull == BOTH_NULL
			//error if numOfNull == 0
		}
		
		int numOfNullTimeInputs = numOfTimeNull(task);

		
		if(numOfNullTimeInputs == NO_NULL_TIME_INPUTS || numOfNullTimeInputs == END_TIME_INPUT_NULL){
			int whichStartTime = findStartTime(task);
			startTime = getTime(task, startDate, whichStartTime);
			
			if(numOfNullTimeInputs == NO_NULL_TIME_INPUTS && numOfNullDateInputs == NO_NULL_DATE_INPUTS){
				int whichEndTime = findEndTime(task);
				endTime = getTime(task, endDate, whichEndTime);
			}
			else if(numOfNullTimeInputs == NO_NULL_TIME_INPUTS && numOfNullDateInputs == END_DATE_INPUT_NULL){
				int whichEndTime = findEndTime(task);
				endTime = getTime(task, startDate, whichEndTime);
			}
		}
		
		ModelTask modelTask = new ModelTask(task.getTaskDescription(), startDate, endDate, startTime, endTime, position, task.isDone(), task.isUrgent());
		return modelTask;
	}
	
	private int findEndTime(Task task) {
		if(task.getEndTime() != null){
			return END_TIME;
		}
		else if(task.getStartTime() != null){
			return START_TIME;
		}
		else{
			return NO_TIME;
		}
	}

	private Date getTime(Task task, Date date, int whichTime) {
		if(date == null){
			System.out.println("getTime, startDate is null");
		}
		int time = 0;
		Calendar cal = Calendar.getInstance();
		
		if(whichTime == START_TIME){
		
			time = Integer.parseInt(task.getStartTime());
		
		}
		else if(whichTime == END_TIME){
			time = Integer.parseInt(task.getEndTime());
		}
		else{
			//NO_TIME
			System.out.println("getTime: whichTime is not start or end");
			return null;
		}
		
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY,time/100);
		cal.set(Calendar.MINUTE, time%100);
		return cal.getTime();
	}

	private int numOfDateNull(Task task){
		int num = 0;
		if(task.getStartDate() == null){
			num++;
		}
		if(task.getDeadLine() == null){
			num++;
		}
		if(task.getEndDate() == null){
			num++;
		}
		
		return num;
	}
	
	private int findStartDate(Task task){
		if(task.getStartDate() != null){
			return START_DATE;
		}
		else if(task.getDeadLine() != null){
			return DEADLINE_DATE;
		}
		else if(task.getEndDate() != null){
			return END_DATE;
		}
		else{
			return NO_DATE;
		}
	}
	
	private int findEndDate(Task task){
		if(task.getEndDate() != null){
			return END_DATE;
		}
		else if(task.getDeadLine() != null){
			return DEADLINE_DATE;
		}
		else if(task.getStartDate() != null){
			return START_DATE;
		}
		else{
			return NO_DATE;
		}
	}
	
	private Date getDate(Task task, int whichDate){
		if(whichDate == START_DATE){
			return dateGetter.parse(task.getStartDate(), new ParsePosition(0));
		}
		else if(whichDate == DEADLINE_DATE){
			return dateGetter.parse(task.getDeadLine(), new ParsePosition(0));
		}
		else if(whichDate == END_DATE){
			return dateGetter.parse(task.getEndDate(), new ParsePosition(0));
		}
		else{
			//whichDate == NO_DATE
			return null;
		}
	}
	
	private int numOfTimeNull(Task task){
		int num = 0;
		
		if(task.getStartTime() == null){
			num++;
		}
		if(task.getEndTime() == null){
			num++;
		}
		return num;
	}
	
	private int findStartTime(Task task){
		if(task.getStartTime() != null){
			return START_TIME;
		}
		else if(task.getEndTime() != null){
			return END_TIME;
		}
		else{
			return NO_TIME;
		}
	}
	
}
