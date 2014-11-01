package logic;

import com.ModelTask;
import com.util.MyLogger;
import java.util.logging.Level;

/**
 * Logic for MarkDone command.
 *
 * Marks the task at input index as done.
 *
 * @author Jireh
 */
public class MarkDone extends CommandFactory {

    private static final int INVALID_INDEX = -1;

    private boolean isDone;

    protected MarkDone(String input) {
        execute(input);
        updateUndoAndRedoStacks();
        updateTaskList();
    }

    @Override
    protected void execute(String input) {
        int index;
        index = getIndex(input);

        if (isValidLineNumber(index)) {
            markTaskAsDone(index);
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
        ModelTask task = list.get(index);
        task.setIsDone(true);
        isDone = true;
        setFeedbackSuccess();
        setGuiFeedbackNormal();
    }

    private int getIndex(String input) {
        try {
            return Integer.parseInt(input) - 1;
        } catch (NumberFormatException ex) {
            return INVALID_INDEX;
        }
    }
}
