package logic;

import com.ModelTask;

/**
 *
 * @author Jireh
 */
public class Search extends CommandFactory {

    private boolean isDone;

    protected Search(String input) {
        tempList.clear();
        execute(input);
        updateTempList();
    }

    @Override
    protected void execute(String input) {

        for (ModelTask task : list) {
            if (task.getEvent().contains(input)) {
                tempList.add(task);
            }
        }
        if (tempList.isEmpty()) {
            CommandExecutor.setUserFeedBack(FeedbackMessages.ERROR_SEARCH_MESSAGE);
            CommandExecutor.setGuiFeedBack(FeedbackMessages.NORMAL_STATE);
        } else {
            CommandExecutor.setUserFeedBack(FeedbackMessages.SUCCESS_SEARCH_MESSAGE);
            CommandExecutor.setGuiFeedBack(FeedbackMessages.SWITCH_TO_TEMP);
            isDone = true;
        }
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }
}
