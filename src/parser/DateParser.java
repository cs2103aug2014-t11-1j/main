package parser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 * This class is used to parse the relevant tokens
 * and retrieve the date_ string if it fits
 * certain requirements and returns null
 * if not found.
 * 
 * * Author: smallson
 */
//@author A0116211B

public class DateParser {

	/**
	 * String constants
	 */
	private static final String STRING_SPACE = " ";
	private static final String STRING_SLASH = "/";
	private static final String STRING_NEXT = "next";

	/**
	 * Tags
	 */
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


	private String date_ = null;
	private String dateStart_ = null;
	private String dateEnd_ = null;

	private DateAndTimeChecker checker = DateAndTimeChecker.getInstance();

	public DateParser(){

	}

	protected String getDate() {
		return date_;
	}

	protected String getDateStart() {
		return dateStart_;
	}

	protected String getDateEnd() {
		return dateEnd_;
	}

	protected void setDateStart(String date_){
		dateStart_ = date_;
	}

	protected String parseDateWithoutKeyword(String[] tokens, int i,String input){

		date_ = null;
		DateStandardizer ds = new DateStandardizer();

		input = checkInputsWithoutKeywords(tokens, i, input, ds);
		
		return input;
	}

	private String checkInputsWithoutKeywords(String[] tokens, int i,
			String input, DateStandardizer ds) {
		
		if(isNotOutOfBounds(i, tokens.length)){
			input = checkDictionaryDayWithoutKeyword(tokens, i, input, ds);
			input = checkSlashDateWithoutKeyword(tokens, i, input, ds);
		}

		input = checkMonthStringWithoutKeyword(tokens, i, input, ds);

		if(isNotOutOfBounds(i+1, tokens.length)){
			input = checkDayFirstStringWithoutKeyword(tokens, i, input, ds);
		}

		if(dictionaryEquals(DICTIONARY_MONTHS,tokens[i])){
			input = checkMonthFirstStringWithoutKeyword(tokens, i, input, ds);
		}

		if(tokens[i].equalsIgnoreCase(STRING_NEXT) && isNotOutOfBounds(i+1,tokens.length)){
			input = checkNextDictionaryDayWithoutKeyword(tokens, i, input, ds);
		}
		return input;
	}

	private String checkNextDictionaryDayWithoutKeyword(String[] tokens, int i,
			String input, DateStandardizer ds) {
		if(dictionaryEquals(DICTIONARY_DAYS,tokens[i+1])){
			if(!tokens[i+1].equalsIgnoreCase("tmr") && !tokens[i+1].equalsIgnoreCase("tommorrow")){
				date_ = "next " + tokens[i+1];
				input = input.replaceFirst(date_, "").trim();
				date_ = ds.formatDate(FORMAT_NEXT_DICTIONARY_DAY + date_);
				if(dateStart_ != null){
					adjustDayDate();
				}
			}
		}
		return input;
	}

	private String checkMonthFirstStringWithoutKeyword(String[] tokens, int i,
			String input, DateStandardizer ds) {
		if(checker.isValidMonthFirstStringDateFormat(tokens,i)){
			input = assignMonthFirstStringWithoutKeyword(tokens, i, input, ds);
		}
		return input;
	}

	private String assignMonthFirstStringWithoutKeyword(String[] tokens, int i,
			String input, DateStandardizer ds) {
		if(i+2 < tokens.length){
			try{
				if(Integer.parseInt(tokens[i+2]) > 0){
					date_ = tokens[i] + STRING_SPACE + tokens[i+1] + STRING_SPACE + tokens[i+2];
					input = input.replaceFirst(date_, "").trim();
					date_ = ds.formatDate(FORMAT_MONTH_FIRST_STRING + date_);
				}
				else{
					date_ = tokens[i] + STRING_SPACE + tokens[i+1];
					input = input.replaceFirst(date_, "").trim();
					date_ = ds.formatDate(FORMAT_MONTH_FIRST_STRING + date_);
				}
			} catch(Exception e){
				date_ = tokens[i] + STRING_SPACE + tokens[i+1];
				input = input.replaceFirst(date_, "").trim();
				date_ = ds.formatDate(FORMAT_MONTH_FIRST_STRING + date_);
			}
		}
		else {
			date_ = tokens[i] + STRING_SPACE + tokens[i+1];
			input = input.replaceFirst(date_, "").trim();
			date_ = ds.formatDate(FORMAT_MONTH_FIRST_STRING + date_);
		}

		if(dateStart_ != null){
			adjustDate();
		}
		return input;
	}

