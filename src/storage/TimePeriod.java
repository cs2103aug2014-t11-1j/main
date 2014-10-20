package storage;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Jireh
 */
public class TimePeriod {

    private Date startDate, endDate, startTime, endTime;
    private SimpleDateFormat dateFormat, timeFormat;

    //constructors
    public TimePeriod() {
        startDate = new Date();
        startTime = new Date();
        endDate = new Date();
        endTime = new Date();

        dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        timeFormat = new SimpleDateFormat("hh:mm");
    }

    public TimePeriod(Date startDate, Date endDate, Date startTime, Date endTime) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;

        dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        timeFormat = new SimpleDateFormat("hh:mm");
    }

    //mutators
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    //accessors
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

    @Override
    public String toString() {
        String returnString = String.format("%s %s - %s %s", timeFormat.format(startTime),
                dateFormat.format(startDate), timeFormat.format(endTime),
                dateFormat.format(endDate));
        return returnString;
    }

}
