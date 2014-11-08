//@author A0111370Y
package logic;

import static logic.CommandFactory.isValidLineNumber;

import com.ModelTask;
import com.util.MyLogger;
import java.util.Collections;
import java.util.logging.Level;

/**
 * Logic for MarkUndone command.
 *
 * Marks the task at input index as not done.
 */
public class MarkUndone extends CommandFactory {

    private static final int INVALID_INDEX = -1;

    private boolean isDone;

    protected MarkUndone(String input) {
        execute(input);
        updateUndoAndRedoStacks();
        updateTaskList();
    }

    @Override
    protected void execute(String input) {
        int index;
        index = getIndex(input);

        if (isValidIndex(index)) {
            markTaskAsUndone(index);
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

    private int getIndex(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            return INVALID_INDEX;
        }
    }

    private void markTaskAsUndone(int index) {
        ModelTask task = null;
        int i = 0;

        for (; i < list.getListSize(); i++) {
            ModelTask temp = list.get(i);
            if (temp.getPosition() == index) {
                task = temp;
                break;
            }
        }

        task.setIsDone(false);
        list.remove(i);
        list.add(task, i);
        isDone = true;
        setFeedbackSuccess();
        setGuiFeedbackNormal();
    }

    private void setFeedbackSuccess() {
        CommandExecutor.setUserFeedBack(FeedbackMessages.SUCCESS_MARKUNDONE_MESSAGE);
    }

    private void setGuiFeedbackNormal() {
        CommandExecutor.setGuiFeedBack(FeedbackMessages.NORMAL_STATE);
    }

    private void logError() {
        MyLogger.log(Level.INFO, FeedbackMessages.ERROR_MARKUNDONE_MESSAGE);
    }

    private void setFeedbackError() {
        CommandExecutor.setUserFeedBack(FeedbackMessages.ERROR_MARKUNDONE_MESSAGE);
    }

    private boolean isValidIndex(int index) {
        return index <= list.getListSize() && index > 0;
    }
}
