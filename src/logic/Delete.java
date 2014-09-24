package logic;

/**
 *
 * @author Jireh
 */
public class Delete extends CommandFactory {

    private boolean isDone;

    /**
     * Constructor
     */
    protected Delete(String input) {
        execute(input);
        updateUndoAndRedoStacks();
        isDone = true;
    }

    @Override
    protected void execute(String input) {
        try {
            list.remove(getIndex(input));
        } catch (Exception ex) {
            printMessage(ex.getMessage());
            return;
        }
        isDone = true;
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }

    private static int getIndex(String input) {
        int index;
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Invalid index!");
        } else {
            index = Integer.parseInt(input.trim().split("\\s+")[0]);
        }
        if (!isValidLineNumber(index)) {
            throw new IllegalArgumentException("Invalid index!");
        } else {
            return index;
        }
    }
}
