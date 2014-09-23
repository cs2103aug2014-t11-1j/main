package gui.controller;

import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import storage.ModelTask;

public class LogicFacadeDummy {

	private ObservableList<ModelTask> taskList;
	private ObservableList<ModelTask> searchedList;
	
	public LogicFacadeDummy(){
		taskList = FXCollections.observableArrayList();
		searchedList = FXCollections.observableArrayList();
	}
	
	public ObservableList<ModelTask> getAllList(){
		taskList.add(new ModelTask("task", new Date(), 1));
		return taskList;
	}
	
	public void getFeedback(String input){
		System.out.println("logic got input: " + input);
	}
}
