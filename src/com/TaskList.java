//@author A0111370Y
package com;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TaskList implements Iterable<ModelTask> {

    /**
     * Class Attributes
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

    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    @Override
    public Iterator<ModelTask> iterator() {
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

    public void setList(ObservableList<ModelTask> list) {
        this.list = list;
    }

    public void clear() {
        list.clear();
    }


}
