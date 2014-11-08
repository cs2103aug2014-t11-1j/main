//@author A0111370Y
package logic;

import java.util.logging.Level;

import com.ModelTask;
import com.util.MyLogger;

/**
 * Logic for Add command.
 * Adds tasks to the list of tasks in CommandFactory.
 */
public class Add extends CommandFactory {

    private boolean isDone_;
    private boolean isUrgent_;

    /**
     * Constructor
     */
    protected Add(String input, boolean isUrgent) {
        isAddingUrgent(isUrgent);
        execute(input);
        updateUndoAndRedoStacks();
        updateTaskList();
    }

    @Override
    protected void execute(String input) {
        try {
            ModelTask temp = addTask(input);
            setFeedbackSuccess(temp);
        } catch (IllegalArgumentException ex) {
            setFeedbackError();
            logError();
            return;
        }
    }

    @Override
    protected boolean isDone() {
        return isDone_;
    }

    private static String formatString(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Invalid!");
        } else {
            return input.trim();
        }
    }

    private void isAddingUrgent(boolean isUrgent) {
        this.isUrgent_ = isUrgent;
    }

    private ModelTask addTask(String input) {
        input = formatString(input);
        ModelTask temp = getTaskFromParser(input);
        temp.setIsUrgent(isUrgent_);
        list.add(temp);
        isDone_ = true;
        return temp;
    }

    private ModelTask getTaskFromParser(String input) {
        return tc.convert(pf.getTask("add " + input), list.getListSize() + 1);
    }

    private void setFeedbackSuccess(ModelTask temp) {
        CommandExecutor.setUserFeedBack(String.format("\"%s\"%s", temp.getEvent(), FeedbackMessages.SUCCESS_ADDING_MESSAGE));
        CommandExecutor.setGuiFeedBack(FeedbackMessages.NORMAL_STATE);
    }

    private void setFeedbackError() {
        CommandExecutor.setUserFeedBack(FeedbackMessages.ERROR_ADDING_MESSAGE);
    }

    private void logError() {
        MyLogger.log(Level.WARNING, FeedbackMessages.ERROR_ADDING_MESSAGE);
    }
}
