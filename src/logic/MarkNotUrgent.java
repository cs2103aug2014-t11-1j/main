//@author A0111370Y
package logic;

import static logic.CommandFactory.updateUndoAndRedoStacks;

import com.ModelTask;
import com.util.MyLogger;
import java.util.Collections;
import java.util.logging.Level;

/**
 * Logic for MarkNotUrgent command.
 *
 * Marks the task at input index as not urgent.
 */
public class MarkNotUrgent extends CommandFactory {

    private static final int INVALID_INDEX = -1;

    private boolean isDone;

    protected MarkNotUrgent(String input) {
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
        return isDone;
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

        task.setIsUrgent(false);
        list.remove(i);
        list.add(task, i);
        isDone = true;
        setFeedbackSuccess();
        setGuiFeedbackNormal();
    }

    private int getIndex(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            return INVALID_INDEX;
        }
    }

    private void setFeedbackSuccess() {
        CommandExecutor.setUserFeedBack(FeedbackMessages.SUCCESS_MARKNOTURGENT_MESSAGE);
    }

    private void logError() {
        MyLogger.log(Level.INFO, FeedbackMessages.ERROR_MARKNOTURGENT_MESSAGE);
    }

    private void setFeedbackError() {
        CommandExecutor.setUserFeedBack(FeedbackMessages.ERROR_MARKNOTURGENT_MESSAGE);
    }

    private void setGuiFeedbackNormal() {
        CommandExecutor.setGuiFeedBack(FeedbackMessages.NORMAL_STATE);
    }

    private boolean isValidIndex(int index) {
        return index <= list.getListSize() && index > 0;
    }
}
