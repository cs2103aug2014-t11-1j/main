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
//@author A0116211B

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
	private String taskDescription_;
	private String startTime_;
	private String endTime_;
	private String deadLine_;
	private String endDate_;
	private String startDate_;

	private boolean isDeadLineFound_;
	private boolean isStartTimeFound_;
	private boolean isStartDateFound_;
	private boolean isEndTimeFound_;
	private boolean isEndDateFound_;

	public InputParser(){
		taskDescription_ = null;
		startDate_ = null;
		startTime_ = null;
		endDate_ = null;
		endTime_ = null;
		deadLine_ = null;

		isDeadLineFound_ = false;
		isStartTimeFound_ = false;
		isStartDateFound_ = false;
		isEndTimeFound_ = false;
		isEndDateFound_ = false;
	}

	public String getTaskDescription(){
		return taskDescription_.replaceAll("\\s+", " ");
	}
	public String getStartDate(){
		return startDate_;
	}
	public String getStartTime(){
		return startTime_;
	}
	public String getEndDate(){
		return endDate_;
	}
	public String getEndTime(){
		return endTime_;
	}
	public String getDeadLine(){
		return deadLine_;
	}

	public void parseInput(String input){

		input = replaceCommandWord(input);

		DateParser dp = new DateParser();
		TimeParser tp = new TimeParser();
		ParseFreeManager pfm = ParseFreeManager.getInstance();

		input = pfm.getParseFreeInput(input);
		String[] tokens = input.split(STRING_SPACE);

		for (int i = 0; i < tokens.length; i++) {
			input = parseWord(input, dp, tp, tokens, i);
		}

		postParsingAdjustments();

		input = pfm.replaceParseFree(input);
		taskDescription_ = input;						
	}

	private String parseWord(String input, DateParser dp, TimeParser tp,
			String[] tokens, int i) {
		
		input = parseWithoutKeyword(input, dp, tp, tokens, i);
		input = parseDeadline(input, dp, tp, tokens, i);
		input = parseTimed(input, dp, tp, tokens, i);
		
		return input;
	}

	private String parseTimed(String input, DateParser dp, TimeParser tp,
			String[] tokens, int i) {
		
		input = parseStart(input, dp, tp, tokens, i);
		input = parseEnd(input, dp, tp, tokens, i);

		return input;
	}

	private String parseEnd(String input, DateParser dp, TimeParser tp,
			String[] tokens, int i) {
		
		if(dictionaryContains(DICTIONARY_KEYWORDS_ENDTIME, tokens[i])){
			
			input = parseEndDate(input, dp, tokens, i);
			input = parseEndTime(input, tp, tokens, i);

		}
		return input;
	}

	private String parseEndTime(String input, TimeParser tp, String[] tokens,
			int i) {
		if(!isEndTimeFound_){
			input = tp.parseTimeWithKeyword(tokens, i, input);
			if(tp.getTime() != null){
				endTime_ = tp.getTime();
				isEndTimeFound_ = true;
			}
		}
		return input;
	}

	private String parseEndDate(String input, DateParser dp, String[] tokens,
			int i) {
		if(!isEndDateFound_){
			input = dp.parseDateWithKeyword(tokens, i, input);
			if(dp.getDate() != null){
				endDate_ = dp.getDate();
				isEndDateFound_ = true;
			}
		}
		return input;
	}

	private String parseStart(String input, DateParser dp, TimeParser tp,
			String[] tokens, int i) {
		
		if(dictionaryContains(DICTIONARY_KEYWORDS_STARTTIME, tokens[i])){

			input = parseDashDate(input, tp, tokens, i);
			input = parseStartDate(input, dp, tokens, i);		
			input = parseToday(input, tokens, i);
			input = parseStartTime(input, tp, tokens, i);

		}
		return input;
	}

	private String parseStartTime(String input, TimeParser tp, String[] tokens,
			int i) {
		if(!isStartTimeFound_){
			input = tp.parseTimeWithKeyword(tokens, i, input);
			if(tp.getTime() != null){
				startTime_ = tp.getTime();
				isStartTimeFound_ = true;
			}
		}
		return input;
	}

	private String parseToday(String input, String[] tokens, int i) {
		if(isNotOutOfBounds(i+1,tokens.length) && !isStartDateFound_){
			if(dictionaryContains(DICTIONARY_KEYWORDS_TODAY, tokens[i+1])){
				startDate_ = getCurrentDate();
				isStartDateFound_ = true;
				input = input.replaceFirst(tokens[i] + STRING_SPACE + tokens[i+1], "").trim();
			}
		}
		return input;
	}

	private String parseStartDate(String input, DateParser dp, String[] tokens,
			int i) {
		if(!isStartDateFound_){
			input = dp.parseDateWithKeyword(tokens, i, input);
			if(dp.getDate() != null){
				startDate_ = dp.getDate();
				dp.setDateStart(startDate_);
				isStartDateFound_ = true;
			}
		}
		return input;
	}

	private String parseDashDate(String input, TimeParser tp, String[] tokens,
			int i) {
		if(!isStartTimeFound_ && !isEndTimeFound_){
			input = tp.parseDashTimeWithKeyword(tokens, i, input);
			if(tp.getStart() != null && tp.getEnd() != null){
				startTime_ = tp.getStart();
				endTime_ = tp.getEnd();
				isStartTimeFound_ = true;
				isEndTimeFound_ = true;
			}
		}
		return input;
	}

	private String parseDeadline(String input, DateParser dp, TimeParser tp,
			String[] tokens, int i) {
		if(dictionaryContains(DICTIONARY_KEYWORDS_DEADLINE, tokens[i])){
			if(!isDeadLineFound_){
				input = dp.parseDateWithKeyword(tokens, i, input);
				if(dp.getDate() != null){
					deadLine_ = dp.getDate();
					isDeadLineFound_ = true;
				}
			}

			input = parseEndTime(input, tp, tokens, i);	

			if(isNotOutOfBounds(i+1,tokens.length) && !isEndTimeFound_){
				if(dictionaryContains(DICTIONARY_KEYWORDS_TODAY, tokens[i+1])){
					endTime_ = STRING_BEFORE_MIDNIGHT;
					isEndTimeFound_ = true;
					input = input.replaceFirst(tokens[i] + STRING_SPACE + tokens[i+1], "").trim();
				}
			}
		}
		return input;
	}

	private String parseWithoutKeyword(String input, DateParser dp,
			TimeParser tp, String[] tokens, int i) {
		
		if(!isDeadLineFound_ && !isEndDateFound_ && !isStartDateFound_ && previousNotKeyWord(tokens,i,input)){
			input = dp.parseDateWithoutKeyword(tokens, i, input);
			if(dp.getDate() != null){
				startDate_ = dp.getDate();
				dp.setDateStart(startDate_);
				isStartDateFound_ = true;
			}
		}

		if(!isStartTimeFound_ && !isEndTimeFound_){
			input = tp.parseDashTimeWithoutKeyword(tokens, i, input);
			if(tp.getStart() != null && tp.getEnd() != null){
				startTime_ = tp.getStart();
				endTime_ = tp.getEnd();
				isStartTimeFound_ = true;
				isEndTimeFound_ = true;
			}
		}

		if((!isStartTimeFound_ || !isEndTimeFound_) && previousNotKeyWord(tokens,i,input)){
			input = tp.parseTimeWithoutKeyword(tokens, i, input);
			if(tp.getTime() != null){
				if(!isStartTimeFound_){
					startTime_ = tp.getTime();
					isStartTimeFound_ = true;
				} else if(!isEndTimeFound_) {
					endTime_ = tp.getTime();
					isEndTimeFound_ = true;
				}
			}
			
			if(dictionaryContains(DICTIONARY_KEYWORDS_TODAY, tokens[i]) && !isEndTimeFound_){
				endTime_ = STRING_BEFORE_MIDNIGHT;
				isEndTimeFound_ = true;
				input = input.replaceFirst(tokens[i], "").trim();
			}
		}

		if(!isStartDateFound_ && !isEndDateFound_){
			if(tokens[i].contains(STRING_DASH) || (tokens[i].contains(STRING_WAVE_DASH)) || (tokens[i].contains(STRING_TO))){
				input = dp.parseDashDateWithoutKeyword(tokens, i, input);
				if(dp.getDateStart() != null && dp.getDateEnd() != null){
					startDate_ = dp.getDateStart();
					endDate_ = dp.getDateEnd();
					isStartDateFound_ = true;
					isEndDateFound_ = true;
				}
			}
		}
		return input;
	}

	private void postParsingAdjustments() {
		
		convertDeadLineToStartDate();
		convertDateAndTime();
	}

	private void convertDateAndTime() {
		
		assignDateIfTimeFound();	
		adjustEndDateAccordingToStartDateAndTime();
		assignTimeIfOnlyDeadLineFound();
		removeDeadLineIfTimedTask();
	}

	private void removeDeadLineIfTimedTask() {
		if(allDatesFound()){
			deadLine_ = null;
		}
	}

	private void assignTimeIfOnlyDeadLineFound() {
		if(onlyDeadLineFound()){
			endTime_ = STRING_BEFORE_MIDNIGHT;
		}
	}

	private void adjustEndDateAccordingToStartDateAndTime() {
		if(isStartDateFound_ && isStartTimeFound_ && isEndTimeFound_ && !isEndDateFound_){
			if(Integer.parseInt(endTime_) < Integer.parseInt(startTime_)){
				endDate_ = getDayModifiedDate(1,startDate_);
				isEndDateFound_ = true;
			}
		}
	}

	private void assignDateIfTimeFound() {
		if(!isDeadLineFound_ && !isStartDateFound_ && !isEndDateFound_){

			String temp = null;

			if(isEndTimeFound_){
				temp = endTime_;
			}
			if(isStartTimeFound_){
				temp = startTime_;
			}

			if(isStartTimeFound_ && isEndTimeFound_){
				if(Integer.parseInt(endTime_) < Integer.parseInt(startTime_)){
					startDate_ = getModifiedDate(0);
					endDate_ = getModifiedDate(1);
					isStartDateFound_ = true;
					isEndDateFound_ = true;
				} 
			} 
			if (temp != null){
				try{
					if(Integer.parseInt(getCurrentTime()) > Integer.parseInt(temp)){
						deadLine_ = getModifiedDate(1);
						isDeadLineFound_ = true;
					}
					else{
						deadLine_ = getModifiedDate(0);
						isDeadLineFound_ = true;
					}
				}catch(Exception e){
					System.out.println("error");
				}
			}
		}
	}

	private void convertDeadLineToStartDate() {
		if(isEndDateFound_ && isDeadLineFound_ && !isStartDateFound_){
			if(validEndDate(endDate_ , deadLine_)){
				startDate_ = deadLine_;
				deadLine_ = null;
				isStartDateFound_ = true;
			} 
		}
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
		return isDeadLineFound_ && isStartDateFound_ && isEndDateFound_;
	}

	private boolean onlyDeadLineFound() {
		return isDeadLineFound_ && !isStartDateFound_ && !isEndDateFound_ && !isStartTimeFound_ && !isEndTimeFound_;
	}

	private boolean isNotOutOfBounds(int index, int length) {
		return index < length && index > 0;
	}

	private boolean validEndDate(String endDate_, String startDate_) {

		String[] endArray = endDate_.split(STRING_SLASH);
		String[] startArray = startDate_.split(STRING_SLASH);

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
