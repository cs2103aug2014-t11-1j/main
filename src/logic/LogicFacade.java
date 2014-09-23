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

public class LogicFacade {

	private static LogicFacade lf = new LogicFacade();
	private CommandExecutor executor;
	private ParserFacade pf = ParserFacade.getInstance();
	private ObservableList<ModelTask> taskList;
	private ObservableList<ModelTask> searchedList;
	
	//constructor
	private LogicFacade(){
		taskList = FXCollections.observableArrayList();
		searchedList = FXCollections.observableArrayList();
	}
	
	//accessor
	public LogicFacade getInstance (){
		return lf;
	}
	
	public String getFeedBack(String InputFromGui) throws Exception{
		String feedBack = executeCommand(InputFromGui);
		return feedBack;
	}

	
	public String executeCommand(String inputFromGui) throws Exception {
		String commandWord = pf.getCommandString(inputFromGui);
		String actualCommandDescription = pf.getStringWithoutCommand(inputFromGui);
		executor = new CommandExecutor(commandWord,actualCommandDescription);
		String feedBack = executor.getFeedBack();
		return feedBack;
	}

	public ObservableList<ModelTask> getAllList(){
		taskList = executor.getAllList();
		return taskList;
	}
	
	public ObservableList<ModelTask> getSearchedList(){
		searchedList = executor.getSearchedList();
		return searchedList;
	}

}
