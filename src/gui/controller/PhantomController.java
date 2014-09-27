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
	private Label timeLabel;
	@FXML
	private Label tfOutput;
	@FXML
	private TextField commandLine;

	//other views and controllers
	@FXML
	private Parent tableView;
	@FXML
	private TableController tableViewController;

	@FXML
	private Parent todayView;
	@FXML
	private TodayViewController todayViewController;

	private LogicFacade logicFacade;
	private MainApp mainApp;

	private AnimationHandler ah;
	private CommandLineUtility clu;

	private ObservableList<ModelTask> allList;
	private ObservableList<ModelTask> todayList;

	public PhantomController() {
		System.out.println("phantom constructor");
		logicFacade = LogicFacade.getInstance();
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	private void initialize() {
		System.out.println("phantom initilising");
		init();

	}

	private void init() {
		initTableView();
		initTodayView();
		initClock();
		initAnimation();
		initCommandLineUtility();
	}

	private void initTableView() {
		allList = logicFacade.getAllList();
		tableViewController.setAllView(allList);
		tableView.setVisible(false);
	}

	private void initTodayView() {
		getTodayList(allList);
		todayViewController.setTodayView(todayList);
		todayView.setVisible(true);
	}

	private void initClock() {
		PhantomClock pc = PhantomClock.getInstance();
		pc.setClock(timeLabel);
	}

	private void initAnimation() {
		ah = AnimationHandler.getInstance();
		ah.initialize(tableView, todayView);
	}

	private void initCommandLineUtility() {
		clu = CommandLineUtility.getInstance();
		clu.initialize(commandLine);
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
		System.out.println(yesterday.toString());
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
	}

	@FXML
	private void handleKeyPressed(KeyEvent e){

		EditListener editListener = new EditListener(logicFacade.getAllList(),commandLine);
		commandLine.textProperty().addListener(editListener);

		if(e.getCode() == KeyCode.ENTER){
			hasOccured = false;
			String input = commandLine.getText();
			commandLine.clear();
			String feedback = "";

			if(input.equalsIgnoreCase("showall")){
				ah.animateLeft();
				switchToAll();
			}
			else if(input.equalsIgnoreCase("showtoday")){
				ah.animateRight();
				switchToToday();
			}
			else{
				try {
					feedback = logicFacade.getFeedBack(input);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				if(feedback == ErrorMessages.SUCCESS_UNDONE_MESSAGE || feedback == ErrorMessages.SUCCESS_REDONE_MESSAGE){
					setAllView(logicFacade.getAllList());
				}
				
				tfOutput.setText(feedback);

				getTodayList(allList);
				todayViewController.setTodayView(todayList);

				clu.forwardToPrevious();
				clu.pushInput(input);
			}
		}

		if(e.getCode() == KeyCode.UP){
			clu.displayPreviousInput();			
		}

		if(e.getCode() == KeyCode.DOWN){
			clu.displayForwardInput();
		}
	}

	private void switchToSearch(ObservableList<ModelTask> list){
		tableViewController.switchToSearch(list);
		tableView.setVisible(true);
		todayView.setVisible(false);
	}

	private void switchToAll(){
		tableViewController.switchToAll();
		tableView.setVisible(true);
		todayView.setVisible(false);
	}

	private void setAllView(ObservableList<ModelTask> list){
		tableViewController.setAllView(list);
		tableView.setVisible(true);
		todayView.setVisible(false);
	}

	private void switchToToday(){
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
