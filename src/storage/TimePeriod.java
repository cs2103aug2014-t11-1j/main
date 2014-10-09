package storage;

import java.util.Date;

/**
 *
 * @author Jireh
 */
public class TimePeriod {

    private Date startTime, endTime, startDate, endDate;

    public TimePeriod(Date startTime, Date endTime, Date startDate, Date endDate) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

}
