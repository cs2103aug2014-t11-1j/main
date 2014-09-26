package storage;

import java.util.Stack;
import javafx.collections.ObservableList;

/**
 *
 * @author Jireh
 */
public class UndoRedoStack {

    private Stack<ObservableList<ModelTask>> stack;

    public UndoRedoStack() {
        stack = new Stack<ObservableList<ModelTask>>();
    }

    /**
     * Accessors
     */
    public Stack<ObservableList<ModelTask>> getStack() {
        return stack;
    }
    
    public int getSize(){
        return stack.size();
    }

    /**
     * Mutators
     */
    public void setStack(Stack<ObservableList<ModelTask>> stack) {
        this.stack = stack;
    }

    public void push(ObservableList<ModelTask> list) {
        stack.push(list);
    }

    public ObservableList<ModelTask> pop() {
        return stack.pop();
    }

    public ObservableList<ModelTask> peek() {
        return stack.peek();
    }

    public void clear() {
        stack.clear();
    }
}
