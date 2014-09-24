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
        updateUndoAndRedoStacks();
        isDone = true;
    }

    @Override
    protected void execute(String input) {
        list.add(pf.getTask(input));
        updateUndoAndRedoStacks();
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }
}
