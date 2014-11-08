package gui.controller.view;

/**
 * @author A0116018R
 * 
 * This class is the controller for OverallView.
 * This class also contains the Parents of the other views
 * and their controllers. 
 * This class will interpret user events and send and receive input
 * from logic.
 */

import gui.TrayApplication;
import gui.controller.AnimationHandler;
import gui.controller.CommandLineUtility;
import gui.controller.EditListener;
import gui.controller.HelperListener;
import gui.controller.PhantomClock;
import gui.controller.PreferenceManager;
import gui.controller.TodayListManager;

import java.io.IOException;
import java.util.logging.Level;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.FeedbackMessages;
import logic.LogicFacade;

import com.ModelTask;
import com.util.MyLogger;

public class PhantomController {
	private static final String DELETE_PROMPT = "delete ";

	private static final String EMPTY_STRING = "";

	private static boolean hasOccured = false;

	private Stage primaryStage_;
	private AnchorPane overallView_;

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
	private Label timeLabel;

	private LogicFacade logicFacade_;
	// for testing purposes
	// private LogicFacadeDummy logicFacade;

	private AnimationHandler ah_;
	private CommandLineUtility clu_;
	private TodayListManager tlm_;
	private PreferenceManager pm_;
	private TrayApplication ta_;
	
	private HelperListener helperListener_;
	
	//@author A0116211B
	final KeyCombination keyCombShiftRight = KeyCodeCombination.valueOf("Shift+RIGHT");
	final KeyCombination keyCombShiftLeft = KeyCodeCombination.valueOf("Shift+LEFT");

	//@author A011601R
	//constructor
	public PhantomController() {
		logicFacade_ = LogicFacade.getInstance();

		// for testing purposes
		// logicFacade_ = new LogicFacadeDummy();
	}
	
	//public methods
	public void setPrimaryStage(Stage stage) {
		primaryStage_ = stage;
	}

	public void setOverallView(AnchorPane overallView) {
		overallView_ = overallView;
	}
	
	//@author A0116211B
	public void initPrefManager() {
		pm_ = PreferenceManager.getInstance();
		pm_.initViews(overallView_);
	}
	
	//@author A0116018R
	/**
	 * Refactored this snippet of code
	 * from handleKeyPressed method for
	 * external use. - smallson
	 */
	public void executeCommand(String input) {
		int guiFeedBack = FeedbackMessages.NORMAL_STATE;
		String userFeedBack = EMPTY_STRING;
		
		try {
			guiFeedBack = logicFacade_.executeCommand(input);
			userFeedBack = logicFacade_.getUserFeedBack();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if(guiFeedBack == FeedbackMessages.UPDATE_ALL_LIST){
			setAllView();
		}
		if(guiFeedBack == FeedbackMessages.SWITCH_TO_TEMP){
			switchToSearch(logicFacade_.getSearchedList());
			ah_.showTableView();
		}
		if(guiFeedBack == FeedbackMessages.SWITCH_TO_TEMP_DELETE){
			switchToSearch(logicFacade_.getSearchedList());
			ah_.showTableView();
			commandLine.setText(DELETE_PROMPT);
			commandLine.end();
		}
		updateTodayView();
		tfOutput.setText(userFeedBack);
		ah_.fadeLabel(tfOutput);
		tfOutput.setText(EMPTY_STRING);
	}
	
	public static boolean isHasOccured() {
		return hasOccured;
	}

	public static void setHasOccured(boolean hasOccured) {
		PhantomController.hasOccured = hasOccured;
	}
	
	//@author A0116211B
	public void animateRight(){
		ah_.animateRight();
	}

	public void animateLeft(){
		ah_.animateLeft();
	}
	
	//@author A0116018R
	//Initialisation
	@FXML
	private void initialize() {
		System.out.println("phantom initilising");
		initAll();
	}

	private void initAll() {
		initTableView();
		initTodayList();
		initClock();
		initAnimation();
		initCommandLineUtility();
		initHelper();
		ta_ = TrayApplication.getInstance();	
	}

	private void initTableView() {
		tableViewController.setAllView(logicFacade_.getAllList());
	}

	private void initTodayList() {
		tlm_ = new TodayListManager();
		updateTodayView();
	}

	private void initClock() {
		PhantomClock pc = PhantomClock.getInstance();
		pc.setClock(timeLabel);
	}

	private void initAnimation() {
		ah_ = AnimationHandler.getInstance();
		ah_.initialize(tableAnchor, todayAnchor, helperAnchor);
	}

	private void initCommandLineUtility() {
		clu_ = CommandLineUtility.getInstance();
		clu_.initialize(commandLine);
	}
	
	private void initHelper() {
		helperListener_ = new HelperListener(helperViewController);
		commandLine.textProperty().addListener(helperListener_);
	}

	//private FXML event handling
	@FXML
	private void handleExit() {
		System.exit(0);
	}

	@FXML
	private void handleMinimise() {
		primaryStage_.close();
		ta_.showProgramIsMinimizedMsg();
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
			setHasOccured(false);

			input = commandLine.getText();
			commandLine.clear();
			
			if (tableViewController.isSearched()) {
				setAllView();
			}
			
			if (input.equalsIgnoreCase("showall")) {
				ah_.showTableView();
			} else if (input.equalsIgnoreCase("showtoday")) {
				ah_.showTodayView();
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
			} else{
				executeCommand(input);

				updateTodayView();
				clu_.forwardToPrevious();
				clu_.pushInput(input);
				ah_.removeHelper();
			}

		} else if (e.getCode() == KeyCode.UP) {
			clu_.displayPreviousInput();
		} else if (e.getCode() == KeyCode.DOWN) {
			clu_.displayForwardInput();
		} else if(e.getCode() == KeyCode.PAGE_DOWN){
			if (ah_.getIsFocusTable() 
					&& commandLine.getText().equals(EMPTY_STRING)) {
				tableViewController.scrollToNext();
			}
	
		}else if(e.getCode() == KeyCode.PAGE_UP){
			if (ah_.getIsFocusTable() 
					&& commandLine.getText().equals(EMPTY_STRING)) {
				tableViewController.scrollToBack();
			}
		}
	}

