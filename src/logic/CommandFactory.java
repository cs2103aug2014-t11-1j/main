package logic;

import java.util.Iterator;
import javafx.collections.ObservableList;
import parser.ParserFacade;
import storage.ModelTask;
import storage.UndoRedoStack;

/**
 *
 * @author Jireh
 */
public abstract class CommandFactory {

    protected static ParserFacade pf = ParserFacade.getInstance();
    protected static ObservableList<ModelTask> list;
    protected static UndoRedoStack undoStack = new UndoRedoStack();
    protected static UndoRedoStack redoStack = new UndoRedoStack();
    protected ObservableList<ModelTask> searchList;

    /**
     * Abstract methods
     */
    protected abstract void execute(String input);

    protected abstract boolean isDone();

    /**
     * Methods
     */
    protected static boolean isValidLineNumber(int num) {
        return num < list.size() && num >= 0;
    }

    protected static void printContentsOfList(int lineNum) {
        ModelTask temp;
        for (int i = 0; i < list.size(); i++) {
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
        copyList(list, temp);
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

    public static ObservableList<ModelTask> getTaskList() {
        return list;
    }

    public ObservableList<ModelTask> getSearchList() {
        return searchList;
    }
}
