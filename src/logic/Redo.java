/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import storage.ModelTask;

/**
 *
 * @author Jireh
 */
public class Redo extends CommandFactory {

    private boolean isDone;

    protected Redo() {
        execute(null);
        updateTaskList();
    }

    @Override
    protected void execute(String input) {
        if (redoStack.getStack().isEmpty()) {
            //System.out.println("No action to redo");
            CommandExecutor.setFeedBack("No action to redo");
        } else {
            list.setList(redoStack.pop());
            ObservableList<ModelTask> temp = FXCollections.observableArrayList();
            copyList(list.getList(), temp);
            undoStack.push(temp);
            isDone = true;
        }
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }

}
