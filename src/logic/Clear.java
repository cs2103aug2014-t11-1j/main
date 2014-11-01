package logic;

import java.util.Iterator;
import java.util.logging.Level;

import com.ModelTask;
import com.util.MyLogger;

/**
 * Logic for Clear command.
 * 
 * Clears the list depending on the input command.
 *
 *
 * @author Jireh
 */
public class Clear extends CommandFactory {

    private boolean isDone;

    //Constructor
    protected Clear(String input) {
        execute(input);
        updateUndoAndRedoStacks();
        updateTaskList();
    }

    @Override
    protected void execute(String input) {
        determineClear(input);
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }

    private void determineClear(String input) {
        if (input.equalsIgnoreCase("")) {
            clearAll();
        } else if (input.equalsIgnoreCase("DONE")) {
            clearDone();
        } else if (input.equalsIgnoreCase("URGENT")) {
            clearUrgent();
        } else if (input.equalsIgnoreCase("NORMAL")) {
            clearNormal();
        } else {
            setFeedbackError();
        }
        setGuiFeedback();
    }

    private void clearAll() {
        list.clear();
        isDone = true;
        setFeedbackSuccessClear();
    }

    private void clearDone() {
        Iterator<ModelTask> iterator = obtainListIterator();

        while (iterator.hasNext()) {
            if (iterator.next().isDone()) {
                iterator.remove();
            }
        }
        setPositionOfTasks();
        isDone = true;
        setFeedbackSuccessClearDone();
    }

    private void clearUrgent() {
        Iterator<ModelTask> iterator = obtainListIterator();

        while (iterator.hasNext()) {
            if (iterator.next().isUrgent()) {
                iterator.remove();
            }
        }
        setPositionOfTasks();
        isDone = true;
        setFeedbackSuccessClearUrgent();
    }

    private void clearNormal() {
        Iterator<ModelTask> iterator = obtainListIterator();

        while (iterator.hasNext()) {
            if (!iterator.next().isUrgent()) {
                iterator.remove();
            }
        }
        setPositionOfTasks();
        isDone = true;
        setFeedbackSuccessClearNormal();
    }

    private void setGuiFeedback() {
        CommandExecutor.setGuiFeedBack(FeedbackMessages.NORMAL_STATE);
    }

    private void setFeedbackError() {
        CommandExecutor.setUserFeedBack(FeedbackMessages.ERROR_CLEAR_MESSAGE);
        MyLogger.log(Level.INFO, FeedbackMessages.ERROR_CLEAR_MESSAGE);
    }

    private void setFeedbackSuccessClear() {
        CommandExecutor.setUserFeedBack(FeedbackMessages.SUCCESS_CLEAR_MESSAGE);
    }

    /**
     * Sets Position of tasks in proper order after clearing.
     */
    private void setPositionOfTasks() {
        for (int i = 0; i < list.getListSize(); i++) {
            list.get(i).setPosition(i + 1);
        }
    }

    private Iterator<ModelTask> obtainListIterator() {
        Iterator<ModelTask> iterator = list.iterator();
        return iterator;
    }

    private void setFeedbackSuccessClearDone() {
        CommandExecutor.setUserFeedBack(FeedbackMessages.SUCCESS_CLEARDONE_MESSAGE);
    }

    private void setFeedbackSuccessClearUrgent() {
        CommandExecutor.setUserFeedBack(FeedbackMessages.SUCCESS_CLEARURGENT_MESSAGE);
    }

    private void setFeedbackSuccessClearNormal() {
        CommandExecutor.setUserFeedBack(FeedbackMessages.SUCCESS_CLEARNORMAL_MESSAGE);
    }
}
