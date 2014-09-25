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
        isDone = true;
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