	private String checkDayFirstStringWithoutKeyword(String[] tokens, int i,
			String input, DateStandardizer ds) {
		if(dictionaryContains(DICTIONARY_MONTHS,tokens[i+1])){
			if(checker.isValidDayFirstStringDateFormat(tokens,i+1)){
				input = assignDayFirstStringWithoutKeyword(tokens, i, input, ds);
			}
		}
		return input;
	}

	private String assignDayFirstStringWithoutKeyword(String[] tokens, int i,
			String input, DateStandardizer ds) {
		
		if(i+2 < tokens.length){
			try{
				if(Integer.parseInt(tokens[i+2]) > 0){
					date_ = tokens[i] + STRING_SPACE + tokens[i+1] + STRING_SPACE + tokens[i+2];
					input = input.replaceFirst(date_, "").trim();
					date_ = ds.formatDate(FORMAT_DAY_FIRST_STRING + date_);
				}
				else{
					date_ = tokens[i] + STRING_SPACE + tokens[i+1];
					input = input.replaceFirst(date_, "").trim();
					date_ = ds.formatDate(FORMAT_DAY_FIRST_STRING + date_);
				}
			} catch(Exception e){
				date_ = tokens[i] + STRING_SPACE + tokens[i+1];
				input = input.replaceFirst(date_, "").trim();
				date_ = ds.formatDate(FORMAT_DAY_FIRST_STRING + date_);
			}
		}
		else{
			date_ = tokens[i] + STRING_SPACE + tokens[i+1];
			input = input.replaceFirst(date_, "").trim();
			date_ = ds.formatDate(FORMAT_DAY_FIRST_STRING + date_);
		}

		if(dateStart_ != null){
			adjustDate();
		}
		return input;
	}

	private String checkMonthStringWithoutKeyword(String[] tokens, int i,
			String input, DateStandardizer ds) {
		if(dictionaryContains(DICTIONARY_MONTHS,tokens[i])){
			
			String temp = tokens[i].replaceAll("(?<=[A-Za-z])(?=[0-9])|(?<=[0-9])(?=[A-Za-z])", " " ).trim();
			String[] dateArray = temp.split(STRING_SPACE);
			if(checker.isValidDayFirstStringDateFormat(dateArray, 1)){
				input = input.replaceFirst(tokens[i], "");
				date_ = ds.formatDate(FORMAT_DAY_FIRST_STRING + temp);
				if(dateStart_ != null){
					adjustDate();
				}
			}
			
			if(checker.isValidMonthFirstStringDateFormat(dateArray, 0)){
				input = input.replaceFirst(tokens[i], "");
				date_ = ds.formatDate(FORMAT_MONTH_FIRST_STRING + temp);
				if(dateStart_ != null){
					adjustDate();
				}
			}
		}
		return input;
	}

	private String checkSlashDateWithoutKeyword(String[] tokens, int i,
			String input, DateStandardizer ds) {
		if(tokens[i].contains("/")){
			if(checker.isValidSlashDateFormat(tokens[i])){
				date_ = tokens[i];
				input = input.replaceFirst(date_, "");
				date_ = ds.formatDate(FORMAT_SLASH_DATE + date_);
				if(dateStart_ != null){
					adjustDate();
				}
			}
		}
		return input;
	}

	private String checkDictionaryDayWithoutKeyword(String[] tokens, int i,
			String input, DateStandardizer ds) {
		if(dictionaryEquals(DICTIONARY_DAYS,tokens[i])){
			date_ = tokens[i];
			input = input.replaceFirst(date_, "").trim();
			date_ = ds.formatDate(FORMAT_DICTIONARY_DAY + date_);
			if(dateStart_ != null){
				adjustDayDate();
			}
		}
		return input;
	}

	protected String parseDashDateWithoutKeyword(String[] tokens, int i, String input) {

		DateStandardizer ds = new DateStandardizer();
		dateStart_ = null;
		dateEnd_ = null;
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
			dateStart_ = ds.formatDate(FORMAT_DASH_DATE_FIRST + toParse.trim());
			dateEnd_ = ds.formatDate(FORMAT_DASH_DATE_NEXT + toParse.trim());
		}

