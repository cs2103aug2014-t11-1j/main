package parser;

import java.util.Scanner;


/**
 * This class is used to parse the relevant tokens
 * and retrieve the time_ string if it fits
 * certain requirements and returns null
 * if not found.
 * 
 * *Author: smallson
 */
//@author A0116211B

public class TimeParser {
	
	/**
	 * String constants
	 */
	private static final String STRING_SPACE = " ";
	private static final String STRING_COLON = ":";
	
	private static final String FORMAT_SPECIAL = "SPT ";
	private static final String FORMAT_DEFAULT = "DT ";
	private static final String FORMAT_MILITARY = "MT ";
	private static final String FORMAT_DASH_TIME_FIRST = "DTF ";
	private static final String FORMAT_DASH_TIME_NEXT = "DTN ";
	
	/**
	 * String Dictionaries
	 */
	private static final String[] DICTIONARY_SPECIAL_TIME = {"NOON", "NN",
															"MIDNIGHT", "MN",
															"MORNING", "MORN",
															"AFTERNOON", "AFTN",
															"TONIGHT", "NIGHTTIME", "NITE", "TONITE"};
	
	private static final String DASH_TIME_REGEX = "(1[012]|[1-9])((:|.)[0-5][0-9])?(\\s)?(-|to)(\\s)?(1[012]|[1-9])((:|.)[0-5][0-9])?(\\s)?(?i)(am|pm|mn|nn)";
	
	private String time_ = null;
	private String start_ = null;
	private String end_ = null;
	
	private DateAndTimeChecker checker = DateAndTimeChecker.getInstance();

	public TimeParser(){		
	}

	protected String parseTimeWithoutKeyword(String[] tokens, int i, String input){

		time_ = null;
		TimeStandardizer ts = new TimeStandardizer();

		if(isNotOutOfBounds(i, tokens.length)){

			if(dictionaryContains(DICTIONARY_SPECIAL_TIME, tokens[i])){
				time_ = tokens[i];
				input = input.replaceFirst(time_, "").trim();
				time_ = ts.formatTime(FORMAT_SPECIAL + time_);
			}

			if(tokens[i].contains("pm")|| tokens[i].contains("am") || tokens[i].contains("nn") || tokens[i].contains("mn")){
				if(checker.isValidDefaultTimeFormat(tokens[i])){
					time_ = tokens[i];
					input = input.replaceFirst(time_, "").trim();
					time_ = ts.formatTime(FORMAT_DEFAULT + time_);
				}
			}
			
			if(tokens[i].matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")){
				if(checker.isValidMilitaryTimeFormat(tokens[i])){
					time_ = tokens[i];
					input = input.replaceFirst(time_, "").trim();
					time_ = ts.formatTime(FORMAT_MILITARY + time_);
				}

			}

//			if(tokens[i].replaceFirst(STRING_COLON, "").length() == 4){
//				if(checker.isValidMilitaryTimeFormat(tokens[i])){
//					time_ = tokens[i];
//					input = input.replaceFirst(time_, "").trim();
//					time_ = ts.formatTime(FORMAT_MILITARY + time_);
//				}
//			}

		}
		return input;
	}
	
	protected String parseDashTimeWithoutKeyword(String[] tokens, int i, String input){
		start_ = null;
		end_ = null;
		String toReplace = null;
		TimeStandardizer ts = new TimeStandardizer();
		
		if(isNotOutOfBounds(i+2, tokens.length)){
			String temp = tokens[i] + tokens[i+1] + tokens[i+2];
			if(temp.matches(DASH_TIME_REGEX)){
				toReplace = tokens[i] + STRING_SPACE + tokens[i+1] + STRING_SPACE + tokens[i+2];
				start_ = ts.formatTime(FORMAT_DASH_TIME_FIRST + temp);
				end_ = ts.formatTime(FORMAT_DASH_TIME_NEXT + temp);
				input = input.replaceFirst(toReplace, "");
			}
		}
		
		if(isNotOutOfBounds(i, tokens.length)){
			if(tokens[i].matches(DASH_TIME_REGEX)){
				start_ = ts.formatTime(FORMAT_DASH_TIME_FIRST + tokens[i]);
				end_ = ts.formatTime(FORMAT_DASH_TIME_NEXT + tokens[i]);
				input = input.replaceFirst(tokens[i], "");
			}
		}
		
		return input;
	}

	protected String parseTimeWithKeyword(String[] tokens, int i, String input) {
		
		time_ = null;
		TimeStandardizer ts = new TimeStandardizer();
		
		if(isNotOutOfBounds(i+1, tokens.length)){

			if(dictionaryContains(DICTIONARY_SPECIAL_TIME, tokens[i+1])){
				time_ = tokens[i+1];
				input = input.replaceFirst(tokens[i] + STRING_SPACE + time_, "").trim();
				time_ = ts.formatTime(FORMAT_SPECIAL + time_);
			}
			
			if(tokens[i+1].contains("pm")|| tokens[i+1].contains("am") || tokens[i+1].contains("nn") || tokens[i+1].contains("mn")){
				if(checker.isValidDefaultTimeFormat(tokens[i+1])){
					time_ = tokens[i+1];
					input = input.replaceFirst(tokens[i] + STRING_SPACE + time_, "").trim();
					time_ = ts.formatTime(FORMAT_DEFAULT + time_);
				}
			}
			
			if(tokens[i+1].matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")){
				if(checker.isValidMilitaryTimeFormat(tokens[i+1])){
					time_ = tokens[i+1];
					input = input.replaceFirst(tokens[i] + STRING_SPACE + time_, "").trim();
					time_ = ts.formatTime(FORMAT_MILITARY + time_);
				}
			}
			
//			if(tokens[i+1].replaceFirst(STRING_COLON, "").length() == 4){
//				if(checker.isValidMilitaryTimeFormat(tokens[i+1])){
//					time_ = tokens[i+1];
//					input = input.replaceFirst(tokens[i] + STRING_SPACE + time_, "").trim();
//					time_ = ts.formatTime(FORMAT_MILITARY + time_);
//				}
//			}
		}
		
		return input;
	}
	
	protected String parseDashTimeWithKeyword(String[] tokens, int i, String input){
		start_ = null;
		end_ = null;
		String toReplace = null;
		TimeStandardizer ts = new TimeStandardizer();
		
		if(isNotOutOfBounds(i+3, tokens.length)){
			String temp = tokens[i+1] + tokens[i+2] + tokens[i+3];
			if(temp.matches(DASH_TIME_REGEX)){
				toReplace = tokens[i] + STRING_SPACE + tokens[i+1] + STRING_SPACE + tokens[i+2] + STRING_SPACE + tokens[i+3];
				start_ = ts.formatTime(FORMAT_DASH_TIME_FIRST + temp);
				end_ = ts.formatTime(FORMAT_DASH_TIME_NEXT + temp);
				input = input.replaceFirst(toReplace, "");
			}
		}
		
		if(isNotOutOfBounds(i+1, tokens.length)){
			if(tokens[i+1].matches(DASH_TIME_REGEX)){
				start_ = ts.formatTime(FORMAT_DASH_TIME_FIRST + tokens[i]);
				end_ = ts.formatTime(FORMAT_DASH_TIME_NEXT + tokens[i]);
				input = input.replaceFirst(tokens[i] + STRING_SPACE + tokens[i+1], "");
			}
		}
		
		return input;
	}


	protected String getTime() {
		return time_;
	}
	
	protected String getStart(){
		return start_;
	}
	
	protected String getEnd(){
		return end_;
	}
	
	private boolean isNotOutOfBounds(int index, int length) {
		return index < length && index >= 0;
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
