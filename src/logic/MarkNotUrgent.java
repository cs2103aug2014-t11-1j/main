package logic;

import static logic.CommandFactory.updateUndoAndRedoStacks;
import storage.ModelTask;

/**
 *
 * @author Jireh
 */
public class MarkNotUrgent extends CommandFactory {

    private boolean isDone;

    protected MarkNotUrgent(String input) {
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
            CommandExecutor.setFeedBack(ErrorMessages.ERROR_MARKNOTURGENT_MESSAGE);
            return;
        }

        if (isValidLineNumber(index)) {
            ModelTask task = list.get(index);
            task.setIsUrgent(false);
            isDone = true;
            CommandExecutor.setFeedBack(ErrorMessages.SUCCESS_MARKNOTURGENT_MESSAGE);
        } else {
            CommandExecutor.setFeedBack(ErrorMessages.ERROR_MARKNOTURGENT_MESSAGE);
        }
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }
}
