package logic;

import java.util.logging.Level;

import com.MyLogger;

/**
 *
 * @author Jireh
 */
public class Delete extends CommandFactory {

    private boolean isDone;

    /**
     * Constructor
     */
    protected Delete(String input) {
        execute(input);
        updateUndoAndRedoStacks();
        updateTaskList();
    }

    @Override
    protected void execute(String input) {
        try {
            int index = getIndex(input);
            list.remove(index);

            for (int i = index; i < list.getListSize(); i++) {
                list.get(i).setPosition(i + 1);
            }
            isDone = true;
            CommandExecutor.setFeedBack(ErrorMessages.SUCCESS_DELETE_MESSAGE);
            MyLogger.log(Level.INFO,ErrorMessages.SUCCESS_DELETE_MESSAGE);
        } catch (Exception ex) {
            CommandExecutor.setFeedBack(ErrorMessages.ERROR_DELETE_MESSAGE);
            MyLogger.log(Level.WARNING,ErrorMessages.ERROR_DELETE_MESSAGE);
            printMessage(ex.getMessage());
            return;
        }
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }

    private static int getIndex(String input) {
        int index;
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Invalid index!");
        } else {
            index = Integer.parseInt(input.trim().split("\\s+")[0]) - 1;
        }
        if (!isValidLineNumber(index)) {
            throw new IllegalArgumentException("Invalid index!");
        } else {
            return index;
        }
    }
}
