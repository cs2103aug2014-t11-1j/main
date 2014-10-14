package logic;

import storage.ModelTask;

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
