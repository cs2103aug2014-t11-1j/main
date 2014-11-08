//@author A0111370Y -unused
//no time to integrate.
package logic;

import java.util.Date;

import com.TentativeTask;
import com.TimePeriod;

public class BlockTentative extends CommandFactory {

    private boolean isDone_;

    protected BlockTentative(String input) {
        execute(input);
        updateGlobalBlockedTimePeriods();
        //currently no undo/redo for this command
    }

    @Override
    protected void execute(String input) {

        TentativeTask temp = pf.getTentative(input);
        if (checkTimePeriods(temp)) {
            addTimePeriods(temp);
            isDone_ = true;
            CommandExecutor.setUserFeedBack(FeedbackMessages.SUCCESS_TENTATIVE_MESSAGE);
            CommandExecutor.setGuiFeedBack(FeedbackMessages.NORMAL_STATE);
        } else {
            CommandExecutor.setUserFeedBack(FeedbackMessages.ERROR_TENTATIVE_MESSAGE);
        }
    }

    @Override
    protected boolean isDone() {
        return isDone_;
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

    private static boolean checkTimePeriods(TentativeTask tentative) {
        for (TimePeriod tp : tentative.getTimePeriods()) {
            for (TimePeriod period : globalBlockedTimePeriods) {
                if (!isValidTimePeriod(tp, period)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void addTimePeriods(TentativeTask tentative) {
        for (TimePeriod tp : tentative.getTimePeriods()) {
            tentative.addGlobalBlockedTimePeriods(tp);
        }
    }
}
