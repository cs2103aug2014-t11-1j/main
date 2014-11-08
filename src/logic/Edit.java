package logic;

import static logic.CommandFactory.tc;

import java.util.logging.Level;

import com.ModelTask;
import com.Task;
import com.util.MyLogger;

/**
 * Logic for Edit command.
 * 
 * Edits task at input index.
 */

//@author A0111370Y
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
            editTask(input);
            setFeedbackSuccess();
            setGuiFeedbackNormal();
        } catch (Exception ex) {
            setFeedbackError();
            logError();
            setGuiFeedbackNormal();
            return;
        }

    }

    @Override
    protected boolean isDone() {
        return isDone;
    }

    private static String[] formatString(String input) {
        if (input == null || input.isEmpty()) {
            setFeedbackInvalidIndex();
            logInvalidIndex();
            throw new IllegalArgumentException("Invalid index!");
        } else {
            return input.trim().split("\\s+", 2);
        }
    }

    private static int getIndex(String[] splitStrings) {
        int index = Integer.parseInt(splitStrings[0]) - 1;

        if (!isValidLineNumber(index)) {
            setFeedbackInvalidIndex();
            logInvalidIndex();
            throw new IllegalArgumentException("Invalid index!");
        } else {
            return index;
        }
    }

    private static void logInvalidIndex() {
        MyLogger.log(Level.WARNING, "Invalid index!");
    }

    private static void setFeedbackInvalidIndex() {
        CommandExecutor.setUserFeedBack("Invalid index!");
    }

    private static Task getNewTaskFromParser(String[] splitStrings) {
        return pf.getTask(splitStrings[1]);
    }

    private void logError() {
        MyLogger.log(Level.WARNING, FeedbackMessages.ERROR_EDIT_MESSAGE);
    }

    private void setFeedbackError() {
        CommandExecutor.setUserFeedBack(FeedbackMessages.ERROR_EDIT_MESSAGE);
    }

    private void setFeedbackSuccess() {
        CommandExecutor.setUserFeedBack(FeedbackMessages.SUCCESS_EDIT_MESSAGE);
    }

    private void setGuiFeedbackNormal() {
        CommandExecutor.setGuiFeedBack(FeedbackMessages.NORMAL_STATE);
    }

    private void editTask(String input) {
        String[] splitStrings;
        int index;
        splitStrings = formatString(input);
        index = getIndex(splitStrings);
        Task newTask = getNewTaskFromParser(splitStrings);
        ModelTask temp = convertTaskToModelTask(input, index);
        list.remove(index);
        list.add(temp, index);
        isDone = true;
    }

    private ModelTask convertTaskToModelTask(String input, int index) {
        ModelTask temp = tc.convert(pf.getTask(input), index + 1);
        return temp;
    }
}
