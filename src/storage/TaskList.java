package storage;

import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TaskList {

    /**
     * Class AttributesO
     */
    private ObservableList<ModelTask> list;

    /**
     * Constructor
     */
    public TaskList() {

        list = FXCollections.observableArrayList();
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

    public void add(ModelTask task, int index) {
        list.add(index, task);
    }

    public void remove(int index) {
        list.remove(index);
    }

    public void clear() {
        list.clear();
    }

    public void setList(ObservableList<ModelTask> list) {
        this.list = list;
    }

    public void set(int index, ModelTask task) {
        list.set(index, task);
    }
}
