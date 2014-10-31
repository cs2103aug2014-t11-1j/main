package logic;

import static logic.CommandFactory.updateUndoAndRedoStacks;

import com.ModelTask;

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
            CommandExecutor.setUserFeedBack(FeedbackMessages.ERROR_MARKURGENT_MESSAGE);
            CommandExecutor.setGuiFeedBack(FeedbackMessages.NORMAL_STATE);
            return;
        }

        if (isValidLineNumber(index)) {
            ModelTask task = list.get(index);
            task.setIsUrgent(true);
            isDone = true;
            CommandExecutor.setUserFeedBack(FeedbackMessages.SUCCESS_MARKURGENT_MESSAGE);
            CommandExecutor.setGuiFeedBack(FeedbackMessages.NORMAL_STATE);
        } else {
            CommandExecutor.setUserFeedBack(FeedbackMessages.ERROR_MARKURGENT_MESSAGE);
            CommandExecutor.setGuiFeedBack(FeedbackMessages.NORMAL_STATE);
        }
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }
}
