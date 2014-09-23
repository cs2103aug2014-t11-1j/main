package logic;

import java.util.ArrayList;

import parser.ParserFacade;
import storage.Task;

/**
 * @author: Zhang Yongkai
 */

public class LogicFacade {

	private static LogicFacade lf = new LogicFacade();
	private ParserFacade pf = ParserFacade.getInstance();
	private CommandExecutor executor;
	
	private LogicFacade(){
	}
	
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

	public ArrayList<Task> listToday (){
		return executor.getTodayList();
	}
	
	public ArrayList<Task> listAll(){
		return executor.getAllList();
	}
	
	public ArrayList<Task> listSearch(){
		return executor.getSearchList();
	}

}
