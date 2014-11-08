package logic;

import static logic.CommandFactory.updateUndoAndRedoStacks;

import com.ModelTask;
import com.util.MyLogger;
import java.util.Collections;
import java.util.logging.Level;

/**
 * Logic for MarkUrgent command.
 *
 * Marks the task at input index as urgent.
 */

//@author A0111370Y
public class MarkUrgent extends CommandFactory {

    private static final int INVALID_INDEX = -1;

    private boolean isDone;

    protected MarkUrgent(String input) {
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

    private int getIndex(String input) {
        try {
            return Integer.parseInt(input) - 1;
        } catch (NumberFormatException ex) {
            return INVALID_INDEX;
        }

    }

    private void markTaskAsUrgent(int index) {
        ModelTask task = list.get(index);
        task.setIsUrgent(true);
        list.remove(index);
        list.add(task, index);
        isDone = true;
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

    private void sortTasksByPositionNumber() {
        Collections.sort(CommandFactory.list.getList(), new ModelTaskNumComparator());
    }

}
