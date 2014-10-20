package logic;

/**
 * @author Zhang Yongkai
 * This logicFacade class will interact with the GUI. It receives raw input from UI and return
 * feedback to the GUI in the form of:
 * 1) a string to tell the GUI what action is done or not done; 
 * 2) a ObservableList<ModelTask> for the GUI to display 
 * 
 */

import java.util.ArrayList;
import java.util.logging.Level;

import com.MyLogger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import storage.ModelTask;
import storage.Storage;
import storage.TentativeTask;
import storage.TimePeriod;

public class LogicFacade {

	private static LogicFacade logicFacade = new LogicFacade();
	private CommandExecutor executor;
	private ObservableList<ModelTask> taskList;
	private ObservableList<ModelTask> searchedList;
	private Storage storage;

	// constructor
	private LogicFacade() {
		try {
			storage = new Storage("text.txt");
			taskList = getOriginalListFromFile();
			executor = new CommandExecutor(taskList,storage);
			searchedList = FXCollections.observableArrayList();
		} catch (Exception e) {
			MyLogger.log(Level.SEVERE,"cannot initialize logicFacade class");
			System.out.println("cannot initialize logicFacade class");
		}
	}

	// accessor
	public static LogicFacade getInstance() {
		return logicFacade;
	}

	public ObservableList<ModelTask> getOriginalListFromFile(){
		return storage.getListFromFile();
	}
	
	public String getUserFeedBack() throws Exception {
		String feedBack = executor.getUserFeedBack();
		return feedBack;
	}

	public int executeCommand(String inputFromGui) throws Exception {
		executor.executeCommand(inputFromGui);
		int feedBack = executor.getGuiFeedBack();
		return feedBack;
	}

	public ObservableList<ModelTask> getAllList() {
		taskList = executor.getAllList();
		return taskList;
	}

	public ObservableList<ModelTask> getSearchedList() {
		searchedList = executor.getSearchedList();
		return searchedList;
	}
	
	public Storage getStorage(){
		return storage;
	}

        public ArrayList<TentativeTask> getTentativeTasks(){
            return CommandFactory.tentativeTasks;
        }
        
        public ArrayList<TimePeriod> getGlobalBlockedTimePeriods(){
            return CommandFactory.globalBlockedTimePeriods;
        }
        
        public ObservableList<String> getTentativeTasksObservableList(){
            return CommandFactory.tentativeTasksObservableList;
        }
}
