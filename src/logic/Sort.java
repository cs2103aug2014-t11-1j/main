package logic;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Jireh
 */
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
            CommandExecutor.setUserFeedBack(FeedbackMessages.SUCCESS_SORTED_MESSAGE);
            CommandExecutor.setGuiFeedBack(FeedbackMessages.NORMAL_STATE);
        } catch (IllegalArgumentException ex) {
            CommandExecutor.setUserFeedBack(FeedbackMessages.ERROR_SORTED_MESSAGE);
            CommandExecutor.setGuiFeedBack(FeedbackMessages.NORMAL_STATE);
            return;
        }
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }

    private static SortCommands getSortCommands(String input) {
        ArrayList<String> tempStrings = new ArrayList<String>();
        for (String tempString : input.split(" ")) {
            tempStrings.add(tempString.toLowerCase());
        }

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

    private static void performSort(SortCommands cmd) {
        switch (cmd) {
            case ALPHA:
                Collections.sort(CommandFactory.list.getList(), new ModelTaskAlphaComparator());
                break;
            case DATE:
                //implement date sort
                //there is no deadline in ModelTask
                Collections.sort(CommandFactory.list.getList(), new ModelTaskDateComparator());
                break;
            case NUM:
                Collections.sort(CommandFactory.list.getList(), new ModelTaskNumComparator());
                break;
            default:
                throw new IllegalArgumentException("Invalid");
        }
    }

}
