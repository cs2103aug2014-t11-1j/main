package parser;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {
	
	/**
	 * String constants
	 */
	private final String STRING_SPACE = " ";
	
	/**
	 * String Dictionaries
	 */
	private static final String[] DICTIONARY_DAYS = { "monday", "tuesday", "wednesday", "thursday", "friday","saturday", "sunday",
		"mon", "tue","tues","wed", "thurs","fri", "sat","sun",	"tmr", "tommorrow"};

	private static final String[] DICTIONARY_MONTHS = {"janurary","feburary","march","april","may","june","july","august","september","october","november","december",
		"jan","feb","mar","apr","jun","jul","aug","sept","sep","oct","nov","dec"};

	private String date = null;

	private DateAndTimeChecker checker = DateAndTimeChecker.getInstance();

	public DateParser(){		
	}
	
	protected String parseDateWithoutKeyword(String[] tokens, int i,String input){
		
		date = null;
		
		if(isNotOutOfBounds(i, tokens.length)){

			if(dictionaryContains(DICTIONARY_DAYS,tokens[i])){
				date = tokens[i];
				input = input.replaceFirst(date, "").trim();
			}

			if(tokens[i].contains("/")){
				if(checker.isValidSlashDateFormat(tokens[i])){
					date = tokens[i];
					input = input.replaceFirst(date, "");
				}
			}
		}
		
		if(isNotOutOfBounds(i+1, tokens.length)){
			if(dictionaryContains(DICTIONARY_MONTHS,tokens[i+1])){
				if(checker.isValidDayFirstStringDateFormat(tokens,i+1)){
					if(i+2 < tokens.length){
						try{
							if(Integer.parseInt(tokens[i+2]) > 0){
								date = tokens[i] + " " + tokens[i+1] + " " + tokens[i+2];
								input = input.replaceFirst(date, "").trim();
							}
							else{
								date = tokens[i] + " " + tokens[i+1];
								input = input.replaceFirst(date, "").trim();
							}
						} catch(Exception e){
							date = tokens[i] + " " + tokens[i+1];
							input = input.replaceFirst(date, "").trim();
						}
					}
					else{
						date = tokens[i] + " " + tokens[i+1];
						input = input.replaceFirst(date, "").trim();
					}
				}
			}

			if(dictionaryContains(DICTIONARY_MONTHS,tokens[i])){
				if(checker.isValidMonthFirstStringDateFormat(tokens,i)){
					if(i+2 < tokens.length){
						try{
							if(Integer.parseInt(tokens[i+2]) > 0){
								date = tokens[i] + " " + tokens[i+1] + " " + tokens[i+2];
								input = input.replaceFirst(date, "").trim();
							}
							else{
								date = tokens[i] + " " + tokens[i+1];
								input = input.replaceFirst(date, "").trim();
							}
						} catch(Exception e){
							date = tokens[i] + " " + tokens[i+1];
							input = input.replaceFirst(date, "").trim();
						}
					}
					else{
						date = tokens[i] + " " + tokens[i+1];
						input = input.replaceFirst(date, "").trim();
					}
				}
			}

			if(tokens[i].equalsIgnoreCase("next")){
				if(dictionaryContains(DICTIONARY_DAYS,tokens[i+1])){
					if(!tokens[i+1].equalsIgnoreCase("tmr") && !tokens[i+1].equalsIgnoreCase("tommorrow")){
						date = "next " + tokens[i+1];
						input = input.replaceFirst(date, "").trim();
					}
				}
			}
		}
		
		return input;
		
	}
	
	protected String parseDateWithKeyword(String[] tokens, int i, String input) {
		
		date = null;
		
		if(isNotOutOfBounds(i+1, tokens.length)){

			if(dictionaryContains(DICTIONARY_DAYS,tokens[i+1])){
				date = tokens[i+1];
				input = input.replaceFirst(tokens[i] + STRING_SPACE + date, "").trim();
			}

			if(tokens[i+1].contains("/")){
				if(checker.isValidSlashDateFormat(tokens[i+1])){
					date = tokens[i+1];
					input = input.replaceFirst(tokens[i] + STRING_SPACE + date, "");
				}
			}
		}
		
		if(isNotOutOfBounds(i+2, tokens.length)){
			if(dictionaryContains(DICTIONARY_MONTHS,tokens[i+2])){
				if(checker.isValidDayFirstStringDateFormat(tokens,i+2)){
					if(i+3 < tokens.length){
						try{
							if(Integer.parseInt(tokens[i+3]) > 0){
								date = tokens[i+1] + " " + tokens[i+2] + " " + tokens[i+3];
								input = input.replaceFirst(tokens[i] + STRING_SPACE + date, "").trim();
							}
							else{
								date = tokens[i+1] + " " + tokens[i+2];
								input = input.replaceFirst(tokens[i] + STRING_SPACE + date, "").trim();
							}
						} catch(Exception e){
							date = tokens[i+1] + " " + tokens[i+2];
							input = input.replaceFirst(tokens[i] + STRING_SPACE + date, "").trim();
						}
					}
					else{
						date = tokens[i+1] + " " + tokens[i+2];
						input = input.replaceFirst(tokens[i] + STRING_SPACE + date, "").trim();
					}
				}
			}

			if(dictionaryContains(DICTIONARY_MONTHS,tokens[i+1])){
				if(checker.isValidMonthFirstStringDateFormat(tokens,i+1)){
					if(i+3 < tokens.length){
						try{
							if(Integer.parseInt(tokens[i+3]) > 0){
								date = tokens[i+1] + " " + tokens[i+2] + " " + tokens[i+3];
								input = input.replaceFirst(tokens[i] + STRING_SPACE + date, "").trim();
							}
							else{
								date = tokens[i+1] + " " + tokens[i+2];
								input = input.replaceFirst(tokens[i] + STRING_SPACE + date, "").trim();
							}
						} catch(Exception e){
							date = tokens[i+1] + " " + tokens[i+2];
							input = input.replaceFirst(tokens[i] + STRING_SPACE + date, "").trim();
						}
					}
					else{
						date = tokens[i+1] + " " + tokens[i+2];
						input = input.replaceFirst(tokens[i] + STRING_SPACE + date, "").trim();
					}
				}
			}

			if(tokens[i+1].equalsIgnoreCase("next")){
				if(dictionaryContains(DICTIONARY_DAYS,tokens[i+2])){
					if(!tokens[i+2].equalsIgnoreCase("tmr") && !tokens[i+2].equalsIgnoreCase("tommorrow")){
						date = "next " + tokens[i+2];
						input = input.replaceFirst(tokens[i] + STRING_SPACE + date, "").trim();
					}
				}
			}
		}
		
		return input;
	}

	//returns the date
	protected String getDate() {
		return date;
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
	
	private String getCurrentDate(){
		return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	}
	
}
