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
        execute(input);
        isDone = true;
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
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }
}
