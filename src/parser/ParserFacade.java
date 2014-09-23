package parser;

import storage.Task;

/**
 * This class is the facade class for the
 * Parser package.
 */


public class ParserFacade {
	
	private static ParserFacade pf = new ParserFacade();
	
	private ParserFacade(){
		
	}
	
	public static ParserFacade getInstance() {
		return pf;
	}

	public Task getTask(String input){
		InputParser ip = new InputParser();
		CommandRetriever cr = CommandRetriever.getInstance();
		input = input.replace(cr.getCommandString(input),"");
		ip.parseInput(input);
		Task task = new Task(ip.getTaskDescription(),ip.getStartDate(),ip.getStartTime(),ip.getEndDate(),ip.getEndTime(),ip.getDeadLine()); 
		return task;
	}
	
	public String getCommandString(String input){
		CommandRetriever cr = CommandRetriever.getInstance();
		return cr.getCommandString(input);
	}
	
	public String getStringWithoutCommand(String input){
		CommandRetriever cr = CommandRetriever.getInstance();
		return input.replace(cr.getCommandString(input),"");
	}
	
}
