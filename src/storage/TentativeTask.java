package storage;

import java.util.ArrayList;

/**
 *
 * @author Jireh
 */
public class TentativeTask {

    private String event;
    private ArrayList<TimePeriod> blockedTimePeriods;

    public TentativeTask(String event) {
        blockedTimePeriods = new ArrayList<TimePeriod>();
        this.event = event;
    }

    public void blockTimePeriod(TimePeriod tp) {
        blockedTimePeriods.add(tp);
    }
}
