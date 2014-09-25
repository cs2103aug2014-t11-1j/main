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
        isDone = true;
    }

    @Override
    protected void execute(String input) {
        ModelTask temp = tc.convert(pf.getTask("add " + input), list.getListSize() + 1);
        list.add(temp);
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }
}
