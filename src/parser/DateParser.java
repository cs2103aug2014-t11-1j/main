package parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class is used to parse the relevant tokens
 * and retrieve the date string if it fits
 * certain requirements and returns null
 * if not found.
 * 
 * * Author: smallson
 */

public class DateParser {

	/**
	 * String constants
	 */
	private static final String STRING_SPACE = " ";
	private static final String STRING_SLASH = "/";

	private static final String FORMAT_DICTIONARY_DAY = "DD ";
	private static final String FORMAT_SLASH_DATE = "SD ";
	private static final String FORMAT_DAY_FIRST_STRING = "DFS ";
	private static final String FORMAT_MONTH_FIRST_STRING = "MFS ";
	private static final String FORMAT_NEXT_DICTIONARY_DAY = "NDD ";
	private static final String FORMAT_DASH_DATE_FIRST = "DDF ";
	private static final String FORMAT_DASH_DATE_NEXT = "DDN ";
	/**
	 * String Dictionaries
	 */
	private static final String[] DICTIONARY_DAYS = { "monday", "tuesday", "wednesday", "thursday", "friday","saturday", "sunday",
		"mon", "tue","tues","wed", "thurs","fri", "sat", "sun",	"tmr", "tomorrow"};

	private static final String[] DICTIONARY_MONTHS = {"janurary","feburary","march","april","may","june","july","august","september","october","november","december",
		"jan","feb","mar","apr","jun","jul","aug","sept","sep","oct","nov","dec"};

	//private static final String[] DICTIONARY_KEY_WORDS = {};


	private String date = null;
	private String dateStart = null;
	private String dateEnd = null;

	private DateAndTimeChecker checker = DateAndTimeChecker.getInstance();

	public DateParser(){

	}

	protected String getDate() {
		return date;
	}

	protected String getDateStart() {
		return dateStart;
	}

	protected String getDateEnd() {
		return dateEnd;
	}

	protected void setDateStart(String date){
		dateStart = date;
	}

	protected String parseDateWithoutKeyword(String[] tokens, int i,String input){

		date = null;
		DateStandardizer ds = new DateStandardizer();

		if(isNotOutOfBounds(i, tokens.length)){

			if(dictionaryContains(DICTIONARY_DAYS,tokens[i])){
				date = tokens[i];
				input = input.replaceFirst(date, "").trim();
				date = ds.formatDate(FORMAT_DICTIONARY_DAY + date);
			}

			if(tokens[i].contains("/")){
				if(checker.isValidSlashDateFormat(tokens[i])){
					date = tokens[i];
					input = input.replaceFirst(date, "");
					date = ds.formatDate(FORMAT_SLASH_DATE + date);
				}
			}
		}

		if(isNotOutOfBounds(i+1, tokens.length)){
			if(dictionaryContains(DICTIONARY_MONTHS,tokens[i+1])){
				if(checker.isValidDayFirstStringDateFormat(tokens,i+1)){
					if(i+2 < tokens.length){
						try{
							if(Integer.parseInt(tokens[i+2]) > 0){
								date = tokens[i] + STRING_SPACE + tokens[i+1] + STRING_SPACE + tokens[i+2];
								input = input.replaceFirst(date, "").trim();
								date = ds.formatDate(FORMAT_DAY_FIRST_STRING + date);
							}
							else{
								date = tokens[i] + STRING_SPACE + tokens[i+1];
								input = input.replaceFirst(date, "").trim();
								date = ds.formatDate(FORMAT_DAY_FIRST_STRING + date);
							}
						} catch(Exception e){
							date = tokens[i] + STRING_SPACE + tokens[i+1];
							input = input.replaceFirst(date, "").trim();
							date = ds.formatDate(FORMAT_DAY_FIRST_STRING + date);
						}
					}
					else{
						date = tokens[i] + STRING_SPACE + tokens[i+1];
						input = input.replaceFirst(date, "").trim();
						date = ds.formatDate(FORMAT_DAY_FIRST_STRING + date);
					}
				}
			}

			if(dictionaryContains(DICTIONARY_MONTHS,tokens[i])){
				if(checker.isValidMonthFirstStringDateFormat(tokens,i)){
					if(i+2 < tokens.length){
						try{
							if(Integer.parseInt(tokens[i+2]) > 0){
								date = tokens[i] + STRING_SPACE + tokens[i+1] + STRING_SPACE + tokens[i+2];
								input = input.replaceFirst(date, "").trim();
								date = ds.formatDate(FORMAT_MONTH_FIRST_STRING + date);
							}
							else{
								date = tokens[i] + STRING_SPACE + tokens[i+1];
								input = input.replaceFirst(date, "").trim();
								date = ds.formatDate(FORMAT_MONTH_FIRST_STRING + date);
							}
						} catch(Exception e){
							date = tokens[i] + STRING_SPACE + tokens[i+1];
							input = input.replaceFirst(date, "").trim();
							date = ds.formatDate(FORMAT_MONTH_FIRST_STRING + date);
						}
					}
					else{
						date = tokens[i] + STRING_SPACE + tokens[i+1];
						input = input.replaceFirst(date, "").trim();
						date = ds.formatDate(FORMAT_MONTH_FIRST_STRING + date);
					}
				}
			}

			if(tokens[i].equalsIgnoreCase("next")){
				if(dictionaryContains(DICTIONARY_DAYS,tokens[i+1])){
					if(!tokens[i+1].equalsIgnoreCase("tmr") && !tokens[i+1].equalsIgnoreCase("tommorrow")){
						date = "next " + tokens[i+1];
						input = input.replaceFirst(date, "").trim();
						date = ds.formatDate(FORMAT_NEXT_DICTIONARY_DAY + date);
					}
				}
			}
		}

		return input;

	}

