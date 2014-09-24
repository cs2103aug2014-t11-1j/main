package storage;

import java.util.Iterator;
import javafx.collections.ObservableList;

public class TaskList {

    /**
     * Class Attributes
     */
    private ObservableList<ModelTask> list;

    /**
     * Constructor
     */
    public TaskList() {

    }

    /**
     * Accessors
     */
    public ObservableList<ModelTask> getList() {
        return list;
    }

    public int getListSize() {
        return list.size();
    }

    public ModelTask get(int index) {
        return list.get(index);
    }

    public Iterator<ModelTask> getIterator() {
        return list.iterator();
    }

    /**
     * Mutators
     */
    public void add(ModelTask task) {
        list.add(task);
    }

    public void add(ModelTask task, int index){
        list.add(index, task);
    }

    public void remove(int index) {
        list.remove(index);
    }

    public void setList(ObservableList<ModelTask> list) {
        this.list = list;
    }

    public void clear() {
        list.clear();
    }
}
