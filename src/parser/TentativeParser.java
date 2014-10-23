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

	private final String PERIOD_SEPERATOR = "|";
	private final String START_END_SEPERATOR = "-";

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
		TentativeTask task = new TentativeTask();
		task.setEvent(inputArray[0]);

		if(inputArray.length > 2){
			task = parseDateAndTime(input,task);
		} else {
			System.out.println("No colon");
		}

		return task;
	}


	private TentativeTask parseDateAndTime(String input, TentativeTask task) {

		String[] periods = input.split(PERIOD_SEPERATOR);

		for (int i = 0; i < periods.length; i++) {
			String[] period = periods[i].split(START_END_SEPERATOR);

			if(period.length != 2){
				continue;
			} else {
				task = parsePeriod(period,task);
			}
		}
		return task;
	}

	private TentativeTask parsePeriod(String[] period, TentativeTask task) {

		TimeParser timeParser = new TimeParser();
		DateParser dateParser = new DateParser();
		TimePeriod timePeriod = new TimePeriod();

		String[] startWords = period[0].trim().split(STRING_SPACE);
		String[] endWords = period[1].trim().split(STRING_SPACE);

		for (int i = 0; i < startWords.length; i++) {

			timeParser.parseTimeWithoutKeyword(startWords, i, period[0]);
			if(timeParser.getTime() != null){
				//set start time
			}

			dateParser.parseDateWithoutKeyword(startWords, i, period[0]);
			if(dateParser.getDate() != null){
				//set start date
			}
		}

		for (int i = 0; i < endWords.length; i++) {

			timeParser.parseTimeWithoutKeyword(startWords, i, period[1]);
			if(timeParser.getTime() != null){
				//set end time
			}

			dateParser.parseDateWithoutKeyword(startWords, i, period[1]);
			if(dateParser.getDate() != null){
				//set end date
			}
		}
		
		return task;
	}

	private String replaceCommandWord(String input) {
		return input.replaceFirst(input.split(STRING_SPACE)[0], "").trim();
	}
}
