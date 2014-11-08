package parser;

import com.Task;
import com.TentativeTask;

/**
 * This class is the facade class for the
 * Parser package.
 * 
 * * Author: smallson
 */
//@author A0116211B

public class ParserFacade {
	
	private static ParserFacade pf_ = new ParserFacade();
	
	private ParserFacade(){		
	}
	
	public static ParserFacade getInstance() {
		return pf_;
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
	
	public TentativeTask getTentative(String input){
		TentativeParser tp = TentativeParser.getInstance();
		return tp.parseTentative(input);
	}
	
}
