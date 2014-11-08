//@author A0111370Y
package logic;

import static logic.CommandFactory.list;

import com.ModelTask;

/**
 * Logic for Display command.
 *
 * Searches and displays tasks of input type.
 */
public class Display extends CommandFactory {

    private boolean isDone_;

    public Display(String input) {
        tempList.clear();
        execute(input);
        updateTempList();
    }

    @Override
    protected void execute(String input) {
        if (!determineDisplay(input)) {
            return;
        }
        setFeedback();
    }

    @Override
    protected boolean isDone() {
        return isDone_;
    }

    private boolean determineDisplay(String input) {
        if (input.equalsIgnoreCase("DONE")) {
            displayDone();
        } else if (input.equalsIgnoreCase("URGENT")) {
            displayUrgent();
        } else {
            setFeedbackError();
            return false;
        }
        return true;
    }

    private void displayUrgent() {
        for (ModelTask task : list) {
            if (task.isUrgent()) {
                tempList.add(task);
            }
        }
    }

    private void displayDone() {
        for (ModelTask task : list) {
            if (task.isDone()) {
                tempList.add(task);
            }
        }
    }

    private void setFeedback() {
        if (tempList.isEmpty()) {
            CommandExecutor.setUserFeedBack(FeedbackMessages.ERROR_SEARCH_MESSAGE);
            CommandExecutor.setGuiFeedBack(FeedbackMessages.NORMAL_STATE);
        } else {
            CommandExecutor.setUserFeedBack(FeedbackMessages.SUCCESS_SEARCH_MESSAGE);
            CommandExecutor.setGuiFeedBack(FeedbackMessages.SWITCH_TO_TEMP);
            isDone_ = true;
        }
    }

    private void setFeedbackError() {
        CommandExecutor.setUserFeedBack(FeedbackMessages.ERROR_DISPLAY_MESSAGE);
    }
}
