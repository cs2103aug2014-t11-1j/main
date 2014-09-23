package storage;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Jireh
 */
public class UndoRedoStack {

    private Stack<ArrayList<Task>> stack;

    public UndoRedoStack() {
        stack = new Stack<ArrayList<Task>>();
    }

    /**
     * Accessors
     */
    public Stack<ArrayList<Task>> getStack() {
        return stack;
    }

    /**
     * Mutators
     */
    public void setStack(Stack<ArrayList<Task>> stack) {
        this.stack = stack;
    }

    public void push(ArrayList<Task> list) {
        stack.push(list);
    }

    public ArrayList<Task> pop() {
        return stack.pop();
    }
    
    public ArrayList<Task> peek(){
        return stack.peek();
    }

    public void clear() {
        stack.clear();
    }
}
