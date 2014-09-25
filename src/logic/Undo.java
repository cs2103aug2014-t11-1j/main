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
        isDone = true;
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

                //System.out.println("Action undone");
                CommandExecutor.setFeedBack("Action undone");
            } else {
                ObservableList<ModelTask> temp = FXCollections.observableArrayList();
                copyList(popped, temp);
                undoStack.push(temp);

                //System.out.println("Action cannot be undone; original state");
                CommandExecutor.setFeedBack("Action cannot be undone; original state");
            }
        } else {
            //System.out.println("Error, empty stack");
            CommandExecutor.setFeedBack("Error, empty stack");
        }
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }
}
