package logic;

import static logic.CommandFactory.isValidLineNumber;
import storage.ModelTask;

/**
 *
 * @author Jireh
 */
public class MarkUndone extends CommandFactory {

    private boolean isDone;

    protected MarkUndone(String input) {
        execute(input);
        updateUndoAndRedoStacks();
        updateTaskList();
    }

    @Override
    protected void execute(String input) {
        int index;
        try {
            index = Integer.parseInt(input) - 1;
        } catch (NumberFormatException ex) {
            CommandExecutor.setFeedBack(ErrorMessages.ERROR_MARKUNDONE_MESSAGE);
            return;
        }

        if (isValidLineNumber(index)) {
            ModelTask task = list.get(index);
            task.setIsDone(false);
            isDone = true;
            CommandExecutor.setFeedBack(ErrorMessages.SUCCESS_MARKUNDONE_MESSAGE);
        } else {
            CommandExecutor.setFeedBack(ErrorMessages.ERROR_MARKUNDONE_MESSAGE);
        }
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }

}
