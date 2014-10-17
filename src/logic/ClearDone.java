package logic;

import java.util.Iterator;
import java.util.logging.Level;

import com.MyLogger;

import storage.ModelTask;

/**
 *
 * @author Jireh
 */
public class ClearDone extends CommandFactory {

    private boolean isDone;

    public ClearDone() {
        execute(null);
        updateUndoAndRedoStacks();
        updateTaskList();
    }

    @Override
    protected void execute(String input) {
        for(ModelTask task: list){
            if (task.isDone()) {
                list.remove(task.getPosition()-1);
            }
        }
        for (int i = 0; i < list.getListSize(); i++) {
            list.get(i).setPosition(i + 1);
        }
        isDone = true;
        CommandExecutor.setFeedBack(ErrorMessages.SUCCESS_CLEARDONE_MESSAGE);
        MyLogger.log(Level.INFO,ErrorMessages.SUCCESS_CLEARDONE_MESSAGE);
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }

}
