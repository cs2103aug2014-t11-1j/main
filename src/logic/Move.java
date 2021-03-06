//@author A0111370Y
package logic;

import com.ModelTask;

/**
 * Takes in String input containing 2 integers in the format "%d %d", where the
 * 1st integer is the task to be moved, and the 2nd integer is the position to
 * move to.
 */
public class Move extends CommandFactory {

    private static final int INDEX_TASK = 0;
    private static final int INDEX_POSITION = 1;

    private boolean isDone_;

    protected Move(String input) {
        execute(input);
        updateUndoAndRedoStacks();
        updateTaskList();
    }

    @Override
    protected void execute(String input) {
        try {
            String[] splitStrings = formatString(input);
            int index = getIndex(splitStrings, INDEX_TASK);
            int position = getIndex(splitStrings, INDEX_POSITION);

            if (isValidLineNumber(index) && isValidLineNumber(position) && index != position) {
                moveTask(index, position);
            } else {
                setFeedbackInvalidIndex();
                setGuiFeedbackNormal();
            }
        } catch (IllegalArgumentException ex) {
            setFeedbackInvalidIndex();
            setGuiFeedbackNormal();
        }
    }

    @Override
    protected boolean isDone() {
        return isDone_;
    }

    private void moveTask(int index, int position) {
        ModelTask temp = list.get(index);

        list.remove(index);
        list.add(temp, position);
        setPositionOfTasks();

        isDone_ = true;
        setFeedbackSuccess(index, position);
        setGuiFeedbackNormal();
    }

    private void setFeedbackSuccess(int index, int position) {
        CommandExecutor.setUserFeedBack(String.format("Task %d has been moved to no. %d.", index + 1, position + 1));
    }

    private void setFeedbackInvalidIndex() {
        CommandExecutor.setUserFeedBack("Please enter 2 valid numbers");
    }

    private void setGuiFeedbackNormal() {
        CommandExecutor.setGuiFeedBack(FeedbackMessages.NORMAL_STATE);
    }

    private void setPositionOfTasks() {
        for (int i = 0; i < list.getListSize(); i++) {
            list.get(i).setPosition(i + 1);
        }
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
