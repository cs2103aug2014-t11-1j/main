package logic;

import java.util.Iterator;
import javafx.collections.ObservableList;
import parser.ParserFacade;
import storage.ModelTask;
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
    protected static TaskList searchList = new TaskList();

    /**
     * Abstract methods
     */
    protected abstract void execute(String input);

    protected abstract boolean isDone();

    /**
     * Methods
     */
    protected static boolean isValidLineNumber(int num) {
        return num < list.getListSize() && num >= 0;
    }

    protected static void printContentsOfList(int lineNum) {
        ModelTask temp;
        for (int i = 0; i < list.getListSize(); i++) {
            temp = list.get(i);
            printMessage(lineNum + ". " + temp.toString() + " " + temp.isDone());
            lineNum++;
        }
    }

    protected static void printMessage(String message) {
        System.out.println(message);
    }

    protected static void updateUndoAndRedoStacks() {
        ObservableList<ModelTask> temp = null;
        copyList(list.getList(), temp);
        undoStack.push(temp);
        redoStack.clear();
    }

    protected static void copyList(ObservableList<ModelTask> oldList, ObservableList<ModelTask> newList) {

        if (!newList.isEmpty()) {
            newList.clear();
        }
        Iterator<ModelTask> itr = oldList.iterator();

        while (itr.hasNext()) {
            newList.add(itr.next());
        }
    }

    public ObservableList<ModelTask> getTaskList() {
        return list.getList();
    }

    public ObservableList<ModelTask> getSearchList() {
        return searchList.getList();
    }
}
