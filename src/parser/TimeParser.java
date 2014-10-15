package parser;

import java.util.Scanner;


/**
 * This class is used to parse the relevant tokens
 * and retrieve the time string if it fits
 * certain requirements and returns null
 * if not found.
 * 
 * *Author: smallson
 */

public class TimeParser {
	
	/**
	 * String constants
	 */
	private static final String STRING_SPACE = " ";
	private static final String STRING_COLON = ":";
	
	private static final String FORMAT_SPECIAL = "SPT ";
	private static final String FORMAT_DEFAULT = "DT ";
	private static final String FORMAT_MILITARY = "MT ";
	
	/**
	 * String Dictionaries
	 */
	private static final String[] DICTIONARY_SPECIAL_TIME = {"NOON", "NN",
															"MIDNIGHT", "MN",
															"MORNING", "MORN",
															"AFTERNOON", "AFTN",
															"TONIGHT", "NIGHTTIME", "NITE", "TONITE"};
	
	private String time = null;
	private String start = null;
	private String end = null;
	
	private DateAndTimeChecker checker = DateAndTimeChecker.getInstance();

	public TimeParser(){		
	}

	protected String parseTimeWithoutKeyword(String[] tokens, int i, String input){

		time = null;
		TimeStandardizer ts = new TimeStandardizer();

		if(isNotOutOfBounds(i, tokens.length)){

			if(dictionaryContains(DICTIONARY_SPECIAL_TIME, tokens[i])){
				time = tokens[i];
				input = input.replaceFirst(time, "").trim();
				time = ts.formatTime(FORMAT_SPECIAL + time);
			}

			if(tokens[i].contains("pm")|| tokens[i].contains("am") || tokens[i].contains("nn") || tokens[i].contains("mn")){
				if(checker.isValidDefaultTimeFormat(tokens[i])){
					time = tokens[i];
					input = input.replaceFirst(time, "").trim();
					time = ts.formatTime(FORMAT_DEFAULT + time);
				}
			}
			

//			if(tokens[i].replaceFirst(STRING_COLON, "").length() == 4){
//				if(checker.isValidMilitaryTimeFormat(tokens[i])){
//					time = tokens[i];
//					input = input.replaceFirst(time, "").trim();
//					time = ts.formatTime(FORMAT_MILITARY + time);
//				}
//			}

		}
		return input;
	}
	
	protected String parseDashTimeWithoutKeyword(String[] tokens, int i, String input){
		start = null;
		end = null;
		String toReplace = null;
		
		if(isNotOutOfBounds(i+2, tokens.length)){
			String temp = tokens[i] + tokens[i+1] + tokens[i+2];
			if(temp.matches("(1[012]|[1-9])(:|.)?[0-5]?[0-9]?(\\s)?(to|-)(\\s)?(1[012]|[1-9])(:|.|to)?[0-5]?[0-9]?(\\s)?(?i)(am|pm|mn|nn)")){
				toReplace = tokens[i] + STRING_SPACE + tokens[i+1] + STRING_SPACE + tokens[i+2];
				System.out.println(true);
				input = input.replaceFirst(toReplace, "");
			}
		}
		
		if(isNotOutOfBounds(i, tokens.length)){
			if(tokens[i].matches("(1[012]|[1-9])(:|.)?[0-5]?[0-9]?(\\s)?(to|-)(\\s)?(1[012]|[1-9])(:|.|to)?[0-5]?[0-9]?(\\s)?(?i)(am|pm|mn|nn)")){
				System.out.println(true);
				input = input.replaceFirst(toReplace, "");
			}
		}
		
		return input;
	}

	protected String parseTimeWithKeyword(String[] tokens, int i, String input) {
		
		time = null;
		TimeStandardizer ts = new TimeStandardizer();
		
		if(isNotOutOfBounds(i+1, tokens.length)){

			if(dictionaryContains(DICTIONARY_SPECIAL_TIME, tokens[i+1])){
				time = tokens[i+1];
				input = input.replaceFirst(tokens[i] + STRING_SPACE + time, "").trim();
				time = ts.formatTime(FORMAT_SPECIAL + time);
			}
			
			if(tokens[i+1].contains("pm")|| tokens[i+1].contains("am") || tokens[i+1].contains("nn") || tokens[i+1].contains("mn")){
				if(checker.isValidDefaultTimeFormat(tokens[i+1])){
					time = tokens[i+1];
					input = input.replaceFirst(tokens[i] + STRING_SPACE + time, "").trim();
					time = ts.formatTime(FORMAT_DEFAULT + time);
				}
			}
			
			if(tokens[i+1].replaceFirst(STRING_COLON, "").length() == 4){
				if(checker.isValidMilitaryTimeFormat(tokens[i+1])){
					time = tokens[i+1];
					input = input.replaceFirst(tokens[i] + STRING_SPACE + time, "").trim();
					time = ts.formatTime(FORMAT_MILITARY + time);
				}
			}
		}
		
		return input;
	}


	protected String getTime() {
		return time;
	}
	
	protected String getStart(){
		return start;
	}
	
	protected String getEnd(){
		return end;
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
