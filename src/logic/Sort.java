package logic;

import com.util.MyLogger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;

/**
 * Logic for Sort command.
 *
 * Sorts tasks according to the type input.
 */

//@author A0111370Y
public class Sort extends CommandFactory {

    private boolean isDone;

    enum SortCommands {

        ALPHA, DATE, NUM
    }

    protected Sort(String input) {
        execute(input);
        updateUndoAndRedoStacks();
        updateTaskList();
    }

    @Override
    protected void execute(String input) {
        SortCommands cmd;
        try {
            cmd = getSortCommands(input);
            performSort(cmd);
            isDone = true;
            setFeedbackSuccess();
            setGuiFeedbackNormal();
        } catch (IllegalArgumentException ex) {
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

    private SortCommands getSortCommands(String input) {
        ArrayList<String> tempStrings = getSortCommandString(input);

        if (tempStrings.contains("alpha")) {
            return SortCommands.ALPHA;
        } else if (tempStrings.contains("date")) {
            return SortCommands.DATE;
        } else if (tempStrings.contains("num") || input.equals("")) {
            return SortCommands.NUM;
        } else {
            throw new IllegalArgumentException("Invalid sort command!");
        }
    }

    private void performSort(SortCommands cmd) {
        switch (cmd) {
            case ALPHA:
                sortTasksAlphabetically();
                break;
            case DATE:
                sortTasksByDate();
                break;
            case NUM:
                sortTasksByPositionNumber();
                break;
            default:
                throw new IllegalArgumentException("Invalid");
        }
    }

    private void sortTasksByPositionNumber() {
        Collections.sort(CommandFactory.list.getList(), new ModelTaskNumComparator());
    }

    private void sortTasksByDate() {
        Collections.sort(CommandFactory.list.getList(), new ModelTaskDateComparator());
    }

    private void sortTasksAlphabetically() {
        Collections.sort(CommandFactory.list.getList(), new ModelTaskAlphaComparator());
    }

    private ArrayList<String> getSortCommandString(String input) {
        ArrayList<String> tempStrings = new ArrayList<String>();
        for (String tempString : input.split(" ")) {
            tempStrings.add(tempString.toLowerCase());
        }
        return tempStrings;
    }

    private void setFeedbackSuccess() {
        CommandExecutor.setUserFeedBack(FeedbackMessages.SUCCESS_SORTED_MESSAGE);
    }

    private void setFeedbackError() {
        CommandExecutor.setUserFeedBack(FeedbackMessages.ERROR_SORTED_MESSAGE);
    }

    private void logError() {
        MyLogger.log(Level.INFO, FeedbackMessages.ERROR_SORTED_MESSAGE);
    }

    private void setGuiFeedbackNormal() {
        CommandExecutor.setGuiFeedBack(FeedbackMessages.NORMAL_STATE);
    }
}
