package gui.controller;

import storage.ModelTask;
import gui.MainApp;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;

public class PhantomController{
	@FXML
	private Label tfOutput;
	
	// Reference to the main application.
	private MainApp mainApp;
	

	
	@FXML
	private Parent tableView;
	@FXML
	private TableController tableViewController;
	
	@FXML
	private Parent commandLineView;
	@FXML
	private CommandLineController commandLineViewController;
	
	private LogicFacadeDummy logicFacade;
	
	public PhantomController() {
		System.out.println("phantom constructor");
		//logicFacade = new LogicFacadeDummy();
	}

	@FXML
	private void initialize() {
		System.out.println("phantom initilising");
		tableViewController.setAllView(logicFacade.getAllList());
		//commandLineViewController.setLogicFacade(logicFacade);
	}
	
	public void switchToSearch(ObservableList<ModelTask> list){
		tableViewController.setVisible(true);
		tableViewController.switchToSearch(list);
	}
	
	public void switchToAll(){
		tableViewController.setVisible(true);
		tableViewController.switchToAll();
	}
	
	public void setAllView(ObservableList<ModelTask> list){
		tableViewController.setVisible(true);
		tableViewController.setAllView(list);
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
		switchToAll();
	}
	
	@FXML
	private void handleSearchTask(){
		tableViewController.setVisible(false);
	}
	*/
	

}
