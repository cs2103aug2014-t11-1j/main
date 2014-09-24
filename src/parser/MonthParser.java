package parser;


/**
 * This class is parse a Month 
 * String into its respective
 * numerical representation
 * 
 * * Author: smallson
 */


public class MonthParser {
	
	private static final String[] DICTIONARY_MONTHS = {"janurary","feburary","march","april","may","june","july","august","september","october","november","december",
		"jan","feb","mar","apr","jun","jul","aug","sept","sep","oct","nov","dec"};

	private static final String[] DICTIONARY_JANURARY = { "JANURARY", "JAN" };
	private static final String[] DICTIONARY_FEBURARY = { "FEBURARY", "FEB" };
	private static final String[] DICTIONARY_MARCH = { "MARCH", "MAR" };
	private static final String[] DICTIONARY_APRIL = { "APRIL", "APR" };
	private static final String[] DICTIONARY_MAY = { "MAY" };
	private static final String[] DICTIONARY_JUNE = { "JUNE", "JUN" };
	private static final String[] DICTIONARY_JULY = { "JULY", "JUL" };
	private static final String[] DICTIONARY_AUGUST = { "AUGUST", "AUG" };
	private static final String[] DICTIONARY_SEPTEMBER = { "SEPTEMBER", "SEP", "SEPT" };
	private static final String[] DICTIONARY_OCTOBER = { "OCTOBER", "OCT" };
	private static final String[] DICTIONARY_NOVEMBER = { "NOVEMBER", "NOV" };
	private static final String[] DICTIONARY_DECEMBER = { "DECEMBER", "DEC" };
	
	private static final int NUM_JAN = 0;
	private static final int NUM_FEB = 1;
	private static final int NUM_MAR = 2;
	private static final int NUM_APR = 3;
	private static final int NUM_MAY = 4;
	private static final int NUM_JUN = 5;
	private static final int NUM_JUL = 6;
	private static final int NUM_AUG = 7;
	private static final int NUM_SEP = 8;
	private static final int NUM_OCT = 9;
	private static final int NUM_NOV = 10;
	private static final int NUM_DEC = 11;
	private static final int NUM_DEFAULT = -1;

	public int getMonthIndex(String string) {
		
		if(dictionaryContains(DICTIONARY_JANURARY,string)){
			return NUM_JAN;
		}else if(dictionaryContains(DICTIONARY_FEBURARY,string)){
			return NUM_FEB;
		}else if(dictionaryContains(DICTIONARY_MARCH,string)){
			return NUM_MAR;
		}else if(dictionaryContains(DICTIONARY_APRIL,string)){
			return NUM_APR;
		}else if(dictionaryContains(DICTIONARY_MAY,string)){
			return NUM_MAY;
		}else if(dictionaryContains(DICTIONARY_JUNE,string)){
			return NUM_JUN;
		}else if(dictionaryContains(DICTIONARY_JULY,string)){
			return NUM_JUL;
		}else if(dictionaryContains(DICTIONARY_AUGUST,string)){
			return NUM_AUG;
		}else if(dictionaryContains(DICTIONARY_SEPTEMBER,string)){
			return NUM_SEP;
		}else if(dictionaryContains(DICTIONARY_OCTOBER,string)){
			return NUM_OCT;
		}else if(dictionaryContains(DICTIONARY_NOVEMBER,string)){
			return NUM_NOV;
		}else if(dictionaryContains(DICTIONARY_DECEMBER,string)){
			return NUM_DEC;
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
