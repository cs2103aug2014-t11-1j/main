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

//@author A0111370Y
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

        sortTasksByPositionNumber();

        if (isValidLineNumber(index)) {
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
            return Integer.parseInt(input) - 1;
        } catch (NumberFormatException ex) {
            return INVALID_INDEX;
        }
    }

    private void markTaskAsUndone(int index) {
        ModelTask task = list.get(index);
        task.setIsDone(false);
        list.remove(index);
        list.add(task, index);
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

    private void sortTasksByPositionNumber() {
        Collections.sort(CommandFactory.list.getList(), new ModelTaskNumComparator());
    }
}
