package gui.controller;

import gui.MainApp;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.controlsfx.control.Notifications;
import javafx.animation.TranslateTransition;


import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import logic.ErrorMessages;
import logic.LogicFacade;
import storage.ModelTask;

public class PhantomController {
	protected static boolean hasOccured = false;

	private Stage primaryStage;
	private AnchorPane overallView;

	@FXML
	private Label tfOutput;
	@FXML
	private TextField commandLine;

	@FXML
	private Parent tableView;
	@FXML
	private TableController tableViewController;
	@FXML
	private AnchorPane tableAnchor;

	@FXML
	private Parent todayView;
	@FXML
	private TodayViewController todayViewController;
	@FXML
	private AnchorPane todayAnchor;

	@FXML
	private Parent helperView;
	@FXML
	private HelperViewController helperViewController;
	@FXML
	private AnchorPane helperAnchor;

	@FXML
	private Parent timelineView;
	@FXML
	private TimelineViewController timelineViewController;
	@FXML
	private AnchorPane timelineAnchor;

	@FXML
	private Parent tentativeView;
	@FXML
	private TentativeViewController tentativeViewController;
	@FXML
	private AnchorPane tentativeAnchor;

	@FXML
	private Label timeLabel;

	@FXML
	private Menu themeMenu;

	private LogicFacade logicFacade;
	// for testing purposes
	// private LogicFacadeDummy logicFacade;

	private AnimationHandler ah;
	private CommandLineUtility clu;
	private TodayListManager tlm;
	private ThemeMenuHandler tmh;
	private PreferenceManager pm;
	private TimelineViewManager tvm;
	
	private HelperListener helperListener;

	private String themeUrl;

	final KeyCombination keyCombShiftRight = KeyCodeCombination.valueOf("Shift+RIGHT");
	final KeyCombination keyCombShiftLeft = KeyCodeCombination.valueOf("Shift+LEFT");

	private int viewIndex;

	//setting items from MainApp
	public PhantomController() {
		System.out.println("phantom constructor");
		logicFacade = LogicFacade.getInstance();

		// for testing purposes
		// logicFacade = new LogicFacadeDummy();
	}

	public void setPrimaryStage(Stage stage) {
		this.primaryStage = stage;
	}

	public void setOverallView(AnchorPane overallView) {
		this.overallView = overallView;
	}

	//Initialisation
	@FXML
	private void initialize() {
		System.out.println("phantom initilising");
		initAll();
	}

	private void initAll() {
		tableViewController.setAllView(logicFacade.getAllList());
		initMenuBar();
		initTodayList();
		initClock();
		initAnimation();
		initCommandLineUtility();
		initTimeline();
		helperListener = new HelperListener(helperViewController);
		commandLine.textProperty().addListener(helperListener);
	}

	public void initPrefManager() {
		pm = PreferenceManager.getInstance();
		pm.initViews(tableView, todayView, helperView, overallView);
	}

	private void initMenuBar() {
		themeUrl = getClass().getResource("../view/DarkTheme.css")
				.toExternalForm();
		tmh = new ThemeMenuHandler(themeMenu);
		tmh.setDarkTheme();
	}

	private void initTodayList() {
		tlm = new TodayListManager();
		updateTodayView();
	}


	private void initClock() {
		PhantomClock pc = PhantomClock.getInstance();
		pc.setClock(timeLabel);
	}

	private void initAnimation() {
		ah = AnimationHandler.getInstance();
		ah.initialize(tableAnchor, todayAnchor, helperAnchor, timelineAnchor);
	}

	private void initCommandLineUtility() {
		clu = CommandLineUtility.getInstance();
		clu.initialize(commandLine);
	}

	private void initTimeline() {
		tvm = new TimelineViewManager();
		tvm.setTimelineViewController(timelineViewController);
		updateTimelineView();
	}

	//FXML event handling
	@FXML
	private void handleExit() {
		System.exit(0);
	}

	@FXML
	private void handleMinimise() {
		primaryStage.setIconified(true);
	}

	@FXML
	private void handleDarkTheme() {
		changeCss("DarkTheme");
		tmh.setDarkTheme();
	}

