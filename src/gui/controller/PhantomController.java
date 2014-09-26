package gui.controller;

import java.util.Calendar;

import gui.MainApp;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import logic.LogicFacade;
import storage.ModelTask;
import logic.ErrorMessages;

public class PhantomController{
	protected static boolean hasOccured = false;
	
	@FXML
	private Label tfOutput;
	@FXML
	private TextField commandLine;
	
	// Reference to the main application.
	private MainApp mainApp;
	
	@FXML
	private Parent tableView;
	@FXML
	private TableController tableViewController;
	
	@FXML
	private Parent todayView;
	@FXML
	private TodayViewController todayViewController;
	
	@FXML
	private Label timeLabel;
	
	private LogicFacade logicFacade;
	
	private AnimationHandler ah;
	
	public PhantomController() {
		System.out.println("phantom constructor");
		logicFacade = LogicFacade.getInstance();
	}

	@FXML
	private void initialize() {
		System.out.println("phantom initilising");
		tableViewController.setAllView(logicFacade.getAllList());
		todayViewController.setTodayView(logicFacade.getAllList());
		tableView.setVisible(true);
		todayView.setVisible(true);		
		
		PhantomClock pc = PhantomClock.getInstance();
		pc.setClock(timeLabel);
		
		ah = AnimationHandler.getInstance();
		ah.initialize(tableView, todayView);		
	}
	
	@FXML
	private void handleKeyPressed(KeyEvent e){
	
		EditListener editListener = new EditListener(logicFacade.getAllList(),commandLine);
		commandLine.textProperty().addListener(editListener);
		
		if(e.getCode() == KeyCode.ENTER){
			hasOccured = false;
			String input = commandLine.getText();
			
			if(input.equalsIgnoreCase("showall")){
				ah.animateLeft();
			}
			if(input.equalsIgnoreCase("showtoday")){
				ah.animateRight();
			}
			commandLine.clear();
			String feedback = "";
			try {
				feedback = logicFacade.getFeedBack(input);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			if(feedback == ErrorMessages.SUCCESS_UNDONE_MESSAGE || feedback == ErrorMessages.ERROR_UNDONE_MESSAGE || feedback == ErrorMessages.SUCCESS_REDONE_MESSAGE){
				setAllView(logicFacade.getAllList());
			}
			
			tfOutput.setText(feedback);
		}
		
		
	}
	
	public void switchToSearch(ObservableList<ModelTask> list){
		tableViewController.switchToSearch(list);
		tableView.setVisible(true);
		todayView.setVisible(false);
	}
	
	public void switchToAll(){
		tableViewController.switchToAll();
		tableView.setVisible(true);
		todayView.setVisible(false);
	}
	
	public void setAllView(ObservableList<ModelTask> list){
		tableViewController.setAllView(list);
		tableView.setVisible(true);
		todayView.setVisible(false);
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
