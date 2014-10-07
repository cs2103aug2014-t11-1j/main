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
		taskList.add(new ModelTask("task", new Date(), null, new Date(), null, 1, true));
		return taskList;
	}
	
	public void getFeedback(String input){
		System.out.println("logic received input: " + input);
	}

	public String getFeedBack(String input) {
		// TODO Auto-generated method stub
		return "facade lol";
	}

	public ObservableList<ModelTask> getSearchedList() {
		searchedList.add(new ModelTask("task", new Date(), null, new Date(), null, 1, true));
		return searchedList;
	}
}
