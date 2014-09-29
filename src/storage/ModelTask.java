package storage;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ModelTask {
	private StringProperty event;
	private StringProperty dateStringProperty;
	private StringProperty timeStringProperty;
	private StringProperty positionStringProperty;

	private SimpleDateFormat standardFormatter, dateFormatter,
			startDateFormatter, endDateFormatter, timeFormatter;
	private Date startDate, endDate, startTime, endTime;
	private String startDateString, endDateString, startTimeString,
			endTimeString;
	private int position;
	private boolean isDone;

	// constructor
	public ModelTask() {
	}

	public ModelTask(String event, Date startDate, Date endDate,
			Date startTime, Date endTime, int position) {
		standardFormatter = new SimpleDateFormat("dd/MM/yyyy");
		dateFormatter = new SimpleDateFormat("EEE, MMM d");
		startDateFormatter = new SimpleDateFormat("d");
		endDateFormatter = new SimpleDateFormat("- d MMM");
		timeFormatter = new SimpleDateFormat("HHmm");

		this.event = new SimpleStringProperty(event);
		this.dateStringProperty = new SimpleStringProperty();
		this.timeStringProperty = new SimpleStringProperty();
		this.positionStringProperty = new SimpleStringProperty();

		setDate(startDate, endDate);
		setTime(startTime, endTime);
		setStartDateString(startDate);
		setEndDateString(endDate);
		setStartTimeString(startTime);
		setEndTimeString(startTime);
		setPosition(position);
		this.isDone = false;
	}

	// mutators
	public void setEvent(String event) {
		this.event.set(event);
	}

	public void setPosition(int position) {
		this.position = position;
		positionStringProperty.set(String.valueOf(position));
	}

	public void setIsDone(boolean isDone) {
		this.isDone = isDone;
	}

	public void setStartDateString(Date startDate) {
		if (startDate != null) {
			startDateString = standardFormatter.format(startDate);
		} else {
			startDateString = null;
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

		if (startDate != null && endDate != null) {
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

	public String getEvent() {
		return event.get();
	}

	public int getPosition() {
		return position;
	}

	public boolean isDone() {
		return this.isDone;
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
}
