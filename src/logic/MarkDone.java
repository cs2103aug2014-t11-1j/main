package logic;

import storage.ModelTask;

/**
 *
 * @author Jireh
 */
public class MarkDone extends CommandFactory {

    private boolean isDone;

    protected MarkDone(String input) {
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
            CommandExecutor.setFeedBack(ErrorMessages.ERROR_MARKDONE_MESSAGE);
            return;
        }

        if (isValidLineNumber(index)) {
            ModelTask task = list.get(index);
            task.setIsDone(true);
            isDone = true;
            CommandExecutor.setFeedBack(ErrorMessages.SUCCESS_MARKDONE_MESSAGE);
        } else {
            CommandExecutor.setFeedBack(ErrorMessages.ERROR_MARKDONE_MESSAGE);
        }
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }
}
