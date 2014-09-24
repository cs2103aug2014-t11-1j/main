package logic;

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
        isDone = true;
    }

    @Override
    protected void execute(String input) {
        list.add(pf.getTask(input));
        updateUndoAndRedoStacks();
    }

    @Override
    protected String formatString(String input) {
        return null;
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }
}
