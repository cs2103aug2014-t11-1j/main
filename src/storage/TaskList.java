package storage;

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

    /**
     * Mutators
     */
    public void add(ModelTask task) {
        list.add(task);
    }

    public void remove(int index) {
        list.remove(index);
    }

    public void setList(ObservableList<ModelTask> list) {
        this.list = list;
    }
}
