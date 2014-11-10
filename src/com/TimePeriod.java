package com;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author A0116018R
 * This class stores a time period set using String.
 * Date string format is dd/mm/yyyy.
 * Time string format is hhmm, 24 hour clock.
 * 
 * Start and end dates and times can be accessed through String
 * or Date form. 
 * 
 * There must be a start date, start time and end time in every timePeriod.
 * 
 * Supports the toString method.
 */
public class TimePeriod {

	private static final SimpleDateFormat dateGetter = new SimpleDateFormat("dd/MM/yyyy");
	private static final SimpleDateFormat timeGetter = new SimpleDateFormat("hhmm");
	private static final SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("EEE, MMM d hh:mm");
	private static final SimpleDateFormat timeOnlyFormatter = new SimpleDateFormat("hh:mm");

	private String startDateString, endDateString, startTimeString, endTimeString;
	private Date startDate, endDate;


	//constructors
	public TimePeriod() {
		startDateString = endDateString = startTimeString = endTimeString = null;
		startDate = endDate = null;
	}

	public TimePeriod(String startDate, String endDate, String startTime, String endTime) {
		this.startDateString = startDate;
		this.endDateString = endDate;
		this.startTimeString = startTime;
		this.endTimeString = endTime;
		
		startDate = endDate = null;
		
		convertStartDate();
		convertEndDate();
	}

	//mutators
	public void setStartDateString(String startDate) {
		this.startDateString = startDate;
		convertStartDate();
	}

	public void setEndDateString(String endDate) {
		this.endDateString = endDate;
		convertEndDate();
	}

	public void setStartTimeString(String startTime) {
		this.startTimeString = startTime;
		convertStartDate();
	}

	public void setEndTimeString(String endTime) {
		this.endTimeString = endTime;
		convertEndDate();
	}

	//accessors
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

	public Date getStartDate(){
		return startDate;
	}

	public Date getEndDate(){
		return endDate;
	}

	@Override
	public String toString() {
		String returnString;
		
		if(endDateString != null || !endDateString.equals("")){
			returnString = dateTimeFormatter.format(startDate) + " - " + dateTimeFormatter.format(endDate);
		}else{
			returnString = dateTimeFormatter.format(startDate) + " - " + timeOnlyFormatter.format(endDate);
		}
		
		return returnString;
	}

	private void convertStartDate(){
		Calendar startDateCal = Calendar.getInstance();

		if(startDateString != null || !startDateString.equals("")){
			startDateCal.setTime(dateGetter.parse(startDateString, new ParsePosition(0)));
		}

		if(startTimeString != null || !startTimeString.equals("")){
			Date temp = timeGetter.parse(startTimeString, new ParsePosition(0));
			Calendar startTimeCal = Calendar.getInstance();
			startTimeCal.setTime(temp);
			startDateCal.set(Calendar.HOUR_OF_DAY, startTimeCal.get(Calendar.HOUR_OF_DAY));
			startDateCal.set(Calendar.MINUTE, startTimeCal.get(Calendar.MINUTE));
		}

		startDate = startDateCal.getTime();
	}

	private void convertEndDate(){
		Calendar endDateCal = Calendar.getInstance();

		if(endDateString != null || !endDateString.equals("")){
			endDateCal.setTime(dateGetter.parse(endDateString, new ParsePosition(0)));
		}else if(startDateString != null || !startDateString.equals("")){
			endDateCal.setTime(dateGetter.parse(startDateString, new ParsePosition(0)));
		}

		if(endTimeString != null || !endTimeString.equals("")){
			Date temp = timeGetter.parse(endTimeString, new ParsePosition(0));
			Calendar endTimeCal = Calendar.getInstance();
			endTimeCal.setTime(temp);
			endDateCal.set(Calendar.HOUR_OF_DAY, endTimeCal.get(Calendar.HOUR_OF_DAY));
			endDateCal.set(Calendar.MINUTE, endTimeCal.get(Calendar.MINUTE));
		}

		endDate = endDateCal.getTime();
	}

}
