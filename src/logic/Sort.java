package logic;

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
        } catch (IllegalArgumentException ex) {
            return;
        }

        isDone = true;
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }

    private static SortCommands getSortCommands(String input) {
        if (input.equalsIgnoreCase("alpha")) {
            return SortCommands.ALPHA;
        } else if (input.equalsIgnoreCase("date")) {
            return SortCommands.DATE;
        } else if (input.equalsIgnoreCase("num") || input.equals("")) {
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