		return input;
	}


	protected String parseDateWithKeyword(String[] tokens, int i, String input) {

		date_ = null;
		DateStandardizer ds = new DateStandardizer();
		String temp = "";

		input = checkInputsWithKeywords(tokens, i, input, ds);

		return input;
	}

	private String checkInputsWithKeywords(String[] tokens, int i,
			String input, DateStandardizer ds) {
		if(isNotOutOfBounds(i+1, tokens.length)){

			if(dictionaryEquals(DICTIONARY_DAYS,tokens[i+1])){
				input = checkDictionaryDayWithKeyword(tokens, i, input, ds);
			}

			if(tokens[i+1].contains("/")){
				input = checkSlashDateFormatWithKeyword(tokens, i, input, ds);
			}

			if(dictionaryContains(DICTIONARY_MONTHS,tokens[i+1])){				
				input = checkMonthStringWithKeyword(tokens, i, input, ds);
			}
		}

		if(isNotOutOfBounds(i+2, tokens.length)){
			
			input = checkDayFirstStringAndMonthFirstStringWithKeyword(tokens,
					i, input, ds);

			if(tokens[i+1].equalsIgnoreCase(STRING_NEXT)){
				input = checkNextDictionaryDayWithKeyword(tokens, i, input, ds);
			}
		}
		return input;
	}

	private String checkNextDictionaryDayWithKeyword(String[] tokens, int i,
			String input, DateStandardizer ds) {
		if(dictionaryEquals(DICTIONARY_DAYS,tokens[i+2])){
			if(!tokens[i+2].equalsIgnoreCase("tmr") && !tokens[i+2].equalsIgnoreCase("tommorrow")){
				date_ = "next " + tokens[i+2];
				input = input.replaceFirst(tokens[i] + STRING_SPACE + date_, "").trim();
				date_ = ds.formatDate(FORMAT_NEXT_DICTIONARY_DAY + date_);
				if(dateStart_ != null){
					adjustDayDate();
				}
			}
		}
		return input;
	}

	private String checkDayFirstStringAndMonthFirstStringWithKeyword(
			String[] tokens, int i, String input, DateStandardizer ds) {
		if(dictionaryEquals(DICTIONARY_MONTHS,tokens[i+2])){
			input = checkDayFirstStringWithKeyword(tokens, i, input, ds);
		}

		if(dictionaryEquals(DICTIONARY_MONTHS,tokens[i+1])){
			input = checkMonthFirstStringWithKeyword(tokens, i, input, ds);
		}
		return input;
	}

	private String checkMonthFirstStringWithKeyword(String[] tokens, int i,
			String input, DateStandardizer ds) {
		if(checker.isValidMonthFirstStringDateFormat(tokens,i+1)){
			input = assignMonthFirstStringWithKeyword(tokens, i, input, ds);
		}
		return input;
	}

	private String assignMonthFirstStringWithKeyword(String[] tokens, int i,
			String input, DateStandardizer ds) {
		if(i+3 < tokens.length){
			try{
				if(Integer.parseInt(tokens[i+3]) > 0){
					date_ = tokens[i+1] + STRING_SPACE + tokens[i+2] + STRING_SPACE + tokens[i+3];
					input = input.replaceFirst(tokens[i] + STRING_SPACE + date_, "").trim();
					date_ = ds.formatDate(FORMAT_MONTH_FIRST_STRING + date_);
				}
				else{
					date_ = tokens[i+1] + STRING_SPACE + tokens[i+2];
					input = input.replaceFirst(tokens[i] + STRING_SPACE + date_, "").trim();
					date_ = ds.formatDate(FORMAT_MONTH_FIRST_STRING + date_);
				}
			} catch(Exception e){
				date_ = tokens[i+1] + STRING_SPACE + tokens[i+2];
				input = input.replaceFirst(tokens[i] + STRING_SPACE + date_, "").trim();
				date_ = ds.formatDate(FORMAT_MONTH_FIRST_STRING + date_);
			}
		}
		else{
			date_ = tokens[i+1] + STRING_SPACE + tokens[i+2];
			input = input.replaceFirst(tokens[i] + STRING_SPACE + date_, "").trim();
			date_ = ds.formatDate(FORMAT_MONTH_FIRST_STRING + date_);
		}
		if(dateStart_ != null){
			adjustDate();
		}
		return input;
	}

	private String checkDayFirstStringWithKeyword(String[] tokens, int i,
			String input, DateStandardizer ds) {
		if(checker.isValidDayFirstStringDateFormat(tokens,i+2)){
			if(i+3 < tokens.length){
				try{
					if(Integer.parseInt(tokens[i+3]) > 0){
						date_ = tokens[i+1] + STRING_SPACE + tokens[i+2] + STRING_SPACE + tokens[i+3];
						input = input.replaceFirst(tokens[i] + STRING_SPACE + date_, "").trim();
						date_ = ds.formatDate(FORMAT_DAY_FIRST_STRING + date_);
					}
					else{
						date_ = tokens[i+1] + STRING_SPACE + tokens[i+2];
						input = input.replaceFirst(tokens[i] + STRING_SPACE + date_, "").trim();
						date_ = ds.formatDate(FORMAT_DAY_FIRST_STRING + date_);
					}
				} catch(Exception e){
					date_ = tokens[i+1] + STRING_SPACE + tokens[i+2];
					input = input.replaceFirst(tokens[i] + STRING_SPACE + date_, "").trim();
					date_ = ds.formatDate(FORMAT_DAY_FIRST_STRING + date_);
				}
			}
			else{
				date_ = tokens[i+1] + STRING_SPACE + tokens[i+2];
				input = input.replaceFirst(tokens[i] + STRING_SPACE + date_, "").trim();
				date_ = ds.formatDate(FORMAT_DAY_FIRST_STRING + date_);
			}

			if(dateStart_ != null){
				adjustDate();
			}
		}
		return input;
	}

	private String checkMonthStringWithKeyword(String[] tokens, int i,
			String input, DateStandardizer ds) {
		String temp;
		temp = tokens[i+1].replaceAll("(?<=[A-Za-z])(?=[0-9])|(?<=[0-9])(?=[A-Za-z])", " " ).trim();
		String[] dateArray = temp.split(STRING_SPACE);
		if(checker.isValidDayFirstStringDateFormat(dateArray, 1)){
			input = input.replaceFirst(tokens[i] + STRING_SPACE + tokens[i+1], "").trim();
			date_ = ds.formatDate(FORMAT_DAY_FIRST_STRING + temp);
			if(dateStart_ != null){
				adjustDate();
			}
		}
		if(checker.isValidMonthFirstStringDateFormat(dateArray, 0)){
			input = input.replaceFirst(tokens[i] + STRING_SPACE + tokens[i+1], "").trim();
			date_ = ds.formatDate(FORMAT_MONTH_FIRST_STRING + temp);
			if(dateStart_ != null){
				adjustDate();
			}
		}
		return input;
	}

	private String checkSlashDateFormatWithKeyword(String[] tokens, int i,
			String input, DateStandardizer ds) {
		if(checker.isValidSlashDateFormat(tokens[i+1])){
			date_ = tokens[i+1];
			input = input.replaceFirst(tokens[i] + STRING_SPACE + date_, "").trim();
			date_ = ds.formatDate(FORMAT_SLASH_DATE + date_);
			if(dateStart_ != null){
				adjustDate();
			}
		}
		return input;
	}

	private String checkDictionaryDayWithKeyword(String[] tokens, int i,
			String input, DateStandardizer ds) {
		date_ = tokens[i+1];
		input = input.replaceFirst(tokens[i] + STRING_SPACE + date_, "").trim();
		date_ = ds.formatDate(FORMAT_DICTIONARY_DAY + date_);
		if(dateStart_ != null){
			adjustDayDate();
		}
		return input;
	}

	private void adjustDate() {
		if(!validEndDate(date_,dateStart_)){
			date_ = getYearModifiedDate();
		}	
	}

	private void adjustDayDate() {
		if(!validEndDate(date_,dateStart_)){
			date_ = getWeekModifiedDate(7,date_);
		}
	}

	private boolean isNotOutOfBounds(int index, int length) {
		return index < length && index >= 0;
	}

	private boolean dictionaryEquals(String[] dictionary, String keyword) {
		boolean isFound = false;
		for (int i = 0; i < dictionary.length; i++) {
			if (dictionary[i].equalsIgnoreCase(keyword)) {
				isFound = true;
				break;
			}
		}
		return isFound;
	}

	private boolean dictionaryContains(String[] dictionary, String keyword) {
		boolean isFound = false;
		for (int i = 0; i < dictionary.length; i++) {
			if (keyword.contains(dictionary[i])) {
				isFound = true;
				break;
			}
		}
		return isFound;
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

	private static String getWeekModifiedDate(int offset, String date_){

		String modDate;
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd/MM/YYYY");

		String[] dateArray = date_.split(STRING_SLASH);
		int day = Integer.parseInt(dateArray[0]);
		int month = Integer.parseInt(dateArray[1]);
		int year = Integer.parseInt(dateArray[2]);

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		calendar.add(Calendar.DAY_OF_MONTH, offset);
		modDate = dayFormat.format(calendar.getTime());

		return modDate;
	}

	private String getYearModifiedDate() {		

		String modDate;
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd/MM/YYYY");

		String[] dateArray = date_.split(STRING_SLASH);
		int day = Integer.parseInt(dateArray[0]);
		int month = Integer.parseInt(dateArray[1]);
		int year = Integer.parseInt(dateArray[2]);

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		calendar.add(Calendar.YEAR, 1);
		modDate = dayFormat.format(calendar.getTime());

		return modDate;
	}	

}