	@FXML
	private void handleBlueTheme() {
		changeCss("BlueTheme");
		tmh.setBlueTheme();
	}

	@FXML
	private void handleRedTheme() {
		changeCss("RedTheme");
		tmh.setRedTheme();
	}

	@FXML
	private void handleGreenTheme() {
		changeCss("GreenTheme");
		tmh.setGreenTheme();
	}

	@FXML
	private void handleKeyPressed(KeyEvent e) {

		EditListener editListener = new EditListener(commandLine);
		commandLine.textProperty().addListener(editListener);

		String input;
		if (keyCombShiftRight.match(e)) {
			animateRight();
		}

		if (keyCombShiftLeft.match(e)) {
			animateLeft();
		}

		if (e.getCode() == KeyCode.ENTER) {
			hasOccured = false;

			input = commandLine.getText();
			commandLine.clear();
			String feedback = "";
			if (tableViewController.isSearched()) {
				switchToAll();
			}
			if (ah.getIsFocusTable() && input.equals("")) {
				tableViewController.scrollToNext();
			} else if (input.equalsIgnoreCase("showall")) {
				ah.showTableView();
			} else if (input.equalsIgnoreCase("showtoday")) {
				ah.showTodayView();
			}else if(input.equalsIgnoreCase("showtentative")){
				ah.showTimelineView();
			}else if(input.equalsIgnoreCase("i love big butts")){
				play();
			} else if (input.equalsIgnoreCase("blue theme")) {
				changeCss("BlueTheme");
			} else if (input.equalsIgnoreCase("dark theme")) {
				changeCss("DarkTheme");
			} else if (input.equalsIgnoreCase("red theme")) {
				changeCss("RedTheme");
			} else if (input.equalsIgnoreCase("green theme")) {
				changeCss("GreenTheme");
			} else if (input.equalsIgnoreCase("royal theme")) {
				changeCss("RoyalTheme");
				tmh.removeSelected();
			} else if (input.equalsIgnoreCase("cog theme")) {
				changeCss("CogTheme");
			} else if (input.equalsIgnoreCase("christmas theme")) {
				changeCss("ChristmasTheme");
			} else if (input.equalsIgnoreCase("wolf theme")) {
				changeCss("WolfTheme");
			} else if (input.equalsIgnoreCase("dragon theme")) {
				changeCss("DragonTheme");
			} else if (input.equalsIgnoreCase("halloween theme")) {
				changeCss("HalloweenTheme");
			} else if (input.equalsIgnoreCase("ghost theme")) {
				changeCss("GhostTheme");
			} else if (input.equalsIgnoreCase("chinese theme")) {
				changeCss("ChineseTheme");
			} else if (input.equalsIgnoreCase("singapore theme")) {
				changeCss("SingaporeTheme");
			} else if (input.equalsIgnoreCase("snake theme")) {
				changeCss("SnakeTheme");
			} else if (input.equalsIgnoreCase("ghosts theme")) {
				changeCss("GhostsTheme");
			} else if (input.equalsIgnoreCase("popup")) {
				showPopup();
			}else if(input.equalsIgnoreCase("tutorial")){
				new TutorialLoader(overallView, themeUrl);
			}
			else{
				executeCommand(input, feedback);

				updateTodayView();
				updateTimelineView();
				clu.forwardToPrevious();
				clu.pushInput(input);
				ah.removeHelper();
			}

		} else if (e.getCode() == KeyCode.UP) {
			clu.displayPreviousInput();
		} else if (e.getCode() == KeyCode.DOWN) {
			clu.displayForwardInput();
		} else {

			//			try {
			//				if (e.getCode() == KeyCode.BACK_SPACE) {

			if (ah.getIsFocusTable()
					&& commandLine.getText().equals("")) {
				tableViewController.scrollToBack();
			}
			//
			//					input = commandLine.getText().substring(0,
			//							commandLine.getText().length() - 1);
			//				} else {
			//					input = commandLine.getText() + e.getText();
			//				}
			//				
			//				if (input.split(" ")[0].equalsIgnoreCase("add")) {
			//					ah.displayHelper();
			//				} else if (input.length() < 2) {
			//					ah.revertView();
			//				}
			//
			//				helperViewController.setHelperView(input);
			//
			//			} catch (Exception exc) {
			//				System.out.println("mother father gentlemen");
			//			}
		}
	}


