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
	private static final String STRING_SPACE = " ";
	private static final String STRING_DASH = "-";
	private static final String STRING_SLASH = "/";
	private static final String STRING_WAVE_DASH = "~";
	private static final String STRING_TO = "TO";
	private static final String STRING_BEFORE_MIDNIGHT = "2359";

	/**
	 * String Dictionaries
	 */
	private final String[] DICTIONARY_KEYWORDS_DEADLINE = { "BY", "BEFORE"};
	private final String[] DICTIONARY_KEYWORDS_STARTTIME = { "AT", "FROM", "ON"};
	private final String[] DICTIONARY_KEYWORDS_ENDTIME = { "TO" , "-" , "UNTIL", "TIL"};
	private final String[] DICTIONARY_KEYWORDS_TODAY = { "TODAY", "TDY" };
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

		input = replaceCommandWord(input);

		DateParser dp = new DateParser();
		TimeParser tp = new TimeParser();
		ParseFreeManager pfm = ParseFreeManager.getInstance();

		input = pfm.getParseFreeInput(input);
		String[] tokens = input.split(STRING_SPACE);
		String tempInput;

		for (int i = 0; i < tokens.length; i++) {

			tempInput = input;

			if(!isDeadLineFound && !isEndDateFound && !isStartDateFound && previousNotKeyWord(tokens,i,input)){
				input = dp.parseDateWithoutKeyword(tokens, i, input);
				if(dp.getDate() != null){
					startDate = dp.getDate();
					dp.setDateStart(startDate);
					isStartDateFound = true;
				}
			}

			if(!isStartTimeFound && !isEndTimeFound){
				input = tp.parseDashTimeWithoutKeyword(tokens, i, input);
				if(tp.getStart() != null && tp.getEnd() != null){
					startTime = tp.getStart();
					endTime = tp.getEnd();
					isStartTimeFound = true;
					isEndTimeFound = true;
				}
			}

			if((!isStartTimeFound || !isEndTimeFound) && previousNotKeyWord(tokens,i,input)){
				input = tp.parseTimeWithoutKeyword(tokens, i, input);
				if(tp.getTime() != null){
					if(!isStartTimeFound){
						startTime = tp.getTime();
						isStartTimeFound = true;
					} else if(!isEndTimeFound) {
						endTime = tp.getTime();
						isEndTimeFound = true;
					}
				}
				
				if(dictionaryContains(DICTIONARY_KEYWORDS_TODAY, tokens[i]) && !isEndTimeFound){
					endTime = STRING_BEFORE_MIDNIGHT;
					isEndTimeFound = true;
					input = input.replaceFirst(tokens[i], "").trim();
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

				if(isNotOutOfBounds(i+1,tokens.length) && !isEndTimeFound){
					if(dictionaryContains(DICTIONARY_KEYWORDS_TODAY, tokens[i+1])){
						endTime = STRING_BEFORE_MIDNIGHT;
						isEndTimeFound = true;
						input = input.replaceFirst(tokens[i] + STRING_SPACE + tokens[i+1], "").trim();
					}
				}
			}

			if(dictionaryContains(DICTIONARY_KEYWORDS_STARTTIME, tokens[i])){

				if(!isStartTimeFound && !isEndTimeFound){
					input = tp.parseDashTimeWithKeyword(tokens, i, input);
					if(tp.getStart() != null && tp.getEnd() != null){
						startTime = tp.getStart();
						endTime = tp.getEnd();
						isStartTimeFound = true;
						isEndTimeFound = true;
					}
				}

				if(!isStartDateFound){
					input = dp.parseDateWithKeyword(tokens, i, input);
					if(dp.getDate() != null){
						startDate = dp.getDate();
						dp.setDateStart(startDate);
						isStartDateFound = true;
					}
				}
				
				if(isNotOutOfBounds(i+1,tokens.length) && !isStartDateFound){
					if(dictionaryContains(DICTIONARY_KEYWORDS_TODAY, tokens[i+1])){
						startDate = getCurrentDate();
						isStartDateFound = true;
						input = input.replaceFirst(tokens[i] + STRING_SPACE + tokens[i+1], "").trim();
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

			//			if(isEndDateFound && isStartDateFound){
			//				if(!validEndDate(endDate , startDate)){
			//					endDate = null;
			//					isEndDateFound = false;
			//					input = tempInput;
			//				}
			//			}

		}

		if(isEndDateFound && isDeadLineFound && !isStartDateFound){
			if(validEndDate(endDate , deadLine)){
				startDate = deadLine;
				deadLine = null;
				isStartDateFound = true;
			} 
		}

		if(!isDeadLineFound && !isStartDateFound && !isEndDateFound){

			String temp = null;

			if(isEndTimeFound){
				temp = endTime;
			}
			if(isStartTimeFound){
				temp = startTime;
			}

			if(isStartTimeFound && isEndTimeFound){
				if(Integer.parseInt(endTime) < Integer.parseInt(startTime)){
					startDate = getModifiedDate(0);
					endDate = getModifiedDate(1);
					isStartDateFound = true;
					isEndDateFound = true;
				} 
			} 
			if (temp != null){
				try{
					if(Integer.parseInt(getCurrentTime()) > Integer.parseInt(temp)){
						deadLine = getModifiedDate(1);
						isDeadLineFound = true;
					}
					else{
						deadLine = getModifiedDate(0);
						isDeadLineFound = true;
					}
				}catch(Exception e){
					System.out.println("error");
				}
			}
		}
		
		if(isStartDateFound && isStartTimeFound && isEndTimeFound && !isEndDateFound){
			if(Integer.parseInt(endTime) < Integer.parseInt(startTime)){
				endDate = getDayModifiedDate(1,startDate);
				isEndDateFound = true;
			}
		}


		if(onlyDeadLineFound()){
			endTime = STRING_BEFORE_MIDNIGHT;
		}

		if(allDatesFound()){
			deadLine = null;
		}

		input = pfm.replaceParseFree(input);
		taskDescription = input;						
	}

	private String getCurrentDate() {
		String currDate;
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd/MM/YYYY");

		Calendar calendar = Calendar.getInstance();
		currDate = dayFormat.format(calendar.getTime());

		return currDate;
	}

	private boolean previousNotKeyWord(String[] tokens, int i, String input) {
		
		try{
			if(dictionaryContains(DICTIONARY_KEYWORDS_DEADLINE,tokens[i-1])){
				return false;
			} else if (dictionaryContains(DICTIONARY_KEYWORDS_STARTTIME,tokens[i-1])){
				return false;
			} else if (dictionaryContains(DICTIONARY_KEYWORDS_ENDTIME,tokens[i-1])){
				return false;
			}
		} catch (IndexOutOfBoundsException e){
			return false;
		}
		return true;
	}
	
	private static String getDayModifiedDate(int offset, String date){

		String modDate;
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd/MM/YYYY");

		String[] dateArray = date.split(STRING_SLASH);
		int day = Integer.parseInt(dateArray[0]);
		int month = Integer.parseInt(dateArray[1]);
		int year = Integer.parseInt(dateArray[2]);

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		calendar.add(Calendar.DAY_OF_MONTH, offset);
		modDate = dayFormat.format(calendar.getTime());

		return modDate;
	}

	private boolean allDatesFound() {
		return isDeadLineFound && isStartDateFound && isEndDateFound;
	}

	private boolean onlyDeadLineFound() {
		return isDeadLineFound && !isStartDateFound && !isEndDateFound && !isStartTimeFound && !isEndTimeFound;
	}

	private boolean isNotOutOfBounds(int index, int length) {
		return index < length && index > 0;
	}

	private boolean validEndDate(String endDate, String startDate) {

		String[] endArray = endDate.split(STRING_SLASH);
		String[] startArray = startDate.split(STRING_SLASH);

		if(Integer.parseInt(endArray[2]) < Integer.parseInt(startArray[2])){
			return false;
		} if (Integer.parseInt(endArray[2]) == Integer.parseInt(startArray[2])){
			if (Integer.parseInt(endArray[1]) < Integer.parseInt(startArray[1])){
				return false;
			}
		} if (Integer.parseInt(endArray[1]) == Integer.parseInt(startArray[1])){
			if (Integer.parseInt(endArray[0]) < Integer.parseInt(startArray[0])){
				return false;
			}
		}

		return true;
	}

	private String replaceCommandWord(String input) {
		return input.replaceFirst(input.split(STRING_SPACE)[0], "").trim();
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
		String modDate;
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd/MM/YYYY");

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, offset);
		modDate = dayFormat.format(calendar.getTime());

		return modDate;
	}	
}
