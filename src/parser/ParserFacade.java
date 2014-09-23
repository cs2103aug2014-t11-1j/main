package parser;

import storage.Task;

/**
 * This class is the facade class for the
 * Parser package.
 */


public class ParserFacade {
	
	private static ParserFacade pf = ParserFacade.getInstance();
	
	public static ParserFacade getInstance() {
		return pf;
	}

	protected Task getTask(String input){
		InputParser ip = InputParser.getInstance();
		Task task = new Task(ip.getTaskDescription(),ip.getStartDate(),ip.getStartTime(),ip.getEndDate(),ip.getEndTime(),ip.getDeadLine()); 
		return task;
	}
	
	protected String getCommandString(String input){
		return null;
	}
	
}
