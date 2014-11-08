//@author A0111370Y
package com;

import java.util.Stack;
import javafx.collections.ObservableList;

public class UndoRedoStack {

    private Stack<ObservableList<ModelTask>> stack_;

    public UndoRedoStack() {
        stack_ = new Stack<ObservableList<ModelTask>>();
    }

    /**
     * Accessors
     */
    public Stack<ObservableList<ModelTask>> getStack() {
        return stack_;
    }

    public int getSize() {
        return stack_.size();
    }

    /**
     * Mutators
     */
    public void setStack(Stack<ObservableList<ModelTask>> stack) {
        this.stack_ = stack;
    }

    public void push(ObservableList<ModelTask> list) {
        stack_.push(list);
    }

    public ObservableList<ModelTask> pop() {
        return stack_.pop();
    }

    public ObservableList<ModelTask> peek() {
        return stack_.peek();
    }

    public void clear() {
        stack_.clear();
    }
}
