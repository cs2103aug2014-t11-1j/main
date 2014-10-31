package logic;

import com.ModelTask;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

                CommandExecutor.setUserFeedBack(FeedbackMessages.SUCCESS_UNDONE_MESSAGE);
                CommandExecutor.setGuiFeedBack(FeedbackMessages.UPDATE_ALL_LIST);
            } else {
                ObservableList<ModelTask> temp = FXCollections.observableArrayList();
                copyList(popped, temp);
                undoStack.push(temp);

                CommandExecutor.setUserFeedBack(FeedbackMessages.ERROR_UNDONE_MESSAGE);
                CommandExecutor.setGuiFeedBack(FeedbackMessages.NORMAL_STATE);
            }
        } else {
            CommandExecutor.setUserFeedBack("Error, empty stack");
        }
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }
}
