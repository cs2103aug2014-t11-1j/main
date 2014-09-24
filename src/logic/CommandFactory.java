package logic;

import java.util.ArrayList;
import java.util.Iterator;
import parser.ParserFacade;
import storage.Task;
import storage.TaskList;
import storage.UndoRedoStack;

/**
 *
 * @author Jireh
 */
public abstract class CommandFactory {

    protected static ParserFacade pf = ParserFacade.getInstance();
    protected static TaskList list = new TaskList();
    protected static UndoRedoStack undoStack = new UndoRedoStack();
    protected static UndoRedoStack redoStack = new UndoRedoStack();

    /**
     * Abstract methods
     */
    protected abstract void execute(String input);

    protected abstract String formatString(String input);

    /**
     * Methods
     */
    private boolean isValidLineNumber(int num) {
        return num < list.getListSize() && num >= 0;
    }

    private void printContentsOfList(int lineNum) {
        Task temp;
        for (int i = 0; i < list.getListSize(); i++) {
            temp = list.get(i);
            printMessage(lineNum + ". " + temp.toString() + " " + temp.isDone());
            lineNum++;
        }
    }

    private void printMessage(String message) {
        System.out.println(message);
    }

    protected void updateUndoAndRedoStacks() {
        ArrayList<Task> temp = new ArrayList<Task>();
        copyArrayList(list.getList(), temp);
        undoStack.push(temp);
        redoStack.clear();
    }

    protected void copyArrayList(ArrayList<Task> oldArrayList, ArrayList<Task> newArrayList) {

        if (!newArrayList.isEmpty()) {
            newArrayList.clear();
        }
        Iterator<Task> itr = oldArrayList.iterator();

        while (itr.hasNext()) {
            newArrayList.add(itr.next());
        }
    }
}
