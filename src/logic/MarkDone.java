//@author A0111370Y
package logic;

import com.ModelTask;
import com.util.MyLogger;
import java.util.logging.Level;

/**
 * Logic for MarkDone command.
 *
 * Marks the task at input index as done.
 */
public class MarkDone extends CommandFactory {

    private static final int INVALID_INDEX = -1;

    private boolean isDone_;

    protected MarkDone(String input) {
        execute(input);
        updateUndoAndRedoStacks();
        updateTaskList();
    }

    @Override
    protected void execute(String input) {
        int index;
        index = getIndex(input);

        if (isValidIndex(index)) {
            markTaskAsDone(index);
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

    private void setFeedbackSuccess() {
        CommandExecutor.setUserFeedBack(FeedbackMessages.SUCCESS_MARKDONE_MESSAGE);
    }

    private void setGuiFeedbackNormal() {
        CommandExecutor.setGuiFeedBack(FeedbackMessages.NORMAL_STATE);
    }

    private void logError() {
        MyLogger.log(Level.INFO, FeedbackMessages.ERROR_MARKDONE_MESSAGE);
    }

    private void setFeedbackError() {
        CommandExecutor.setUserFeedBack(FeedbackMessages.ERROR_MARKDONE_MESSAGE);
    }

    private void markTaskAsDone(int index) {
        ModelTask task = null;
        int i = 0;

        for (; i < list.getListSize(); i++) {
            ModelTask temp = list.get(i);
            if (temp.getPosition() == index) {
                task = temp;
                break;
            }
        }

        task.setIsDone(true);
        list.remove(i);
        list.add(task, i);
        isDone_ = true;
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

    private boolean isValidIndex(int index) {
        return index <= list.getListSize() && index > 0;
    }

}
