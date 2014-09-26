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
        ModelTask temp = tc.convert(pf.getTask("add " + input), list.getListSize() + 1);
        list.add(temp);
        isDone = true;
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }
}
