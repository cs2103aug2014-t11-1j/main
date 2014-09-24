package logic;

/**
 * @author Zhang Yongkai
 * This logicFacade class will interact with the GUI. It receives raw input from UI and return
 * feedback to the GUI in the form of:
 * 1) a string to tell the GUI what action is done or not done; 
 * 2) a ObservableList<ModelTask> for the GUI to display 
 * 
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import parser.ParserFacade;
import storage.ModelTask;
import storage.Storage;

public class LogicFacade {

	private static LogicFacade logicFacade = new LogicFacade();
	private CommandExecutor executor;
	private ParserFacade parserFacade = ParserFacade.getInstance();
	private ObservableList<ModelTask> taskList;
	private ObservableList<ModelTask> searchedList;
	private Storage storage;

	// constructor
	private LogicFacade() {
		try {
			storage = new Storage("text.txt");
			taskList = FXCollections.observableArrayList();
			searchedList = FXCollections.observableArrayList();
		} catch (Exception e) {
			System.out.println("cannot initialize logicFacade class");
		}
	}

	// accessor
	public LogicFacade getInstance() {
		return logicFacade;
	}

	public ObservableList<ModelTask> getOriginalListFromFile(){
		return storage.getListFromFile();
	}
	
	public String getFeedBack(String InputFromGui) throws Exception {
		String feedBack = executeCommand(InputFromGui);
		return feedBack;
	}

	public String executeCommand(String inputFromGui) throws Exception {
		String commandWord = parserFacade.getCommandString(inputFromGui);
		String actualCommandDescription = parserFacade
				.getStringWithoutCommand(inputFromGui);
		executor = new CommandExecutor(commandWord, actualCommandDescription);
		String feedBack = executor.getFeedBack();
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

}