	/**
	 * Refactored this snippet of code
	 * from handleKeyPressed method for
	 * external use. - smallson
	 */
	public void executeCommand(String input, String feedback) {
		try {
			feedback = logicFacade.getFeedBack(input);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if(shouldUpdateAllView(feedback)){
			setAllView(logicFacade.getAllList());
		}
		if(shouldSwitchToSearch(feedback)){
			switchToSearch(logicFacade.getSearchedList());
			ah.showTableView();
		}

		tfOutput.setText(feedback);
	}

	private void updateTodayView() {
		ObservableList<ModelTask> allList = logicFacade.getAllList();
		ObservableList<ModelTask> todayList = tlm.getTodayList(allList);
		todayViewController.setTodayView(todayList);
	}

	private void updateTimelineView() {
		tvm.setAllList(logicFacade.getAllList());
	}

	private void play() {
		final URL resource = getClass().getResource("a.mp3");
		final Media media = new Media(resource.toString());
		final MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
	}

	private boolean shouldSwitchToSearch(String feedback) {
		return feedback.contains(ErrorMessages.SUCCESS_SEARCH_MESSAGE);
	}

	private boolean shouldUpdateAllView(String feedback) {
		return feedback == ErrorMessages.SUCCESS_UNDONE_MESSAGE
				|| feedback == ErrorMessages.SUCCESS_REDONE_MESSAGE;
	}

	private void switchToSearch(ObservableList<ModelTask> list) {
		tableViewController.switchToSearch(list);
	}

	private void switchToAll() {
		tableViewController.switchToAll();
	}

	private void setAllView(ObservableList<ModelTask> list) {
		tableViewController.setAllView(list);
	}

	private void changeCss(String cssFileName) {
		themeUrl = getClass().getResource("../view/" + cssFileName + ".css")
				.toExternalForm();

		overallView.getStylesheets().clear();
		overallView.getStylesheets().add(themeUrl);

		tableView.getStylesheets().clear();
		tableView.getStylesheets().add(themeUrl);

		todayView.getStylesheets().clear();
		todayView.getStylesheets().add(themeUrl);

		helperView.getStylesheets().clear();
		helperView.getStylesheets().add(themeUrl);

		try {
			pm.saveCSSPref(cssFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showPopup() {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PopupView.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			page.getStylesheets().clear();
			page.getStylesheets().add(themeUrl);

			Stage popupStage = new Stage();
			popupStage.setTitle("Popup");
			popupStage.initModality(Modality.WINDOW_MODAL);
			popupStage.initStyle(StageStyle.TRANSPARENT);
			popupStage.initOwner(primaryStage);

			Scene scene = new Scene(page);
			popupStage.setScene(scene);

			PopupController controller = loader.getController();
			controller.setPopupStage(popupStage);

			popupStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void animateRight(){
		ah.animateRight();
	}

	public void animateLeft(){
		ah.animateLeft();
	}


	/*
	 * unused classes for initial testing with command buttons in gui
	 * 
	 * @FXML private void handleDeleteTask() { String numString =
	 * commandLine.getText(); commandLine.clear();
	 * 
	 * if(numString == null || numString.length() == 0){
	 * tfOutput.setText("Invalid deletion"); } else{ int lineNum =
	 * Integer.parseInt(numString); taskList.remove(lineNum - 1); for(int i =
	 * lineNum-1; i<taskList.size(); i++){ taskList.get(i).setPosition(i+1); } }
	 * }
	 * 
	 * @FXML private void handleAddTask() { String taskString =
	 * commandLine.getText(); commandLine.clear(); ModelTask task = new
	 * ModelTask(taskString, new Date(), taskList.size() + 1);
	 * taskList.add(task); }
	 * 
	 * @FXML private void handleClear(){ taskList.clear(); }
	 * 
	 * @FXML private void handleEditTask(){ switchToAll(); }
	 * 
	 * @FXML private void handleSearchTask(){
	 * tableViewController.setVisible(false); }
	 */
}
