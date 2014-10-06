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
        searchList.clear();
        execute(input);
        updateSearchList();
    }

    @Override
    protected void execute(String input) {
        Iterator<ModelTask> iterator = list.getIterator();

        while (iterator.hasNext()) {
            ModelTask currentTask = iterator.next();
            if (currentTask.getEvent().contains(input)) {
                searchList.add(currentTask);
            }
        }
        if (searchList.isEmpty()) {
            CommandExecutor.setFeedBack("Search term not found.");
        } else {
            CommandExecutor.setFeedBack("Search term found.");
            isDone = true;
        }
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }
}
