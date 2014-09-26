package gui.controller;

import gui.MainApp;

import java.util.Calendar;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import logic.ErrorMessages;
import logic.LogicFacade;
import storage.ModelTask;

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


	private LogicFacade logicFacade;

	private ObservableList<ModelTask> allList;
	private ObservableList<ModelTask> todayList;


	
	@FXML
	private Label timeLabel;

	
	private AnimationHandler ah;
	

	public PhantomController() {
		System.out.println("phantom constructor");
		logicFacade = LogicFacade.getInstance();
	}

	@FXML
	private void initialize() {
		System.out.println("phantom initilising");
		allList = logicFacade.getAllList();
		tableViewController.setAllView(allList);
		getTodayList(allList);
		todayViewController.setTodayView(todayList);
		tableView.setVisible(false);
		todayView.setVisible(true);
	}


	//TODO: should be under initilise todayView
	private void getTodayList(ObservableList<ModelTask> list) {
		todayList = FXCollections.observableArrayList();

		Calendar yesterdayCal = Calendar.getInstance();
		yesterdayCal.set(Calendar.DAY_OF_YEAR, yesterdayCal.get(Calendar.DAY_OF_YEAR) - 1);
		yesterdayCal.set(Calendar.HOUR_OF_DAY, 23);
		yesterdayCal.set(Calendar.MINUTE, 59);
		yesterdayCal.set(Calendar.SECOND, 59);

		Calendar tomorrowCal = Calendar.getInstance();
		tomorrowCal.set(Calendar.HOUR_OF_DAY, 23);
		tomorrowCal.set(Calendar.MINUTE, 59);
		tomorrowCal.set(Calendar.SECOND, 59);

		Date yesterday = yesterdayCal.getTime();
		Date tomorrow = tomorrowCal.getTime();
		
		System.out.println(tomorrow.toString());
		for(ModelTask task : list){
			if(task.getStartDate() != null){
				System.out.println(task.getStartDate().toString());
				System.out.println(task.getStartDate().compareTo(tomorrow));
				if(task.getStartDate().compareTo(yesterday) > 0 && task.getStartDate().compareTo(tomorrow) < 0){
					todayList.add(task);
				}
			}
		}

		todayList.sort(new ModelTaskStartDateComparator());
		if(todayList.size() > 5){
			for(int i = 5; i < todayList.size(); i++){
				todayList.remove(5);
			}
		}
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
				switchToAll();
			}
			if(input.equalsIgnoreCase("showtoday")){
				ah.animateRight();
				switchToToday();
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

	public void switchToToday(){
		todayView.setVisible(true);
		tableView.setVisible(false);
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
