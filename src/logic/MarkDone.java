package logic;

import storage.ModelTask;

/**
 *
 * @author Jireh
 */
public class MarkDone extends CommandFactory {

    private boolean isDone;

    protected MarkDone(String input) {
        execute(input);
        updateUndoAndRedoStacks();
        isDone = true;
    }

    @Override
    protected void execute(String input) {
        int index = Integer.parseInt(input) - 1;

        if (isValidLineNumber(index)) {
            ModelTask task = list.get(index);
            task.setIsDone(true);
        } else {
            throw new IllegalArgumentException("Invalid index!");
        }
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }
}