	//private methods
	private void updateTodayView() {
		ObservableList<ModelTask> allList = logicFacade_.getAllList();
		ObservableList<ModelTask> todayList = tlm_.getTodayList(allList);
		todayViewController.setTodayView(todayList);
	}

	private void switchToSearch(ObservableList<ModelTask> list) {
		tableViewController.switchToSearch(list);
	}

	private void setAllView() {
		tableViewController.setAllView(logicFacade_.getAllList());
	}

	private void changeCss(String cssFileName) {
		pm_.setCss(cssFileName);
		
		try {
			pm_.saveCSSPref(cssFileName);
		} catch (IOException e) {
			e.printStackTrace();
			MyLogger.log(Level.WARNING, "preference not saved");
		}
	}
	
// not fully developed features
//	@FXML
//	private Parent timelineView;
//	@FXML
//	private TimelineViewController timelineViewController;
//	@FXML
//	private AnchorPane timelineAnchor;
//
//	@FXML
//	private Parent tentativeView;
//	@FXML
//	private TentativeViewController tentativeViewController;
//	@FXML
//	private AnchorPane tentativeAnchor;
	
//	private TimelineViewManager tvm;
	

//	private void initTimeline() {
//		tvm = new TimelineViewManager();
//		tvm.setTimelineViewController(timelineViewController);
//		updateTimelineView();
//	}
	
//	private void updateTimelineView() {
//	tvm.setAllList(logicFacade.getAllList());
//}
	
//	private void showPopup() {
//	try {
//
//		FXMLLoader loader = new FXMLLoader();
//		loader.setLocation(MainApp.class.getResource("controller/view/PopupView.fxml"));
//		AnchorPane page = (AnchorPane) loader.load();
//		
//		String themeUrl = pm.getThemeUrl();
//		page.getStylesheets().clear();
//		page.getStylesheets().add(themeUrl);
//
//		Stage popupStage = new Stage();
//		popupStage.setTitle("Popup");
//		popupStage.initModality(Modality.WINDOW_MODAL);
//		popupStage.initStyle(StageStyle.TRANSPARENT);
//		popupStage.initOwner(primaryStage);
//
//		Scene scene = new Scene(page);
//		popupStage.setScene(scene);
//
//		PopupController controller = loader.getController();
//		controller.setPopupStage(popupStage);
//
//		popupStage.showAndWait();
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
//}
//	@author A0116211B
//	private void play() {
//		final URL resource = getClass().getResource("a.mp3");
//		final Media media = new Media(resource.toString());
//		final MediaPlayer mediaPlayer = new MediaPlayer(media);
//		mediaPlayer.play();
//	}

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
