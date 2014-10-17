package logic;

import java.util.Iterator;
import storage.ModelTask;

/**
 *
 * @author Jireh
 */
public class Search extends CommandFactory {

    // private static ObservableList<ModelTask> searchList;
    private boolean isDone;

    protected Search(String input) {
        tempList.clear();
        execute(input);
        updateTempList();
    }

    @Override
    protected void execute(String input) {
        Iterator<ModelTask> iterator = list.getIterator();

        while (iterator.hasNext()) {
            ModelTask currentTask = iterator.next();
            if (currentTask.getEvent().contains(input)) {
                tempList.add(currentTask);
            }
        }
        if (tempList.isEmpty()) {
            CommandExecutor.setFeedBack(ErrorMessages.ERROR_SEARCH_MESSAGE);
        } else {
            CommandExecutor.setFeedBack(ErrorMessages.SUCCESS_SEARCH_MESSAGE);
            isDone = true;
        }
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }
}
