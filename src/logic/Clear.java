package logic;

/**
 *
 * @author Jireh
 */
public class Clear extends CommandFactory {

    boolean isDone;

    //Constructor
    protected Clear() {
        execute(null);
        updateUndoAndRedoStacks();
    }

    @Override
    protected void execute(String input) {
        list.clear();
        isDone = true;
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
