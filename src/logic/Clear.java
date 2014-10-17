package logic;

import java.util.logging.Level;

import com.MyLogger;
import java.util.Iterator;
import static logic.CommandFactory.list;
import storage.ModelTask;

/**
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
            CommandExecutor.setFeedBack(ErrorMessages.ERROR_CLEAR_MESSAGE);
        }
    }

    private void clearAll() {
        list.clear();
        isDone = true;
        CommandExecutor.setFeedBack(ErrorMessages.SUCCESS_CLEAR_MESSAGE);
        MyLogger.log(Level.INFO, ErrorMessages.SUCCESS_CLEAR_MESSAGE);
    }

    private void clearDone() {
        Iterator<ModelTask> iterator = list.iterator();

        while (iterator.hasNext()) {
            if (iterator.next().isDone()) {
                iterator.remove();
            }
        }
        for (int i = 0; i < list.getListSize(); i++) {
            list.get(i).setPosition(i + 1);
        }
        isDone = true;
        CommandExecutor.setFeedBack(ErrorMessages.SUCCESS_CLEARDONE_MESSAGE);
        MyLogger.log(Level.INFO, ErrorMessages.SUCCESS_CLEARDONE_MESSAGE);
    }

    private void clearUrgent() {
        Iterator<ModelTask> iterator = list.iterator();

        while (iterator.hasNext()) {
            if (iterator.next().isUrgent()) {
                iterator.remove();
            }
        }
        for (int i = 0; i < list.getListSize(); i++) {
            list.get(i).setPosition(i + 1);
        }
        isDone = true;
        CommandExecutor.setFeedBack(ErrorMessages.SUCCESS_CLEARURGENT_MESSAGE);
    }

    private void clearNormal() {
        Iterator<ModelTask> iterator = list.iterator();

        while (iterator.hasNext()) {
            if (!iterator.next().isUrgent()) {
                iterator.remove();
            }
        }
        for (int i = 0; i < list.getListSize(); i++) {
            list.get(i).setPosition(i + 1);
        }
        isDone = true;
        CommandExecutor.setFeedBack(ErrorMessages.SUCCESS_CLEARURGENT_MESSAGE);
    }
}
