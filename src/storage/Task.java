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
