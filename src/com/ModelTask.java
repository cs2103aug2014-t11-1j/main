package com;
/**
 * @author A0116018R
 * 
 * This class is used by GUI to store information about a task.
 * This class may be extended for other kinds of tasks.
 */

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
	final static SimpleDateFormat STANDARD_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
	final static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("EEE, MMM d");
	final static SimpleDateFormat START_DATE_ONE_MONTH_FORMATTER = new SimpleDateFormat("d ");
	final static SimpleDateFormat START_DATE_TWO_MONTH_FORMATTER = new SimpleDateFormat("d MMM");
	final static SimpleDateFormat END_DATE_FORMATTER = new SimpleDateFormat("- d MMM");
	final static SimpleDateFormat TIME_FORMATTER = new SimpleDateFormat("HH:mm");
	
	final static boolean IS_TENTATIVE = false;

	StringProperty event_;
	StringProperty dateStringProperty_;
	StringProperty timeStringProperty_;
	StringProperty positionStringProperty_;
	BooleanProperty isDoneBooleanProperty_;
	BooleanProperty isUrgentBooleanProperty_;

	ObjectProperty<EventAndDone> eventAndDoneProperty_;

	Date startDate_, endDate_, startTime_, endTime_, deadLine_;
	String startDateString_, endDateString_, startTimeString_,
	endTimeString_, deadLineString_;
	int position_;
	boolean isDone_, isUrgent_;

	EventAndDone eventAndDone_;

	// constructors
	/**
	 * This constructs an empty modeltask.
	 * Various fields can be filled in later.
	 */
	public ModelTask() {
		this.event_ = new SimpleStringProperty();
		this.dateStringProperty_ = new SimpleStringProperty();
		this.timeStringProperty_ = new SimpleStringProperty();
		this.positionStringProperty_ = new SimpleStringProperty();
		this.isDoneBooleanProperty_ = new SimpleBooleanProperty();
		this.isUrgentBooleanProperty_ = new SimpleBooleanProperty();
		this.eventAndDoneProperty_ = new SimpleObjectProperty<EventAndDone>();
	}
	
	/**
	 * This constructs a ModelTask with the various parameters in one go.
	 * @param event
	 * @param startDate
	 * @param endDate
	 * @param startTime
	 * @param endTime
	 * @param position
	 * @param isDone
	 * @param isUrgent
	 */
	public ModelTask(String event, Date startDate, Date endDate,
			Date startTime, Date endTime, int position, boolean isDone, boolean isUrgent) {

		this.event_ = new SimpleStringProperty(event);
		this.dateStringProperty_ = new SimpleStringProperty();
		this.timeStringProperty_ = new SimpleStringProperty();
		this.positionStringProperty_ = new SimpleStringProperty();
		this.isDoneBooleanProperty_ = new SimpleBooleanProperty();
		this.isUrgentBooleanProperty_ = new SimpleBooleanProperty();
		this.eventAndDoneProperty_ = new SimpleObjectProperty<EventAndDone>();

		this.eventAndDone_ = new EventAndDone(event, isDone);

		setEvent(event);
		setDate(startDate, endDate);
		setTime(startTime, endTime);
		setStartDateString(startDate);
		setEndDateString(endDate);
		setStartTimeString(startTime);
		setEndTimeString(endTime);
		setPosition(position);
		setDeadLineString(deadLine_);
		setIsDone(isDone);
		setIsUrgent(isUrgent);
	}

	//method to return a copy of a modelTask
	/**
	 * This returns a new ModelTask that has the
	 * exact same attributes of the current ModelTask.
	 * @return ModelTask
	 */
	public ModelTask copyTask(){

		ModelTask temp = new ModelTask();

		temp.setEvent(getEvent());           
		temp.setDate(startDate_, endDate_);
		temp.setTime(startTime_, endTime_);
		temp.setStartDateString(startDate_);
		temp.setEndDateString(endDate_);
		temp.setStartTimeString(startTime_);
		temp.setEndTimeString(endTime_);
		temp.setPosition(position_);
		temp.setDeadLineString(deadLine_);
		temp.setIsDone(isDone_);
		temp.setIsUrgent(isUrgent_);
		
		return temp;
	}

	// mutators
	public void setEvent(String event) {
		this.event_.set(event);
		this.eventAndDone_ = new EventAndDone(event, isDone_);
		eventAndDoneProperty_.setValue(eventAndDone_);
	}

	public void setPosition(int position) {
		this.position_ = position;
		positionStringProperty_.set(String.valueOf(position));
	}

	public void setIsDone(boolean isDone) {
		this.isDone_ = isDone;
		isDoneBooleanProperty_.set(isDone);
		this.eventAndDone_ = new EventAndDone(eventAndDone_.getEvent(), isDone);
		eventAndDoneProperty_.set(eventAndDone_);
	}

	public void setIsUrgent(boolean isUrgent){
		this.isUrgent_ = isUrgent;
		isUrgentBooleanProperty_.set(isUrgent);
	}

	public void setStartDateString(Date startDate) {
		if (startDate != null) {
			startDateString_ = STANDARD_FORMATTER.format(startDate);
		} else {
			startDateString_ = null;
		}
	}

	private void setDeadLineString(Date deadLine) {
		if (deadLine != null) {
			deadLineString_ = STANDARD_FORMATTER.format(deadLine);
		} else {
			deadLineString_ = null;
		}
	}

	public void setEndDateString(Date endDate) {
		if (endDate != null) {
			endDateString_ = STANDARD_FORMATTER.format(endDate);
		} else {
			endDateString_ = null;
		}
	}

	public void setStartTimeString(Date startTime) {
		if (startTime != null) {
			startTimeString_ = TIME_FORMATTER.format(startTime);
		} else {
			startTimeString_ = null;
		}
	}

	public void setEndTimeString(Date endTime) {
		if (endTime != null) {
			endTimeString_ = TIME_FORMATTER.format(endTime);
		} else {
			endTimeString_ = null;
		}
	}
	
	/**
	 * This sets the start and end dates.
	 * Either or both fields maybe null
	 * depending on whether the task has those date attributes.
	 * @param startDate
	 * @param endDate
	 */
	public void setDate(Date startDate, Date endDate) {
		this.startDate_ = startDate;
		this.endDate_ = endDate;
		SimpleDateFormat startDateFormatter;

		// setting deadline
		if (endDate != null) {
			this.deadLine_ = endDate;
		} else if (startDate != null) {
			this.deadLine_ = startDate;
		} else {
			this.deadLine_ = null;
		}

		if (startDate != null && endDate != null) {
			if (!isSameMonth(startDate, endDate)) {
				startDateFormatter = START_DATE_TWO_MONTH_FORMATTER;
			}
			else{
				startDateFormatter = START_DATE_ONE_MONTH_FORMATTER;
			}
			String startDateString = startDateFormatter.format(startDate);
			String endDateString = END_DATE_FORMATTER.format(endDate);
			this.dateStringProperty_.set(startDateString + endDateString);

		} else if (startDate == null && endDate == null) {
			this.dateStringProperty_.set("");
		} else if (startDate != null) {
			String dateString = DATE_FORMATTER.format(startDate);
			this.dateStringProperty_.set(dateString);
		} else if (endDate != null) {
			String dateString = DATE_FORMATTER.format(endDate);
			this.dateStringProperty_.set(dateString);
		} else {
			this.dateStringProperty_.set("");
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
	
	/**
	 * This sets the timings. 
	 * Either one or both may be null
	 * if the task does not have these attributes.
	 * @param startTime
	 * @param endTime
	 */
	public void setTime(Date startTime, Date endTime) {
		this.startTime_ = startTime;
		this.endTime_ = endTime;

		if (startTime != null && endTime != null) {
			String startTimeString = TIME_FORMATTER.format(startTime);
			String endTimeString = TIME_FORMATTER.format(endTime);
			this.timeStringProperty_
			.set(startTimeString + " - " + endTimeString);
		} else if (startTime == null && endTime == null) {
			this.timeStringProperty_.set("");
		} else if (startTime != null) {
			String timeString = TIME_FORMATTER.format(startTime);
			this.timeStringProperty_.set(timeString);
		} else if (endTime != null) {
			String timeString = TIME_FORMATTER.format(endTime);
			this.timeStringProperty_.set(timeString);
		} else {
			this.timeStringProperty_.set("");
		}

	}

	// accessors
	public String getStartDateString() {
		return startDateString_;
	}

	public String getEndDateString() {
		return endDateString_;
	}

	public String getStartTimeString() {
		return startTimeString_;
	}

	public String getEndTimeString() {
		return endTimeString_;
	}

	public String getDeadLineString() {
		return deadLineString_;
	}

	public Date getStartDate() {
		return startDate_;
	}

	public Date getEndDate() {
		return endDate_;
	}

	public Date getStartTime() {
		return startTime_;
	}

	public Date getEndTime() {
		return endTime_;
	}

	public Date getDeadLine() {
		return deadLine_;
	}

	public String getEvent() {
		return event_.get();
	}

	public int getPosition() {
		return position_;
	}

	public boolean isDone() {
		return isDone_;
	}

	public boolean isUrgent(){
		return isUrgent_;
	}

	public boolean isTentative(){
		return IS_TENTATIVE;
	}
	
	public StringProperty getDateStringProperty() {
		return dateStringProperty_;
	}

	public StringProperty getTimeStringProperty() {
		return timeStringProperty_;
	}

	public StringProperty getEventProperty() {
		return event_;
	}

	public StringProperty getPositionStringProperty() {
		return positionStringProperty_;
	}

	public BooleanProperty getisDoneBooleanProperty(){
		return isDoneBooleanProperty_;
	}

	public BooleanProperty getIsUrgentBooleanProperty(){
		return isUrgentBooleanProperty_;
	}

	public ObjectProperty<EventAndDone> getEventAndDoneProperty(){
		return eventAndDoneProperty_;
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
