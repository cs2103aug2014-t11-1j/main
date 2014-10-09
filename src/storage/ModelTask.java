package storage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ModelTask {
	private StringProperty event;
	private StringProperty dateStringProperty;
	private StringProperty timeStringProperty;
	private StringProperty positionStringProperty;
	private BooleanProperty isDoneBooleanProperty;

	private SimpleDateFormat standardFormatter, dateFormatter,
			startDateFormatter, endDateFormatter, timeFormatter;
	private Date startDate, endDate, startTime, endTime, deadLine;
	private String startDateString, endDateString, startTimeString,
			endTimeString, deadLineString;
	private int position;
	private boolean isDone;

	// constructor
	public ModelTask() {
	}

	public ModelTask(String event, Date startDate, Date endDate,
		Date startTime, Date endTime, int position, boolean isDone) {
		standardFormatter = new SimpleDateFormat("dd/MM/yyyy");
		dateFormatter = new SimpleDateFormat("EEE, MMM d");
		startDateFormatter = new SimpleDateFormat("d ");
		endDateFormatter = new SimpleDateFormat("- d MMM");
		timeFormatter = new SimpleDateFormat("HHmm");

		this.event = new SimpleStringProperty(event);
		this.dateStringProperty = new SimpleStringProperty();
		this.timeStringProperty = new SimpleStringProperty();
		this.positionStringProperty = new SimpleStringProperty();
		this.isDoneBooleanProperty = new SimpleBooleanProperty();

		setDate(startDate, endDate);
		setTime(startTime, endTime);
		setStartDateString(startDate);
		setEndDateString(endDate);
		setStartTimeString(startTime);
		setEndTimeString(endTime);
		setPosition(position);
		setDeadLineString(deadLine);
		setIsDone(isDone);
	}
        
        public ModelTask(ModelTask mt){
            standardFormatter = new SimpleDateFormat("dd/MM/yyyy");
            dateFormatter = new SimpleDateFormat("EEE, MMM d");
            startDateFormatter = new SimpleDateFormat("d ");
            endDateFormatter = new SimpleDateFormat("- d MMM");
            timeFormatter = new SimpleDateFormat("HHmm");
            
            this.event = new SimpleStringProperty();
            this.dateStringProperty = new SimpleStringProperty();
            this.timeStringProperty = new SimpleStringProperty();
            this.positionStringProperty = new SimpleStringProperty();
            this.isDoneBooleanProperty = new SimpleBooleanProperty();
            
            event = mt.event;            
            setDate(mt.startDate, mt.endDate);
            setTime(mt.startTime, mt.endTime);
            setStartDateString(mt.startDate);
            setEndDateString(mt.endDate);
            setStartTimeString(mt.startTime);
            setEndTimeString(mt.endTime);
            setPosition(mt.position);
            setDeadLineString(mt.deadLine);
            setIsDone(mt.isDone);
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
		isDoneBooleanProperty.set(isDone);
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
				startDateFormatter = new SimpleDateFormat("d MMM ");
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
	
	public BooleanProperty getisDoneBooleanProperty(){
		return isDoneBooleanProperty;
	}
}
