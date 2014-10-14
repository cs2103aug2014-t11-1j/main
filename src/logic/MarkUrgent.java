package logic;

import static logic.CommandFactory.updateUndoAndRedoStacks;
import storage.ModelTask;

/**
 *
 * @author Jireh
 */
public class MarkUrgent extends CommandFactory {

    private boolean isDone;

    protected MarkUrgent(String input) {
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
            CommandExecutor.setFeedBack(ErrorMessages.ERROR_MARKURGENT_MESSAGE);
            return;
        }

        if (isValidLineNumber(index)) {
            ModelTask task = list.get(index);
            task.setIsUrgent(true);
            isDone = true;
            CommandExecutor.setFeedBack(ErrorMessages.SUCCESS_MARKURGENT_MESSAGE);
        } else {
            CommandExecutor.setFeedBack(ErrorMessages.ERROR_MARKURGENT_MESSAGE);
        }
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }
}
