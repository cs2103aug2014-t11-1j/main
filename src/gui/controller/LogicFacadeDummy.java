package gui.controller;
/**
 * This is a dummy class used for isolated testing 
 * of the GUI.
 * 
 * @author A0116018R
 */
import java.util.Date;

import com.ModelTask;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LogicFacadeDummy {

	private ObservableList<ModelTask> taskList_;
	private ObservableList<ModelTask> searchedList_;
	
	public LogicFacadeDummy(){
		taskList_ = FXCollections.observableArrayList();
		searchedList_ = FXCollections.observableArrayList();
	}
	
	public ObservableList<ModelTask> getAllList(){
		taskList_.add(new ModelTask("task", new Date(), null, new Date(), null, 1, true, true));
		return taskList_;
	}
	
	public void getFeedback(String input){
		System.out.println("logic received input: " + input);
	}

	public String getFeedBack(String input) {
		return "USER FEEDBACK";
	}

	public ObservableList<ModelTask> getSearchedList() {
		searchedList_.add(new ModelTask("task", new Date(), null, new Date(), null, 1, true, true));
		return searchedList_;
	}
}
