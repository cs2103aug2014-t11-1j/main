package logic;

import javafx.collections.ObservableList;
import storage.ModelTask;

/**
 *
 * @author Jireh
 */
public class Undo extends CommandFactory {

    private boolean isDone;

    @Override
    protected void execute(String input) {
        if (!undoStack.getStack().isEmpty()) {
            ObservableList<ModelTask> popped = undoStack.pop();

            if (!undoStack.getStack().isEmpty()) {
                redoStack.push(popped);

                popped = undoStack.peek();
                ObservableList<ModelTask> temp = null;
                copyList(popped, temp);
                list.setList(temp);

                System.out.println("Action undone");
            } else {
                ObservableList<ModelTask> temp = null;
                copyList(popped, temp);
                undoStack.push(temp);

                System.out.println("Action cannot be undone; original state");
            }
        } else {
            System.out.println("Error, empty stack");
        }
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }
}
