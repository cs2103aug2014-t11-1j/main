package storage;

/**
 * This class is used to store information about a task
 * entered by a user during runtime.
 * 
 */


public class Task {
	
	/**
	 * Class Attributes
	 */	
	

	private final int INDEX_OF_EVENT = 0;
	private final int INDEX_OF_STARTDATE = 1;
	private final int INDEX_OF_ENDDATE = 2;
	private final int INDEX_OF_STARTTIME = 3;
	private final int INDEX_OF_ENDTIME = 4;
	private final int INDEX_OF_ISDONE = 5;
	
	
	private String taskDescription;
	private String startDate;
	private String startTime;
	private String endDate;
	private String endTime;
	private String deadLine;
	private boolean isDone;
	
	/**
	 * Constructor
	 */
	public Task(String taskDescription, String startDate, String startTime,
				String endDate, String endTime, String deadLine){
		this.taskDescription = taskDescription;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endDate = endDate;
		this.endTime = endTime;
		this.deadLine = deadLine;
		this.isDone = false;
	}
	
	
	//this constructor is meant for copying files to observablelist
	public Task(String str,boolean fromFile){
String[] array = str.split(";");
		
		String input;
		this.taskDescription = array[INDEX_OF_EVENT];
		
		input = array[INDEX_OF_STARTDATE];
		if(input.equals("null")){
			this.startDate = null;
		}else{
			this.startDate = input;
		}
		
		input = array[INDEX_OF_ENDDATE];
		if(input.equals("null")){
			this.endDate = null;
		}else{
			this.endDate = input;
		}
		
		input = array[INDEX_OF_STARTTIME];
		if(input.equals("null")){
			this.startTime = null;
		}else{
			this.startTime = input;
		}
		
		input = array[INDEX_OF_ENDTIME];
		if(input.equals("null")){
			this.endTime = null;
		}else{
			this.endTime = input;
		}
		
		input = array[INDEX_OF_ISDONE];
		if(input.equalsIgnoreCase("true")){
			this.isDone = true;
		}else{
			this.isDone = false;
		}
	}
	
	
	/**
	 * Accessors
	 */
	public String getTaskDescription(){
		return taskDescription;
	}
	public String getStartDate(){
		return startDate;
	}
	public String getStartTime(){
		return startTime;
	}
	public String getEndDate(){
		return endDate;
	}
	public String getEndTime(){
		return endTime;
	}
	public String getDeadLine(){
		return deadLine;
	}
	public boolean isDone(){
		return this.isDone;
	}
	
	/**
	 * Mutators
	 */
	public void setTaskDescription(String taskDescription){
		this.taskDescription = taskDescription;
	}
	public void setStartDate(String startDate){
		this.startDate = startDate;
	}
	public void setStartTime(String startTime){
		this.startTime = startTime;
	}
	public void setEndDate(String endDate){
		this.endDate = endDate;
	}
	public void setEndTime(String endTime){
		this.endTime = endTime;
	}
	public void setIsDone(boolean isDone){
		this.isDone = isDone;
	}
}
