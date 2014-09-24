package logic;

import static logic.CommandFactory.tc;
import storage.ModelTask;
import storage.Task;

/**
 *
 * @author Jireh
 */
public class Edit extends CommandFactory {

    private boolean isDone;

    //Constructor
    protected Edit(String input) {
        execute(input);
        updateUndoAndRedoStacks();
        isDone = true;
    }

    @Override
    protected void execute(String input) {
        String[] splitStrings = formatString(input);
        int index = getIndex(splitStrings);
        Task newTask = getNewTask(splitStrings);
        ModelTask temp = tc.convert(pf.getTask(input), list.getListSize() + 1);
        list.remove(index);
        list.add(temp, index);

        updateUndoAndRedoStacks();
        isDone = true;
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }

    private static String[] formatString(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Invalid index!");
        } else {
            return input.trim().split("\\s+", 2);
        }
    }

    private static int getIndex(String[] splitStrings) {
        int index = Integer.parseInt(splitStrings[0]) - 1;

        if (!isValidLineNumber(index)) {
            throw new IllegalArgumentException("Invalid index!");
        } else {
            return index;
        }
    }

    private static Task getNewTask(String[] splitStrings) {
        return pf.getTask(splitStrings[1]);
    }

}
