package parser;

/**
 * This class is parse a Day 
 * String into its respective
 * numerical representation
 * 
 * * Author: smallson
 */

public class DayParser {
	
	private static final String[] DICTIONARY_DAYS = { "monday", "tuesday", "wednesday", "thursday", "friday","saturday", "sunday",
		"mon", "tue","tues","wed", "thurs","fri", "sat","sun",	"tmr", "tommorrow" };
	
	private static final String[] DICTIONARY_SUNDAY = { "SUNDAY", "SUN" };
	private static final String[] DICTIONARY_MONDAY = { "MONDAY", "MON" };
	private static final String[] DICTIONARY_TUESDAY = { "TUESDAY", "TUE", "TUES" };
	private static final String[] DICTIONARY_WEDNESDAY = { "WEDNESDAY", "WED" };
	private static final String[] DICTIONARY_THURSDAY = { "THURSDAY", "THUR", "THURS" };
	private static final String[] DICTIONARY_FRIDAY = { "FRIDAY", "FRI" };
	private static final String[] DICTIONARY_SATURDAY = { "SATURDAY", "SAT" };
	
	private static final int NUM_MONDAY = 1;
	private static final int NUM_TUESDAY = 2;
	private static final int NUM_WEDNESDAY = 3;
	private static final int NUM_THURSDAY = 4;
	private static final int NUM_FRIDAY = 5;
	private static final int NUM_SATURDAY = 6;
	private static final int NUM_SUNDAY = 7;
	private static final int NUM_DEFAULT = 0;

	public int getDayIndex(String date) {
		
		if(dictionaryContains(DICTIONARY_SUNDAY,date)){
			return NUM_SUNDAY;
		}else if(dictionaryContains(DICTIONARY_MONDAY,date)){
			return NUM_MONDAY;
		}else if(dictionaryContains(DICTIONARY_TUESDAY,date)){
			return NUM_TUESDAY;
		}else if(dictionaryContains(DICTIONARY_WEDNESDAY,date)){
			return NUM_WEDNESDAY;
		}else if(dictionaryContains(DICTIONARY_THURSDAY,date)){
			return NUM_THURSDAY;
		}else if(dictionaryContains(DICTIONARY_FRIDAY,date)){
			return NUM_FRIDAY;
		}else if(dictionaryContains(DICTIONARY_SATURDAY,date)){
			return NUM_SATURDAY;
		} 
			return NUM_DEFAULT;
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
