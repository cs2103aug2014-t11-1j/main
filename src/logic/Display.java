package logic;

import static logic.CommandFactory.list;

import com.ModelTask;

/**
 *
 * @author Jireh
 */
public class Display extends CommandFactory {

    private boolean isDone;

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
        return isDone;
    }

    private boolean determineDisplay(String input) {
        if (input.equalsIgnoreCase("DONE")) {
            displayDone();          
        } else if (input.equalsIgnoreCase("URGENT")) {
            displayUrgent();
        } else {
            CommandExecutor.setUserFeedBack(FeedbackMessages.ERROR_DISPLAY_MESSAGE);
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
            isDone = true;
        }
    }
}
