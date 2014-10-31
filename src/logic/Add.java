package logic;

import java.util.logging.Level;

import com.ModelTask;
import com.util.MyLogger;


/**
 *
 * @author Jireh
 */
public class Add extends CommandFactory {

    private boolean isDone;
    private boolean isUrgent;

    /**
     * Constructor
     */
    protected Add(String input, boolean isUrgent) {
        this.isUrgent = isUrgent;
        execute(input);
        updateUndoAndRedoStacks();
        updateTaskList();
    }

    @Override
    protected void execute(String input) {
        try {
            input = formatString(input);
            ModelTask temp = tc.convert(pf.getTask("add " + input), list.getListSize() + 1);
            temp.setIsUrgent(isUrgent);
            list.add(temp);
            isDone = true;
            CommandExecutor.setUserFeedBack(String.format("\"%s\" %s", temp.getEvent(), FeedbackMessages.SUCCESS_ADDING_MESSAGE));
            CommandExecutor.setGuiFeedBack(FeedbackMessages.NORMAL_STATE);
        } catch (IllegalArgumentException ex) {
            CommandExecutor.setUserFeedBack(FeedbackMessages.ERROR_ADDING_MESSAGE);
            MyLogger.log(Level.WARNING,FeedbackMessages.ERROR_ADDING_MESSAGE);
            return;
        }
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }

    private static String formatString(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Invalid!");
        } else {
            return input.trim();
        }
    }
}
