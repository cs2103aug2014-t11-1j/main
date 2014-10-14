package logic;

import java.util.logging.Level;

import com.MyLogger;

/**
 *
 * @author Jireh
 */
public class Clear extends CommandFactory {

    private boolean isDone;

    //Constructor
    protected Clear() {
        execute(null);
        updateUndoAndRedoStacks();
        updateTaskList();
    }

    @Override
    protected void execute(String input) {
        list.clear();
        isDone = true;
        CommandExecutor.setFeedBack(ErrorMessages.SUCCESS_CLEAR_MESSAGE);
        MyLogger.log(Level.INFO,ErrorMessages.SUCCESS_CLEAR_MESSAGE);
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }

}
