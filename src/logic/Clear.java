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
        updateTaskList();
    }

    @Override
    protected void execute(String input) {
        list.clear();
        isDone = true;
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }

}
