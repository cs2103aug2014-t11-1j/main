//@author A0111370Y
package logic;

import com.ModelTask;
import com.util.MyLogger;
import java.util.logging.Level;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Logic for Redo Command.
 */
public class Redo extends CommandFactory {

    private boolean isDone_;

    protected Redo() {
        execute(null);
        updateTaskList();
    }

    @Override
    protected void execute(String input) {
        if (redoStack.getStack().isEmpty()) {
            setFeedbackError();
            logError();
            setGuiFeedbackNormal();
        } else {
            redo();
            setFeedbackSuccess();
            setGuiFeedbackUpdateAllList();
        }
    }

    @Override
    protected boolean isDone() {
        return isDone_;
    }

    private void redo() {
        setListInRedoStackAsCurrentList();
        copyCurrentListIntoUndoStack();
        isDone_ = true;
    }

    private void setListInRedoStackAsCurrentList() {
        list.setList(redoStack.pop());
    }

    private void copyCurrentListIntoUndoStack() {
        ObservableList<ModelTask> temp = FXCollections.observableArrayList();
        copyList(list.getList(), temp);
        undoStack.push(temp);
    }

    private void setFeedbackSuccess() {
        CommandExecutor.setUserFeedBack(FeedbackMessages.SUCCESS_REDONE_MESSAGE);
    }

    private void setGuiFeedbackUpdateAllList() {
        CommandExecutor.setGuiFeedBack(FeedbackMessages.UPDATE_ALL_LIST);
    }

    private void setGuiFeedbackNormal() {
        CommandExecutor.setGuiFeedBack(FeedbackMessages.NORMAL_STATE);
    }

    private void setFeedbackError() {
        CommandExecutor.setUserFeedBack(FeedbackMessages.ERROR_REDONE_MESSAGE);
    }

    private void logError() {
        MyLogger.log(Level.INFO, FeedbackMessages.ERROR_REDONE_MESSAGE);
    }
}
