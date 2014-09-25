package logic;

import storage.ModelTask;

/**
 * Takes in String input containing 2 integers in the format "%d %d", where the
 * 1st integer is the task to be moved, and the 2nd integer is the position to
 * move to.
 *
 * @author Jireh
 */
public class Move extends CommandFactory {

    private static final int INDEX_TASK = 0;
    private static final int INDEX_POSITION = 1;

    private boolean isDone;

    protected Move(String input) {
        execute(input);
        updateUndoAndRedoStacks();
        isDone = true;
    }

    @Override
    protected void execute(String input) {
        String[] splitStrings = formatString(input);
        int index = getIndex(splitStrings, INDEX_TASK);
        int position = getIndex(splitStrings, INDEX_POSITION);

        if (isValidLineNumber(index) && isValidLineNumber(position) && index != position) {
            ModelTask temp = list.get(index);

            list.remove(index);
            list.add(temp, position);

            printMessage(String.format("Task %d has been moved to %d.", index + 1, position + 1));
        } else {
            printMessage("Please enter 2 valid numbers.");
        }
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }

    private static String[] formatString(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Invalid index!");
        } else {
            return input.trim().split("\\s+", 3);
        }
    }

    private static int getIndex(String[] splitStrings, int argument) {
        int index = Integer.parseInt(splitStrings[argument]) - 1;

        if (!isValidLineNumber(index)) {
            throw new IllegalArgumentException("Invalid index!");
        } else {
            return index;
        }
    }
}