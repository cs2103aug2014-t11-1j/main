package logic;

import storage.ModelTask;

/**
 *
 * @author Jireh
 */
public class Add extends CommandFactory {

    private boolean isDone;

    /**
     * Constructor
     */
    protected Add(String input) {
        execute(input);
        updateUndoAndRedoStacks();
        updateTaskList();
    }

    @Override
    protected void execute(String input) {

        try {
            input = formatString(input);
        } catch (IllegalArgumentException ex) {
            return;
        }

        ModelTask temp = tc.convert(pf.getTask("add " + input), list.getListSize() + 1);
        list.add(temp);
        isDone = true;
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }

    private static String formatString(String input) {
        if (input == null || input.isEmpty()) {
            CommandExecutor.setFeedBack("Invalid!");
            throw new IllegalArgumentException("Invalid!");
        } else {
            return input.trim();
        }
    }
}
