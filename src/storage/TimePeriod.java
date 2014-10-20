package storage;

import java.util.Date;

/**
 *
 * @author Jireh
 */
public class TimePeriod {

    private Date startDate, endDate, startTime, endTime;
    
    //constructors
    public TimePeriod(){
    	
    }
    
    public TimePeriod(Date startDate, Date endDate, Date startTime, Date endTime) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    //mutators
    public void setStartDate(Date startDate){
    	this.startDate = startDate;
    }
    
    public void setEndDate(Date endDate){
    	this.endDate = endDate;
    }
    
    public void setStartTime(Date startTime){
    	this.startTime = startTime;
    }
    
    public void setEndTime(Date endTime){
    	this.endTime = endTime;
    }
    
    //accessors
    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
    
    public Date getStartTime(){
    	return startTime;
    }
    
    public Date getEndTime(){
    	return endTime;
    }
    
    public String toString(){
    	//TODO: make a toString function
    	return null;
    }

}
