package logic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import storage.ModelTask;

/**
 *
 * @author Jireh
 */
public class Undo extends CommandFactory {

    private boolean isDone;

    protected Undo() {
        execute(null);
        updateTaskList();
    }

    @Override
    protected void execute(String input) {
        if (!undoStack.getStack().isEmpty()) {
            ObservableList<ModelTask> popped = undoStack.pop();

            if (!undoStack.getStack().isEmpty()) {
                redoStack.push(popped);

                popped = undoStack.peek();
                ObservableList<ModelTask> temp = FXCollections.observableArrayList();
                copyList(popped, temp);
                list.setList(temp);
                isDone = true;

                CommandExecutor.setFeedBack(ErrorMessages.SUCCESS_UNDONE_MESSAGE);
            } else {
                ObservableList<ModelTask> temp = FXCollections.observableArrayList();
                copyList(popped, temp);
                undoStack.push(temp);

                CommandExecutor.setFeedBack(ErrorMessages.ERROR_UNDONE_MESSAGE);
            }
        } else {
            CommandExecutor.setFeedBack("Error, empty stack");
        }
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }
}
