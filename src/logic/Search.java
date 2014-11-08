//@author A0111370Y
package logic;

import com.ModelTask;

/**
 * Logic for Search command.
 *
 * Searches for tasks containing input string and adds them to a list of search
 * results.
 */
public class Search extends CommandFactory {

    private boolean isDone_;

    protected Search(String input) {
        tempList.clear();
        execute(input);
        updateTempList();
    }

    @Override
    protected void execute(String input) {
        searchListForHits(input);

        if (tempList.isEmpty()) {
            setFeedbackError();
            setGuiFeedbackNormal();
        } else {
            setFeedbackSuccess();
            setGuiFeedbackSwitchToTemp();
            isDone_ = true;
        }
    }

    @Override
    protected boolean isDone() {
        return isDone_;
    }

    private void searchListForHits(String input) {
        for (ModelTask task : list) {
            if (task.getEvent().contains(input)) {
                addFoundTasksToTempList(task);
            }
        }
    }

    private void addFoundTasksToTempList(ModelTask task) {
        tempList.add(task);
    }

    private void setFeedbackError() {
        CommandExecutor.setUserFeedBack(FeedbackMessages.ERROR_SEARCH_MESSAGE);
    }

    private void setGuiFeedbackNormal() {
        CommandExecutor.setGuiFeedBack(FeedbackMessages.NORMAL_STATE);
    }

    private void setFeedbackSuccess() {
        CommandExecutor.setUserFeedBack(FeedbackMessages.SUCCESS_SEARCH_MESSAGE);
    }

    private void setGuiFeedbackSwitchToTemp() {
        CommandExecutor.setGuiFeedBack(FeedbackMessages.SWITCH_TO_TEMP);
    }

}