	protected String parseDashDateWithoutKeyword(String[] tokens, int i, String input) {

		DateStandardizer ds = new DateStandardizer();
		dateStart = null;
		dateEnd = null;
		String toParse = null;
		String toReplace = null;
		if(isNotOutOfBounds(i-1,tokens.length) && isNotOutOfBounds(i+2,tokens.length)){
			toParse = tokens[i-1] + tokens[i] + tokens[i+1] + tokens[i+2];
			toReplace = tokens[i-1] + STRING_SPACE + tokens[i] + STRING_SPACE + tokens[i+1] + STRING_SPACE + tokens[i+2];
		}
		else if (isNotOutOfBounds(i-1,tokens.length) && isNotOutOfBounds(i+1,tokens.length)){
			toParse = tokens[i-1] + tokens[i] + tokens[i+1];
			toReplace = tokens[i-1] + STRING_SPACE + tokens[i] + STRING_SPACE + tokens[i+1];
		} else {
			toParse = tokens[i];
			toReplace = tokens[i];
		}

		if(checker.isValidDashDateFormat(toParse.trim())){
			input = input.replaceFirst(toReplace, "");
			System.out.println(input);
			dateStart = ds.formatDate(FORMAT_DASH_DATE_FIRST + toParse.trim());
			dateEnd = ds.formatDate(FORMAT_DASH_DATE_NEXT + toParse.trim());
		}

		return input;
	}


