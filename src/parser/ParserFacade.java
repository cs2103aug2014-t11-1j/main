package parser;

import storage.Task;

/**
 * This class is the facade class for the
 * Parser package.
 * 
 * * Author: smallson
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
		ip.parseInput(input); 
		return new Task(ip.getTaskDescription(),ip.getStartDate(),ip.getStartTime(),ip.getEndDate(),ip.getEndTime(),ip.getDeadLine());
	}
	
	public String getCommandString(String input){
		CommandRetriever cr = CommandRetriever.getInstance();
		return cr.getCommandString(input);
	}
	
	public String getStringWithoutCommand(String input){
		CommandRetriever cr = CommandRetriever.getInstance();
		return input.replaceFirst(cr.getCommandString(input),"").trim();
	}
	
}
