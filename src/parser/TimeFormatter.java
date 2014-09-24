package parser;

import java.util.Scanner;

/**
 * This class is used to convert
 * various time formats into a single
 * standard time format.
 * 
 * * Author: smallson
 */


public class TimeFormatter {
	
	private static final String[] DICTIONARY_NOON = {"NOON", "NN"};
	private static final String[] DICTIONARY_MIDNIGHT = {"MIDNIGHT", "MN"};
	
	private static final String STRING_NOON = "1200";
	private static final String STRING_MIDNIGHT = "0000";
	
	
	public TimeFormatter(){
	}

	public String convertSPTformat(String time) {
		if(dictionaryContains(DICTIONARY_NOON,time)){
			return STRING_NOON;
		}else if(dictionaryContains(DICTIONARY_MIDNIGHT,time)){
			return STRING_MIDNIGHT;
		}		
		return time;
	}

	public String convertDTformat(String time) {
		
		String hours = null;
		String minutes = "00";
		
		try{
			Scanner sc = new Scanner(time).useDelimiter("[^0-9]+");		
			Integer integer = sc.nextInt();
			if(integer == 12){
				if(time.replace("12", "").equalsIgnoreCase("NN")){
					return STRING_NOON;
				}
				if(time.replace("12", "").equalsIgnoreCase("MN")){
					return STRING_MIDNIGHT;
				}
				if(time.contains("AM") || time.contains("am")){
					integer -= 12;
				}

			}

			if(time.contains("PM") || time.contains("pm") && integer < 12){
				integer += 12;
			}
			
			hours = integer.toString();
			
			if(sc.hasNextInt()){
				Integer temp = sc.nextInt();
				minutes = temp.toString();
			}

		} catch(Exception e){
			System.out.println("exception");
			return time;
		}

		if(hours.length() < 2){
			hours = "0" + hours;
		}
		return hours + minutes;
	}

	public String convertMTformat(String time) {
		return time;
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
