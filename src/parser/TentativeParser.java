package parser;

import java.util.ArrayList;

import storage.TentativeTask;
import storage.TimePeriod;

/**
 * This class is used to parse
 * tentative input by the user.
 * 
 * * Author: smallson
 */

public class TentativeParser {
	
	/**
	 * String constants
	 */
	private final String STRING_SPACE = " ";
	private final String STRING_COLON = ":";
	private final String STRING_SEMICOLON = ";";
	
	private String taskDescription;
	private ArrayList<TimePeriod> list;
	private TentativeTask task;
	
	private static TentativeParser tp = new TentativeParser();
	
	private TentativeParser(){
	}

	public static TentativeParser getInstance(){
		return tp;
	}
	
	public TentativeTask parseTentative(String input){
			
		input = replaceCommandWord(input);
		String [] inputArray = input.split(STRING_COLON);
		String taskDescription = inputArray[0];
		TentativeTask task = new TentativeTask(taskDescription);
		
		if(inputArray.length > 2){
			task = parseDateAndTime(input,task);
		} else {
			System.out.println("No colon");
		}
		
		return task;
	}


	private TentativeTask parseDateAndTime(String input, TentativeTask task) {
		return task;
	}

	private String replaceCommandWord(String input) {
		return input.replaceFirst(input.split(STRING_SPACE)[0], "").trim();
	}
}
