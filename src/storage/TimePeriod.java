package storage;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Jireh
 */
public class TimePeriod {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
	private static final SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
	
	private String startDateString, endDateString, startTimeString, endTimeString;
    private Date startDate, endDate;
    

    //constructors
    public TimePeriod() {
    }

    public TimePeriod(String startDate, String endDate, String startTime, String endTime) {
        this.startDateString = startDate;
        this.endDateString = endDate;
        this.startTimeString = startTime;
        this.endTimeString = endTime;
        
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
        String returnString = String.format("%s %s - %s %s", timeFormat.format(startTimeString),
                dateFormat.format(startDateString), timeFormat.format(endTimeString),
                dateFormat.format(endDateString));
        return returnString;
    }
    
    private void convertStartDate(){
    	
    }
    
    private void convertEndDate(){
    	
    }

}