	protected String parseDateWithKeyword(String[] tokens, int i, String input) {

		date = null;
		DateStandardizer ds = new DateStandardizer();

		if(isNotOutOfBounds(i+1, tokens.length)){

			if(dictionaryContains(DICTIONARY_DAYS,tokens[i+1])){
				date = tokens[i+1];
				input = input.replaceFirst(tokens[i] + STRING_SPACE + date, "").trim();
				date = ds.formatDate(FORMAT_DICTIONARY_DAY + date);
				if(dateStart != null){
					adjustDate();
				}
			}

			if(tokens[i+1].contains("/")){
				if(checker.isValidSlashDateFormat(tokens[i+1])){
					date = tokens[i+1];
					input = input.replaceFirst(tokens[i] + STRING_SPACE + date, "");
					date = ds.formatDate(FORMAT_SLASH_DATE + date);
				}
			}
		}

		if(isNotOutOfBounds(i+2, tokens.length)){
			if(dictionaryContains(DICTIONARY_MONTHS,tokens[i+2])){
				if(checker.isValidDayFirstStringDateFormat(tokens,i+2)){
					if(i+3 < tokens.length){
						try{
							if(Integer.parseInt(tokens[i+3]) > 0){
								date = tokens[i+1] + STRING_SPACE + tokens[i+2] + STRING_SPACE + tokens[i+3];
								input = input.replaceFirst(tokens[i] + STRING_SPACE + date, "").trim();
								date = ds.formatDate(FORMAT_DAY_FIRST_STRING + date);
							}
							else{
								date = tokens[i+1] + STRING_SPACE + tokens[i+2];
								input = input.replaceFirst(tokens[i] + STRING_SPACE + date, "").trim();
								date = ds.formatDate(FORMAT_DAY_FIRST_STRING + date);
							}
						} catch(Exception e){
							date = tokens[i+1] + STRING_SPACE + tokens[i+2];
							input = input.replaceFirst(tokens[i] + STRING_SPACE + date, "").trim();
							date = ds.formatDate(FORMAT_DAY_FIRST_STRING + date);
						}
					}
					else{
						date = tokens[i+1] + STRING_SPACE + tokens[i+2];
						input = input.replaceFirst(tokens[i] + STRING_SPACE + date, "").trim();
						date = ds.formatDate(FORMAT_DAY_FIRST_STRING + date);
					}
				}
			}

			if(dictionaryContains(DICTIONARY_MONTHS,tokens[i+1])){
				if(checker.isValidMonthFirstStringDateFormat(tokens,i+1)){
					if(i+3 < tokens.length){
						try{
							if(Integer.parseInt(tokens[i+3]) > 0){
								date = tokens[i+1] + STRING_SPACE + tokens[i+2] + STRING_SPACE + tokens[i+3];
								input = input.replaceFirst(tokens[i] + STRING_SPACE + date, "").trim();
								date = ds.formatDate(FORMAT_MONTH_FIRST_STRING + date);
							}
							else{
								date = tokens[i+1] + STRING_SPACE + tokens[i+2];
								input = input.replaceFirst(tokens[i] + STRING_SPACE + date, "").trim();
								date = ds.formatDate(FORMAT_MONTH_FIRST_STRING + date);
							}
						} catch(Exception e){
							date = tokens[i+1] + STRING_SPACE + tokens[i+2];
							input = input.replaceFirst(tokens[i] + STRING_SPACE + date, "").trim();
							date = ds.formatDate(FORMAT_MONTH_FIRST_STRING + date);
						}
					}
					else{
						date = tokens[i+1] + STRING_SPACE + tokens[i+2];
						input = input.replaceFirst(tokens[i] + STRING_SPACE + date, "").trim();
						date = ds.formatDate(FORMAT_MONTH_FIRST_STRING + date);
					}
				}
			}

			if(tokens[i+1].equalsIgnoreCase("next")){
				if(dictionaryContains(DICTIONARY_DAYS,tokens[i+2])){
					if(!tokens[i+2].equalsIgnoreCase("tmr") && !tokens[i+2].equalsIgnoreCase("tommorrow")){
						date = "next " + tokens[i+2];
						input = input.replaceFirst(tokens[i] + STRING_SPACE + date, "").trim();
						date = ds.formatDate(FORMAT_NEXT_DICTIONARY_DAY + date);
					}
				}
			}
		}

		return input;
	}

	private void adjustDate() {

		if(!validEndDate(date,dateStart)){
			try {
				date = getModifiedDate(7,date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

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

	private static String getModifiedDate(int offset, String date) throws ParseException{
		String modDate;
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd/MM/YYYY");
		Date currDate = dayFormat.parse(date);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currDate);
		calendar.add(Calendar.DATE, offset);
		modDate = dayFormat.format(calendar.getTime());

		return modDate;
	}	

}
