package storage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ModelTask {
	//variables are package-private for sub-classes to access these variables
	
	//date formatters for all tasks
	final static SimpleDateFormat standardFormatter = new SimpleDateFormat("dd/MM/yyyy");
	final static SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE, MMM d");
	final static SimpleDateFormat startDateOneMonthFormatter = new SimpleDateFormat("d ");
	final static SimpleDateFormat startDateTwoMonthFormatter = new SimpleDateFormat("d MMM");
	final static SimpleDateFormat endDateFormatter = new SimpleDateFormat("- d MMM");
	final static SimpleDateFormat timeFormatter = new SimpleDateFormat("HHmm");
	
	final static boolean isTentative = false;

	StringProperty event;
	StringProperty dateStringProperty;
	StringProperty timeStringProperty;
	StringProperty positionStringProperty;
	BooleanProperty isDoneBooleanProperty;
	BooleanProperty isUrgentBooleanProperty;

	ObjectProperty<EventAndDone> eventAndDoneProperty;

	Date startDate, endDate, startTime, endTime, deadLine;
	String startDateString, endDateString, startTimeString,
	endTimeString, deadLineString;
	int position;
	boolean isDone, isUrgent;

	EventAndDone eventAndDone;

	// constructors
	public ModelTask() {
		this.event = new SimpleStringProperty();
		this.dateStringProperty = new SimpleStringProperty();
		this.timeStringProperty = new SimpleStringProperty();
		this.positionStringProperty = new SimpleStringProperty();
		this.isDoneBooleanProperty = new SimpleBooleanProperty();
		this.isUrgentBooleanProperty = new SimpleBooleanProperty();
		this.eventAndDoneProperty = new SimpleObjectProperty<EventAndDone>();
	}

	public ModelTask(String event, Date startDate, Date endDate,
			Date startTime, Date endTime, int position, boolean isDone, boolean isUrgent) {

		this.event = new SimpleStringProperty(event);
		this.dateStringProperty = new SimpleStringProperty();
		this.timeStringProperty = new SimpleStringProperty();
		this.positionStringProperty = new SimpleStringProperty();
		this.isDoneBooleanProperty = new SimpleBooleanProperty();
		this.isUrgentBooleanProperty = new SimpleBooleanProperty();
		this.eventAndDoneProperty = new SimpleObjectProperty<EventAndDone>();

		this.eventAndDone = new EventAndDone(event, isDone);

		setEvent(event);
		setDate(startDate, endDate);
		setTime(startTime, endTime);
		setStartDateString(startDate);
		setEndDateString(endDate);
		setStartTimeString(startTime);
		setEndTimeString(endTime);
		setPosition(position);
		setDeadLineString(deadLine);
		setIsDone(isDone);
		setIsUrgent(isUrgent);
	}

	//method to return a copy of a modelTask
	public ModelTask copyTask(){

		ModelTask temp = new ModelTask();

		temp.setEvent(getEvent());           
		temp.setDate(startDate, endDate);
		temp.setTime(startTime, endTime);
		temp.setStartDateString(startDate);
		temp.setEndDateString(endDate);
		temp.setStartTimeString(startTime);
		temp.setEndTimeString(endTime);
		temp.setPosition(position);
		temp.setDeadLineString(deadLine);
		temp.setIsDone(isDone);
		temp.setIsUrgent(isUrgent);
		
		return temp;
	}

	// mutators
	public void setEvent(String event) {
		this.event.set(event);
		this.eventAndDone = new EventAndDone(event, isDone);
		eventAndDoneProperty.setValue(eventAndDone);
	}

	public void setPosition(int position) {
		this.position = position;
		positionStringProperty.set(String.valueOf(position));
	}

	public void setIsDone(boolean isDone) {
		this.isDone = isDone;
		isDoneBooleanProperty.set(isDone);
		this.eventAndDone = new EventAndDone(eventAndDone.getEvent(), isDone);
		eventAndDoneProperty.set(eventAndDone);
	}

	public void setIsUrgent(boolean isUrgent){
		this.isUrgent = isUrgent;
		isUrgentBooleanProperty.set(isUrgent);
	}

	public void setStartDateString(Date startDate) {
		if (startDate != null) {
			startDateString = standardFormatter.format(startDate);
		} else {
			startDateString = null;
		}
	}

	private void setDeadLineString(Date deadLine) {
		if (deadLine != null) {
			deadLineString = standardFormatter.format(deadLine);
		} else {
			deadLineString = null;
		}
	}

	public void setEndDateString(Date endDate) {
		if (endDate != null) {
			endDateString = standardFormatter.format(endDate);
		} else {
			endDateString = null;
		}
	}

	public void setStartTimeString(Date startTime) {
		if (startTime != null) {
			startTimeString = timeFormatter.format(startTime);
		} else {
			startTimeString = null;
		}
	}

	public void setEndTimeString(Date endTime) {
		if (endTime != null) {
			endTimeString = timeFormatter.format(endTime);
		} else {
			endTimeString = null;
		}
	}

	public void setDate(Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
		SimpleDateFormat startDateFormatter;

		// setting deadline
		if (endDate != null) {
			this.deadLine = endDate;
		} else if (startDate != null) {
			this.deadLine = startDate;
		} else {
			this.deadLine = null;
		}

		if (startDate != null && endDate != null) {
			if (!isSameMonth(startDate, endDate)) {
				startDateFormatter = startDateTwoMonthFormatter;
			}
			else{
				startDateFormatter = startDateOneMonthFormatter;
			}
			String startDateString = startDateFormatter.format(startDate);
			String endDateString = endDateFormatter.format(endDate);
			this.dateStringProperty.set(startDateString + endDateString);

		} else if (startDate == null && endDate == null) {
			this.dateStringProperty.set("");
		} else if (startDate != null) {
			String dateString = dateFormatter.format(startDate);
			this.dateStringProperty.set(dateString);
		} else if (endDate != null) {
			String dateString = dateFormatter.format(endDate);
			this.dateStringProperty.set(dateString);
		} else {
			this.dateStringProperty.set("");
		}

	}

	private boolean isSameMonth(Date startDate, Date endDate) {
		Calendar cal = Calendar.getInstance();
		int month1;
		int month2;

		cal.setTime(startDate);
		month1 = cal.get(Calendar.MONTH);
		cal.setTime(endDate);
		month2 = cal.get(Calendar.MONTH);

		return month1 == month2;
	}

	public void setTime(Date startTime, Date endTime) {
		this.startTime = startTime;
		this.endTime = endTime;

		if (startTime != null && endTime != null) {
			String startTimeString = timeFormatter.format(startTime);
			String endTimeString = timeFormatter.format(endTime);
			this.timeStringProperty
			.set(startTimeString + " - " + endTimeString);
		} else if (startTime == null && endTime == null) {
			this.timeStringProperty.set("");
		} else if (startTime != null) {
			String timeString = timeFormatter.format(startTime);
			this.timeStringProperty.set(timeString);
		} else if (endTime != null) {
			String timeString = timeFormatter.format(endTime);
			this.timeStringProperty.set(timeString);
		} else {
			this.timeStringProperty.set("");
		}

	}

	// accessors
	public String getStartDateString() {
		return startDateString;
	}

	public String getEndDateString() {
		return endDateString;
	}

	public String getStartTimeString() {
		return startTimeString;
	}

	public String getEndTimeString() {
		return endTimeString;
	}

	public String getDeadLineString() {
		return deadLineString;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	public String getEvent() {
		return event.get();
	}

	public int getPosition() {
		return position;
	}

	public boolean isDone() {
		return isDone;
	}

	public boolean isUrgent(){
		return isUrgent;
	}

	public boolean isTentative(){
		return isTentative;
	}
	
	public StringProperty getDateStringProperty() {
		return dateStringProperty;
	}

	public StringProperty getTimeStringProperty() {
		return timeStringProperty;
	}

	public StringProperty getEventProperty() {
		return event;
	}

	public StringProperty getPositionStringProperty() {
		return positionStringProperty;
	}

	public BooleanProperty getisDoneBooleanProperty(){
		return isDoneBooleanProperty;
	}

	public BooleanProperty getIsUrgentBooleanProperty(){
		return isUrgentBooleanProperty;
	}

	public ObjectProperty<EventAndDone> getEventAndDoneProperty(){
		return eventAndDoneProperty;
	}

	
	/**
	 * this returns a string representation of a ModelTask for saving to text file
	 * format is: event;startdate;enddate;starttime;endtime;isDone;isUrgent
	 */
	public String toString() {

		String event = getEvent();
		String startDate = getStartDateString();
		String endDate = getEndDateString();
		String startTime = getStartTimeString();
		String endTime = getEndTimeString();
		String deadLine = getDeadLineString();
		boolean isDone = isDone();
        boolean isUrgent = isUrgent();
		
		
		String saveString =event + ";" + startDate + ";" + endDate + ";" 
		+ startTime + ";" +endTime + ";" + deadLine +";" + isDone+ ";" + isUrgent;
		return saveString;
	}


}
