//@author A0111370Y
package logic;

import static logic.CommandFactory.updateUndoAndRedoStacks;

import com.ModelTask;
import com.util.MyLogger;
import java.util.logging.Level;

/**
 * Logic for MarkUrgent command.
 *
 * Marks the task at input index as urgent.
 */
public class MarkUrgent extends CommandFactory {

    private static final int INVALID_INDEX = -1;

    private boolean isDone_;

    protected MarkUrgent(String input) {
        execute(input);
        updateUndoAndRedoStacks();
        updateTaskList();
    }

    @Override
    protected void execute(String input) {
        int index;
        index = getIndex(input);

        if (isValidIndex(index)) {
            markTaskAsUrgent(index);
        } else {
            setFeedbackError();
            logError();
            setGuiFeedbackNormal();
        }
    }

    @Override
    protected boolean isDone() {
        return isDone_;
    }

    private int getIndex(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            return INVALID_INDEX;
        }

    }

    private void markTaskAsUrgent(int index) {
        ModelTask task = null;
        int i = 0;

        for (; i < list.getListSize(); i++) {
            ModelTask temp = list.get(i);
            if (temp.getPosition() == index) {
                task = temp;
                break;
            }
        }

        task.setIsUrgent(true);
        list.remove(i);
        list.add(task, i);
        isDone_ = true;
        setFeedbackSuccess();
        setGuiFeedbackNormal();
    }

    private void setGuiFeedbackNormal() {
        CommandExecutor.setGuiFeedBack(FeedbackMessages.NORMAL_STATE);
    }

    private void setFeedbackSuccess() {
        CommandExecutor.setUserFeedBack(FeedbackMessages.SUCCESS_MARKURGENT_MESSAGE);
    }

    private void setFeedbackError() {
        CommandExecutor.setUserFeedBack(FeedbackMessages.ERROR_MARKURGENT_MESSAGE);
    }

    private void logError() {
        MyLogger.log(Level.INFO, FeedbackMessages.ERROR_MARKURGENT_MESSAGE);
    }

    private boolean isValidIndex(int index) {
        return index <= list.getListSize() && index > 0;
    }
}
