package logic;

import static logic.CommandFactory.tc;

import java.util.logging.Level;

import com.ModelTask;
import com.Task;
import com.util.MyLogger;

/**
 *
 * @author Jireh
 */
public class Edit extends CommandFactory {

    private boolean isDone;

    //Constructor
    protected Edit(String input) {
        execute(input);
        updateUndoAndRedoStacks();
        updateTaskList();
    }

    @Override
    protected void execute(String input) {
        String[] splitStrings;
        int index;
        try {
            splitStrings = formatString(input);
            index = getIndex(splitStrings);
            Task newTask = getNewTask(splitStrings);
            ModelTask temp = tc.convert(pf.getTask(input), index + 1);
            list.remove(index);
            list.add(temp, index);

            isDone = true;
            CommandExecutor.setUserFeedBack(FeedbackMessages.SUCCESS_EDIT_MESSAGE);
            MyLogger.log(Level.INFO,FeedbackMessages.SUCCESS_EDIT_MESSAGE);
            CommandExecutor.setGuiFeedBack(FeedbackMessages.NORMAL_STATE);
        } catch (Exception ex) {
            CommandExecutor.setUserFeedBack(FeedbackMessages.ERROR_EDIT_MESSAGE);
            MyLogger.log(Level.WARNING,FeedbackMessages.ERROR_EDIT_MESSAGE);
            CommandExecutor.setGuiFeedBack(FeedbackMessages.NORMAL_STATE);
            return;
        }

    }

    @Override
    protected boolean isDone() {
        return isDone;
    }

    private static String[] formatString(String input) {
        if (input == null || input.isEmpty()) {
            CommandExecutor.setUserFeedBack("Invalid index!");
            MyLogger.log(Level.WARNING,"Invalid index!");
            throw new IllegalArgumentException("Invalid index!");
        } else {
            return input.trim().split("\\s+", 2);
        }
    }

    private static int getIndex(String[] splitStrings) {
        int index = Integer.parseInt(splitStrings[0]) - 1;

        if (!isValidLineNumber(index)) {
            CommandExecutor.setUserFeedBack("Invalid index!");
            MyLogger.log(Level.WARNING,"Invalid index!");
            throw new IllegalArgumentException("Invalid index!");
        } else {
            return index;
        }
    }

    private static Task getNewTask(String[] splitStrings) {
        return pf.getTask(splitStrings[1]);
    }

}
