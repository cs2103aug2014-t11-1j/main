/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

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
    }

    @Override
    protected void execute(String input) {
        if (redoStack.getStack().isEmpty()) {
            System.out.println("No action to redo");
        } else {
            list.setList(redoStack.pop());
            ObservableList<ModelTask> temp = null;
            copyList(list.getList(), temp);
            undoStack.push(temp);
        }
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }

}
