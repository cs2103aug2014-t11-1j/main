package parser;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class is used to extract required
 * information from the user's input.
 */

public class InputParser {

	/**
	 * String constants
	 */
	private final String STRING_SPACE = " ";
	
	/**
	 * String Dictionaries
	 */
	private final String[] DICTIONARY_KEYWORDS_DEADLINE = { "BY", "BEFORE" };
	private final String[] DICTIONARY_KEYWORDS_STARTTIME = { "AT", "AFTER", "ON", "FROM"};
	private final String[] DICTIONARY_KEYWORDS_ENDTIME = { "TO", "-"};
	
	/**
	 * Required information from input
	 */
	//we should store such information in a separate class eg Task
	private String taskDescription;
	private String startTime;
	private String endTime;
	private String deadLine;
	//currently not implemented
	private String endDate;
	private String startDate;
	
	//making this class a static
	private static InputParser inputParser = new InputParser();
	
	private InputParser(){
		taskDescription = null;
		startDate = null;
		startTime = null;
		endDate = null;
		endTime = null;
		deadLine = null;
	}
	
	//for other classes to get an inputparser
	public static InputParser getInstance(){
		return inputParser;
	}
	
	public String getTaskDescription(){
		return taskDescription;
	}
	public String getStartDate(){
		return startDate;
	}
	public String getStartTime(){
		return startTime;
	}
	public String getEndDate(){
		return endDate;
	}
	public String getEndTime(){
		return endTime;
	}
	public String getDeadLine(){
		return deadLine;
	}
	
	public void parseInput(String input){
		
		DateParser dp = new DateParser();
		TimeParser tp = new TimeParser();
		
		String[] tokens = input.split(STRING_SPACE);
		
		for (int i = 0; i < tokens.length; i++) {

			if(tokens[i].contains("\"")){				
				System.out.println(tokens[i].split("\"").length);
				System.out.println(i);
				if(tokens[i].split("\"").length % 2 == 0){
					i++;
				}
				else{
					while(!tokens[i].contains("\"")){
						i++;
					}
				}
			}
			
			if(i >= tokens.length){
				taskDescription = input;
				break;
			}
			
			if(dictionaryContains(DICTIONARY_KEYWORDS_DEADLINE, tokens[i])){
				
					input = dp.parseDateWithKeyword(tokens, i, input);
					deadLine = dp.getDate();


					input = tp.parseTimeWithKeyword(tokens, i, input);
					if(tp.getTime() != null){
						endTime = tp.getTime();
				}
					
			}

			if(dictionaryContains(DICTIONARY_KEYWORDS_STARTTIME, tokens[i])){
				input = dp.parseDateWithKeyword(tokens, i, input);
				startDate = dp.getDate();
				
				input = tp.parseTimeWithKeyword(tokens, i, input);
				if(tp.getTime() != null){
					startTime = tp.getTime();
				}
			}

			if(dictionaryContains(DICTIONARY_KEYWORDS_ENDTIME, tokens[i])){
				input = dp.parseDateWithKeyword(tokens, i, input);
				endDate = dp.getDate();
				
				input = tp.parseTimeWithKeyword(tokens, i, input);
				if(tp.getTime() != null){
					endTime = tp.getTime();
				}
			}
		}
		
		taskDescription = input;						
	}
	
	private boolean isNotOutOfBounds(int index, int length) {
		return index < length && index > 0;
	}

	private boolean dictionaryContains(String[] dictionary, String keyword) {
		boolean isFound = false;
		for (int i = 0; i < dictionary.length; i++) {
			if (dictionary[i].equalsIgnoreCase(keyword)) {
				isFound = true;
				break;
			}
		}
		return isFound;
	}
	

	
}
