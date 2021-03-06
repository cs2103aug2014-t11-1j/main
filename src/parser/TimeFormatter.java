package parser;

import java.util.Scanner;

/**
 * This class is used to convert
 * various time formats into a single
 * standard time format.
 * 
 * * Author: smallson
 */
//@author A0116211B

public class TimeFormatter {
	
	private static final String[] DICTIONARY_NOON = {"NOON", "NN"};
	private static final String[] DICTIONARY_MIDNIGHT = {"MIDNIGHT", "MN"};
	private static final String[] DICTIONARY_MORNING = {"MORNING", "MORN"};
	private static final String[] DICTIONARY_AFTERNOON = {"AFTERNOON", "AFTN"};
	private static final String[] DICTIONARY_EVENING = {"EVENING"};
	private static final String[] DICTIONARY_NIGHT = {"TONIGHT", "NIGHTTIME", "NITE", "TONITE"};
	
	private static final String STRING_NOON = "1200";
	private static final String STRING_MIDNIGHT = "0000";
	private static final String STRING_MORNING = "1100";
	private static final String STRING_AFTERNOON = "1700";
	private static final String STRING_EVENING = "1900";
	private static final String STRING_NIGHT = "2300";
	
	private static final String STRING_DASH = "-";
	private static final String STRING_TO = "to";
	private static final String STRING_COLON = ":";
	
	
	public TimeFormatter(){
	}

	public String convertSPTformat(String time) {
		
		if(dictionaryContains(DICTIONARY_NOON, time)){
			return STRING_NOON;
		} else if (dictionaryContains(DICTIONARY_MIDNIGHT, time)){
			return STRING_MIDNIGHT;
		} else if (dictionaryContains(DICTIONARY_MORNING, time)){
			return STRING_MORNING;
		} else if (dictionaryContains(DICTIONARY_AFTERNOON, time)){
			return STRING_AFTERNOON;
		} else if (dictionaryContains(DICTIONARY_EVENING, time)){
			return STRING_EVENING;
		} else if (dictionaryContains(DICTIONARY_NIGHT, time)){
			return STRING_NIGHT;
		}
		System.out.println("fail");
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

		String[] timeArray = time.split(STRING_COLON);
		
		if(timeArray[0].length() < 2){
			time = "0" + time;
		}
		
		return time.replace(STRING_COLON, "");
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

	public String convertDTFformat(String time) {
		
		String hours = null;
		String minutes = "00";
		
		String[] timeArray = time.split(STRING_DASH);
		if(timeArray.length < 2){
			timeArray = time.split(STRING_TO);
		}
		
		try{
			Scanner sc = new Scanner(timeArray[0]).useDelimiter("[^0-9]+");		
			Integer integer = sc.nextInt();
			if(integer == 12){
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
			return null;
		}
		
		if(hours.length() < 2){
			hours = "0" + hours;
		}
		
		
		
		return hours + minutes;
	}

	public String convertDTNformat(String time) {
		
		String hours = null;
		String minutes = "00";
		
		String[] timeArray = time.split(STRING_DASH);
		if(timeArray.length < 2){
			timeArray = time.split(STRING_TO);
		}
		
		try{
			Scanner sc = new Scanner(timeArray[1]).useDelimiter("[^0-9]+");		
			Integer integer = sc.nextInt();
			if(integer == 12){
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
			return null;
		}
		
		if(hours.length() < 2){
			hours = "0" + hours;
		}

		return hours + minutes;
	}
}
