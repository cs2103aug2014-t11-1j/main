//@author A0111370Y
package com;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TaskList implements Iterable<ModelTask> {

    /**
     * Class Attributes
     */
    private ObservableList<ModelTask> list_;

    /**
     * Constructor
     */
    public TaskList() {
        list_ = FXCollections.observableArrayList();
    }

    /**
     * Accessors
     */
    public ObservableList<ModelTask> getList() {
        return list_;
    }

    public int getListSize() {
        return list_.size();
    }

    public ModelTask get(int index) {
        return list_.get(index);
    }

    public boolean isEmpty() {
        return list_.isEmpty();
    }

    @Override
    public Iterator<ModelTask> iterator() {
        return list_.iterator();
    }

    /**
     * Mutators
     */
    public void add(ModelTask task) {
        list_.add(task);
    }

    public void add(ModelTask task, int index) {
        list_.add(index, task);
    }

    public void remove(int index) {
        list_.remove(index);
    }

    public void setList(ObservableList<ModelTask> list) {
        this.list_ = list;
    }

    public void clear() {
        list_.clear();
    }

}
