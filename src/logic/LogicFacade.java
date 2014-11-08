package logic;

import java.util.ArrayList;
import java.util.logging.Level;
import com.ModelTask;
import com.TentativeTask;
import com.TimePeriod;
import com.util.MyLogger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import storage.Storage;

//@author A0110567L
/**
 * This logicFacade class will interact with the GUI. It receives raw input from UI and return
 * feedback to the GUI in the form of:
 * 1) a string to tell the GUI whether an action is done or not done; 
 * 2) an int value to tell GUI which view to switch to
 */
public class LogicFacade {

	private static LogicFacade logicFacade = new LogicFacade();
	private CommandExecutor executor_;
	private ObservableList<ModelTask> taskList_;
	private ObservableList<ModelTask> searchedList_;
	private Storage storage_;

	// constructor
	private LogicFacade() {
		try {
			storage_ = new Storage("phantomStorage.txt");
			taskList_ = getOriginalListFromFile();
			executor_ = new CommandExecutor(taskList_, storage_);
			searchedList_ = FXCollections.observableArrayList();
		} catch (Exception e) {
			MyLogger.log(Level.SEVERE, "cannot initialize logicFacade class");
			System.out.println("cannot initialize logicFacade class");
		}
	}

	// accessor
	public static LogicFacade getInstance() {
		return logicFacade;
	}

	public ObservableList<ModelTask> getOriginalListFromFile() {
		return storage_.getListFromFile();
	}

	public String getUserFeedBack() throws Exception {
		String feedBack = executor_.getUserFeedBack();
		return feedBack;
	}

	public int executeCommand(String inputFromGui) throws Exception {
		executor_.executeCommand(inputFromGui);
		int feedBack = executor_.getGuiFeedBack();
		return feedBack;
	}

	public ObservableList<ModelTask> getAllList() {
		taskList_ = executor_.getAllList();
		return taskList_;
	}

	public ObservableList<ModelTask> getSearchedList() {
		searchedList_ = executor_.getSearchedList();
		return searchedList_;
	}

	public Storage getStorage() {
		return storage_;
	}

	public ArrayList<TentativeTask> getTentativeTasks() {
		return CommandFactory.tentativeTasks;
	}

	public ArrayList<TimePeriod> getGlobalBlockedTimePeriods() {
		return CommandFactory.globalBlockedTimePeriods;
	}

	public ObservableList<String> getTentativeTasksObservableList() {
		return CommandFactory.tentativeTasksObservableList;
	}
}
