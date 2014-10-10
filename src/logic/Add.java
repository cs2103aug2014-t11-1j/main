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
            ModelTask temp = tc.convert(pf.getTask("add " + input), list.getListSize() + 1);
            list.add(temp);
            isDone = true;
            CommandExecutor.setFeedBack(String.format("\"%s\" %s", temp.getEvent(), ErrorMessages.SUCCESS_ADDING_MESSAGE));
        } catch (IllegalArgumentException ex) {
            CommandExecutor.setFeedBack(ErrorMessages.ERROR_ADDING_MESSAGE);
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
