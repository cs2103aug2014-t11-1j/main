package logic;

import java.util.Iterator;
import storage.ModelTask;

/**
 *
 * @author Jireh
 */
public class ClearDone extends CommandFactory {

    private boolean isDone;

    public ClearDone() {
        execute(null);
        updateUndoAndRedoStacks();
        updateTaskList();
    }

    @Override
    protected void execute(String input) {
        Iterator<ModelTask> iterator = list.getIterator();

        while (iterator.hasNext()) {
            if (iterator.next().isDone()) {
                iterator.remove();
            }
        }
        for (int i = 0; i < list.getListSize(); i++) {
            list.get(i).setPosition(i + 1);
        }
        isDone = true;
        CommandExecutor.setFeedBack(ErrorMessages.SUCCESS_CLEARDONE_MESSAGE);
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }

}
