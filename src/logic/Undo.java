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
            ObservableList<ModelTask> popped = getListFromUndoStack();

            if (!undoStack.getStack().isEmpty()) {
                addCurrentListToRedoStack(popped);
                undo();
                setFeedbackSuccess();
                setGuiFeedbackUpdateAllList();
            } else {
                ObservableList<ModelTask> temp = FXCollections.observableArrayList();
                copyList(popped, temp);
                undoStack.push(temp);

                setFeedbackError();
                setGuiFeedbackNormal();
            }
        } else {
            setFeedbackEmptyStack();
        }
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }

    private void undo() {
        ObservableList<ModelTask> popped;
        popped = undoStack.peek();
        ObservableList<ModelTask> temp = FXCollections.observableArrayList();
        copyList(popped, temp);
        list.setList(temp);
        isDone = true;
    }

    private ObservableList<ModelTask> getListFromUndoStack() {
        ObservableList<ModelTask> popped = undoStack.pop();
        return popped;
    }

    private void addCurrentListToRedoStack(ObservableList<ModelTask> popped) {
        redoStack.push(popped);
    }

    private void setFeedbackError() {
        CommandExecutor.setUserFeedBack(FeedbackMessages.ERROR_UNDONE_MESSAGE);
    }

    private void setFeedbackSuccess() {
        CommandExecutor.setUserFeedBack(FeedbackMessages.SUCCESS_UNDONE_MESSAGE);
    }

    private void setGuiFeedbackUpdateAllList() {
        CommandExecutor.setGuiFeedBack(FeedbackMessages.UPDATE_ALL_LIST);
    }

    private void setGuiFeedbackNormal() {
        CommandExecutor.setGuiFeedBack(FeedbackMessages.NORMAL_STATE);
    }

    private void setFeedbackEmptyStack() {
        CommandExecutor.setUserFeedBack("Error, empty stack");
    }

}
