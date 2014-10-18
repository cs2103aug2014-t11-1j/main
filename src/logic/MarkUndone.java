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
            CommandExecutor.setUserFeedBack(FeedbackMessages.ERROR_MARKUNDONE_MESSAGE);
            CommandExecutor.setGuiFeedBack(FeedbackMessages.NORMAL_STATE);
            return;
        }

        if (isValidLineNumber(index)) {
            ModelTask task = list.get(index);
            task.setIsDone(false);
            isDone = true;
            CommandExecutor.setUserFeedBack(FeedbackMessages.SUCCESS_MARKUNDONE_MESSAGE);
            CommandExecutor.setGuiFeedBack(FeedbackMessages.NORMAL_STATE);
        } else {
            CommandExecutor.setUserFeedBack(FeedbackMessages.ERROR_MARKUNDONE_MESSAGE);
            CommandExecutor.setGuiFeedBack(FeedbackMessages.NORMAL_STATE);
        }
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }

}
