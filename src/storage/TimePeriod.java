package storage;

import java.util.Date;

/**
 *
 * @author Jireh
 */
public class TimePeriod {

    private Date startDate, endDate;

    public TimePeriod(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
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
