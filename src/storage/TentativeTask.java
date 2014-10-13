package storage;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Jireh
 */
public class TentativeTask {

    private String event;
    private static ArrayList<TimePeriod> globalBlockedTimePeriods;
    private ArrayList<TimePeriod> blockedTimePeriods;

    public TentativeTask(String event) {
        blockedTimePeriods = new ArrayList<TimePeriod>();
        this.event = event;
    }

    public boolean blockTimePeriod(TimePeriod tp) {
        for (TimePeriod period : globalBlockedTimePeriods) {
            if (!isValidTimePeriod(tp, period)) {
                return false;
            }
        }
        globalBlockedTimePeriods.add(tp);
        blockedTimePeriods.add(tp);
        return true;
    }

    public ArrayList<TimePeriod> getBlockedTimePeriods() {
        return blockedTimePeriods;
    }

    public static ArrayList<TimePeriod> getGlobalBlockedTimePeriods() {
        return globalBlockedTimePeriods;
    }

    private static boolean isValidTimePeriod(TimePeriod tp, TimePeriod toCompare) {
        Date start = tp.getStartDate();
        Date end = tp.getEndDate();
        Date compareStart = toCompare.getStartDate();
        Date compareEnd = toCompare.getEndDate();

        boolean isStartBeforeEnd = start.before(end);
        boolean isStartValid = !(start.after(compareStart) && start.before(compareEnd));
        boolean isEndValid = !(end.after(compareStart) && end.before(compareEnd));

        return isStartBeforeEnd && isStartValid && isEndValid;
    }
}
