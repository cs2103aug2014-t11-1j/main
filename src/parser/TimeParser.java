package parser;

import java.util.Scanner;

public class TimeParser {
	
	/**
	 * String constants
	 */
	private final String STRING_SPACE = " ";
	/**
	 * String Dictionaries
	 */
	private static final String[] DICTIONARY_SPECIAL_TIME = {"NOON", "NN", "MIDNIGHT", "MN"};
	
	private String time = null;
	
	private DateAndTimeChecker checker = DateAndTimeChecker.getInstance();
	
	public TimeParser(){		
	}
	

	protected String parseTimeWithKeyword(String[] tokens, int i, String input) {
		
		if(isNotOutOfBounds(i+1, tokens.length)){

			if(dictionaryContains(DICTIONARY_SPECIAL_TIME, tokens[i+1])){
				time = tokens[i+1];
				input = input.replace(tokens[i] + STRING_SPACE + time, "");
			}
			
			if(tokens[i+1].contains("pm")|| tokens[i+1].contains("am") || tokens[i+1].contains("nn") || tokens[i+1].contains("mn")){
				if(checker.isValidDefaultTimeFormat(tokens[i+1])){
					time = tokens[i+1];
					input = input.replace(tokens[i] + STRING_SPACE + time, "");
				}
			}
			
			if(tokens[i+1].length() == 4){
				if(checker.isValidMilitaryTimeFormat(tokens[i+1])){
					time = tokens[i+1];
					input = input.replace(tokens[i] + STRING_SPACE + time, "");
				}
			}

		}
		
		return input;
	}


	protected String getTime() {
		return time;
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
