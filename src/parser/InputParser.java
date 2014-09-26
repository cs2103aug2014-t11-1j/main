package parser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class is used to extract required
 * information from the user's input.
 * 
 * * Author: smallson
 */

public class InputParser {

	/**
	 * String constants
	 */
	private final String STRING_SPACE = " ";
	private final String STRING_DASH = "-";
	private final String STRING_WAVE_DASH = "~";
	private final String STRING_TO = "TO";

	/**
	 * String Dictionaries
	 */
	private final String[] DICTIONARY_KEYWORDS_DEADLINE = { "BY", "BEFORE", "ON" };
	private final String[] DICTIONARY_KEYWORDS_STARTTIME = { "AT", "AFTER", "FROM"};
	private final String[] DICTIONARY_KEYWORDS_ENDTIME = { "TO" };
	//	private final String[] DICTIONARY_KEYWORDS_DASH = { "-", "~" };

	/**
	 * Required information from input
	 */
	private String taskDescription;
	private String startTime;
	private String endTime;
	private String deadLine;
	private String endDate;
	private String startDate;

	private boolean isDeadLineFound;
	private boolean isStartTimeFound;
	private boolean isStartDateFound;
	private boolean isEndTimeFound;
	private boolean isEndDateFound;

	public InputParser(){
		taskDescription = null;
		startDate = null;
		startTime = null;
		endDate = null;
		endTime = null;
		deadLine = null;

		isDeadLineFound = false;
		isStartTimeFound = false;
		isStartDateFound = false;
		isEndTimeFound = false;
		isEndDateFound = false;
	}

	public String getTaskDescription(){
		return taskDescription.replaceAll("\\s+", " ");
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

			if(!isDeadLineFound && !isEndDateFound){
				input = dp.parseDateWithoutKeyword(tokens, i, input);
				if(dp.getDate() != null){
					deadLine = dp.getDate();
					isDeadLineFound = true;
				}
			}

			if(!isEndTimeFound && !isStartTimeFound){
				input = tp.parseTimeWithoutKeyword(tokens, i, input);
				if(tp.getTime() != null){
					endTime = tp.getTime();
					isEndTimeFound = true;
				}
			}

			if(!isStartDateFound && !isEndDateFound){
				if(tokens[i].contains(STRING_DASH) || (tokens[i].contains(STRING_WAVE_DASH)) || (tokens[i].contains(STRING_TO))){
					input = dp.parseDashDateWithoutKeyword(tokens, i, input);
					if(dp.getDateStart() != null && dp.getDateEnd() != null){
						startDate = dp.getDateStart();
						endDate = dp.getDateEnd();
						isStartDateFound = true;
						isEndDateFound = true;
					}
				}
			}

			if(dictionaryContains(DICTIONARY_KEYWORDS_DEADLINE, tokens[i])){
				if(!isDeadLineFound){
					input = dp.parseDateWithKeyword(tokens, i, input);
					if(dp.getDate() != null){
						deadLine = dp.getDate();
						isDeadLineFound = true;
					}
				}

				if(!isEndTimeFound){
					input = tp.parseTimeWithKeyword(tokens, i, input);
					if(tp.getTime() != null){
						endTime = tp.getTime();
						isEndTimeFound = true;
					}
				}					
			}

			if(dictionaryContains(DICTIONARY_KEYWORDS_STARTTIME, tokens[i])){
				if(!isStartDateFound){
					input = dp.parseDateWithKeyword(tokens, i, input);
					if(dp.getDate() != null){
						startDate = dp.getDate();
						isStartDateFound = true;
					}
				}

				if(!isStartTimeFound){
					input = tp.parseTimeWithKeyword(tokens, i, input);
					if(tp.getTime() != null){
						startTime = tp.getTime();
						isStartTimeFound = true;
					}
				}

			}

			if(dictionaryContains(DICTIONARY_KEYWORDS_ENDTIME, tokens[i])){
				if(!isEndDateFound){
					input = dp.parseDateWithKeyword(tokens, i, input);
					if(dp.getDate() != null){
						endDate = dp.getDate();
						isEndDateFound = true;
					}
				}

				if(!isEndTimeFound){
					input = tp.parseTimeWithKeyword(tokens, i, input);
					if(tp.getTime() != null){
						endTime = tp.getTime();
						isEndTimeFound = true;
					}
				}
			}
		}

		if(!isDeadLineFound && !isStartDateFound && !isEndDateFound){

			String temp = null;
			if(isStartTimeFound){
				temp = startTime;
			}
			if(isEndTimeFound){
				temp = endTime;
			}

			if(temp != null){
				try{
					if(Integer.parseInt(getCurrentTime()) > Integer.parseInt(temp)){
						deadLine = getModifiedDate(1);
					}
					else{
						deadLine = getModifiedDate(0);
					}
				}catch(Exception e){
					System.out.println("error");
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

	private String getCurrentTime(){
		SimpleDateFormat currTime = new SimpleDateFormat("HHmm");//dd/MM/yyyy
		Date now = new Date();
		String strTime = currTime.format(now);
		return strTime;
	}

	private static String getModifiedDate(int offset){
		String currDate;
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd/MM/YYYY");

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, offset);
		currDate = dayFormat.format(calendar.getTime());

		return currDate;
	}	
}
