package logic;

import java.util.Iterator;
import static logic.CommandFactory.updateUndoAndRedoStacks;
import storage.ModelTask;

/**
 *
 * @author Jireh
 */
public class ClearUrgent extends CommandFactory {

    private boolean isDone;

    public ClearUrgent() {
        execute(null);
        updateUndoAndRedoStacks();
        updateTaskList();
    }

    @Override
    protected void execute(String input) {
        Iterator<ModelTask> iterator = list.getIterator();

        while (iterator.hasNext()) {
            if (iterator.next().isUrgent()) {
                iterator.remove();
            }
        }
        for (int i = 0; i < list.getListSize(); i++) {
            list.get(i).setPosition(i + 1);
        }
        isDone = true;
        CommandExecutor.setFeedBack(ErrorMessages.SUCCESS_CLEARURGENT_MESSAGE);
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }

}
