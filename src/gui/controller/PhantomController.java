package gui.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import gui.MainApp;

public class PhantomController{
	@FXML
	private Label tfOutput;
	
	// Reference to the main application.
	private MainApp mainApp;
	
	@FXML
	private Parent tableView;
	@FXML
	private TableController tableViewController;
	
	public PhantomController() {
	}

	@FXML
	private void initialize() {
	}
	
	@FXML
	private TextField commandLine;
	
	@FXML
	private void handleKeyPressed(KeyEvent e){
		if(e.getCode() == KeyCode.ENTER){
			String input = commandLine.getText();
			commandLine.clear();
		}
	}
	
	@FXML
	private void handleExit(){
		System.exit(0);
	}
	
	@FXML
	private void handleMinimise(){
		mainApp.getPrimaryStage().setIconified(true);
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	/*
	@FXML
	private void handleDeleteTask() {
		String numString = commandLine.getText();
		commandLine.clear();

		if(numString == null || numString.length() == 0){
			tfOutput.setText("Invalid deletion");
		}
		else{
			int lineNum = Integer.parseInt(numString);
			taskList.remove(lineNum - 1);
			for(int i = lineNum-1; i<taskList.size(); i++){
				taskList.get(i).setPosition(i+1);
			}
		}
	}

	@FXML
	private void handleAddTask() {
		String taskString = commandLine.getText();
		commandLine.clear();
		ModelTask task = new ModelTask(taskString, new Date(), taskList.size() + 1);
		taskList.add(task);
	}

	@FXML
	private void handleClear(){
		taskList.clear();
	}
	
	@FXML
	private void handleEditTask(){
		String taskString = commandLine.getText();
		commandLine.clear();
		taskList.get(0).setEvent(taskString);
	}
	
	@FXML
	private void handleSearchTask(){
		String searchTerm = commandLine.getText();
		commandLine.clear();
		searchedList.add(new ModelTask(searchTerm, new Date(), 1));
		taskTable.setItems(searchedList);
	}
	*/
	

}
