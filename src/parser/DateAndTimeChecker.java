package parser;

import java.util.Scanner;

/**
 * This class is used to verify 
 * valid time and date formats.
 * * Author: smallson
 */
//@author A0116211B
public class DateAndTimeChecker {
	
	/**
	 * String constants
	 */
	private static final String STRING_SPACE = " ";
	private static final String STRING_DASH = "-";
	
	/**
	 * Integer constants
	 */
	private static final int INT_HOUR_TWELVE = 12;
	
	private static final int INT_MAX_HOUR= 23;
	private static final int INT_MAX_MINUTES = 59;
	private static final int INT_MAX_DAYS= 31;
	
	/**
	 * String dictionaries
	 */
	private static final String[] DICTIONARY_MONTHS = {"janurary","feburary","march","april","may","june","july","august","september","october","november","december",
		"jan","feb","mar","apr","jun","jul","aug","sept","sep","oct","nov","dec"};
	private static final String[] DICTIONARY_SUFFIX_TIME = {"AM","PM","MN","NN"};
	
	/**
	 * Regex constants
	 */	
	private static final String DEFAULT_TIME_REGEX = "(1[012]|[1-9])((:|.)[0-5][0-9])?(\\s)?(?i)(am|pm|mn|nn)";
	private static final String NUMBER_REGEX = "[^0-9]+";
	
	private static DateAndTimeChecker checker_ = new DateAndTimeChecker();
	
	private DateAndTimeChecker(){
	}
	
	protected static DateAndTimeChecker getInstance(){
		return checker_;
	}
	
	
	/**
	 * Time Verification
	 */
	public boolean isValidDefaultTimeFormat(String string) {
		
		if(!string.matches(DEFAULT_TIME_REGEX)){
			return false;
		}
		
		return verifyDefaultTimeFormat(string);
	}

	private boolean verifyDefaultTimeFormat(String string) {
		
		try{
			Scanner sc = new Scanner(string).useDelimiter(NUMBER_REGEX);		
			int integer = sc.nextInt();
			
			if(!dictionaryContains(DICTIONARY_SUFFIX_TIME,string.substring(string.length() - 2))){
				return false;
			}

			if(integer > INT_HOUR_TWELVE || integer <= 0){
				System.out.println("invalid time");
				return false;
			}
			
			if(sc.hasNextInt()){
				String minutes = string.substring(string.length() - 4, string.length() - 2);
				int temp = sc.nextInt();
				if(temp > INT_MAX_MINUTES || temp < 0 || temp != Integer.parseInt(minutes)){
					return false;
				}
			}
			
		} catch(Exception e){
			return false;
		}
		
		return true;
	}

	public boolean isValidMilitaryTimeFormat(String string) {
		
		string = string.replaceAll(":", "");

		return verifyMilitaryTimeFormat(string);
	}

	private boolean verifyMilitaryTimeFormat(String string) {
		try{
			String firstTwoDigits = string.substring(0,2);
			String lastTwoDigits = string.substring(2);
			
			if(Integer.parseInt(firstTwoDigits) < 0 || Integer.parseInt(lastTwoDigits) < 0){
				System.out.println("invalid military time format");
				return false;
			}

			if(Integer.parseInt(firstTwoDigits) > INT_MAX_HOUR || Integer.parseInt(lastTwoDigits) > INT_MAX_MINUTES){
				System.out.println("invalid military time format");
				return false;
			}

		}catch(Exception e){
			return false;
		}
		return true;
	}

	/**
	 * Date Verification
	 */

	public boolean isValidSlashDateFormat(String string){

		String [] num = string.split("/");

		return verifySlashDateFormat(num);
	}

	private boolean verifySlashDateFormat(String[] num) {
		if(num.length < 2 || num.length > 3)
			return false;
		
		try{
			if(Integer.parseInt(num[0]) > INT_MAX_DAYS || Integer.parseInt(num[0]) <= 0){
				System.out.println("invalid day");
				return false;
			}

		} catch (Exception e){
			//System.out.println("day exception");
			return false;
		}

		try{
			if(Integer.parseInt(num[1]) > INT_HOUR_TWELVE || Integer.parseInt(num[1]) <= 0){
				System.out.println("invalid month");
				return false;
			}

		} catch (Exception e){
			//System.out.println("month exception");
			return false;
		}

		if(num.length == 3){
			try{
				if(Integer.parseInt(num[2]) < 0){
					System.out.println("invalid year");
					return false;
				}

			} catch (Exception e){
				return false;
			}
		}
		return true;
	}

	public boolean isValidMonthFirstStringDateFormat(String[] words, int index) {

		try{
			String temp = words[index + 1];
			temp = temp.replaceAll("st","");
			temp = temp.replaceAll("nd","");
			temp = temp.replaceAll("rd","");
			temp = temp.replaceAll("th","");
			if(Integer.parseInt(temp) > INT_MAX_DAYS || Integer.parseInt(temp) <= 0){
				System.out.println("invalid string day");
				return false;
			}
		} catch(Exception e){
			return false;
		}
		return true;
	}

	public boolean isValidDayFirstStringDateFormat(String[] words, int index) {
		
		try{
			String temp = words[index - 1];
			temp = temp.replaceAll("st","");
			temp = temp.replaceAll("nd","");
			temp = temp.replaceAll("rd","");
			temp = temp.replaceAll("th","");
			if(Integer.parseInt(temp) > INT_MAX_DAYS || Integer.parseInt(temp) <= 0){
				System.out.println("invalid string day");
				return false;
			}
		} catch(Exception e){
	//		System.out.println("day first string day exception");
			return false;
		}

		return true;
	}

	public boolean isValidDashDateFormat(String toParse) {
		
		try{
			Scanner sc = new Scanner(toParse).useDelimiter(NUMBER_REGEX);		
			Integer firstInteger = sc.nextInt();
			Integer secondInteger = sc.nextInt();
			if(firstInteger >= secondInteger){
				return false;
			}
			if(firstInteger > INT_MAX_DAYS || secondInteger > INT_MAX_DAYS){
				return false;
			}
			String month = toParse.replace(firstInteger.toString(), "");
			month = month.replace(secondInteger.toString(), "");
			month = month.replace("-", "");
			if(!dictionaryContains(DICTIONARY_MONTHS,month)){
				return false;
			}
		} catch(Exception e){
			return false;
		}
		
		return true;
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
	
	//depreciated
	public boolean isValidSingleStringDateFormat(String[] words, int index, int type) {
		
		String date = words[index].replaceAll("([^\\d-]?)(-?[\\d\\.]+)([^\\d]?)", "$1 $2 $3").replaceAll(" +", STRING_SPACE);
		String[] dateArray = date.split(STRING_SPACE);
		
		if(dateArray.length > 3 || dateArray.length < 2){
			System.out.println("valid");
			return false;
		}else if(Integer.parseInt(dateArray[type]) > INT_MAX_DAYS || Integer.parseInt(dateArray[type]) <= 0){
			return false;
		}
		return true;
	}
}
