package logic;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import parser.ParserFacade;
import storage.ModelTask;
import storage.TaskConverter;
import storage.TaskList;
import storage.TentativeTask;
import storage.TimePeriod;
import storage.UndoRedoStack;

/**
 *
 * @author Jireh
 */
public abstract class CommandFactory {

    protected static ParserFacade pf = ParserFacade.getInstance();
    protected static TaskConverter tc = TaskConverter.getInstance();
    protected static TaskList list = new TaskList();
    protected static TaskList tempList = new TaskList();
    protected static UndoRedoStack undoStack = new UndoRedoStack();
    protected static UndoRedoStack redoStack = new UndoRedoStack();
    protected static ArrayList<TentativeTask> tentativeTasks = new ArrayList<TentativeTask>();
    protected static ArrayList<TimePeriod> globalBlockedTimePeriods = new ArrayList<TimePeriod>();
    protected static ObservableList<String> tentativeTasksObservableList = FXCollections.observableArrayList();

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

    protected static void printContentsOfList(ObservableList<ModelTask> list) {
        ModelTask temp;
        for (int i = 0; i < list.size(); i++) {
            temp = list.get(i);
            printMessage(temp.getEvent());
        }
    }

    protected static void printMessage(String message) {
        System.out.println(message);
    }

    protected static void updateUndoAndRedoStacks() {
        ObservableList<ModelTask> temp = FXCollections.observableArrayList();
        copyList(list.getList(), temp);
        undoStack.push(temp);
        redoStack.clear();
    }

    protected static void copyList(ObservableList<ModelTask> oldList, ObservableList<ModelTask> newList) {

        if (!newList.isEmpty()) {
            newList.clear();
        }
        for(ModelTask task : oldList){
            ModelTask temp = task.copyTask();
            newList.add(temp);
        }
    }

    protected static void updateTaskList() {
        CommandExecutor.setTaskList(list.getList());
    }

    protected static void updateTempList() {
        CommandExecutor.setTempList(tempList.getList());
    }

    protected static void updateGlobalBlockedTimePeriods() {
        globalBlockedTimePeriods = TentativeTask.getGlobalBlockedTimePeriods();
    }
}
