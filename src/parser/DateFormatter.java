package parser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * This class is used to convert
 * various date formats into a single
 * standard date format.
 * 
 * * Author: smallson
 */


public class DateFormatter {
	
	private static final String[] DICTIONARY_TOMORROW = { "TOMORROW", "TMR" };
	
	private static final int NUM_SINGLE_DAY_OFFSET = 1;


	public DateFormatter(){			
	}

	public String convertDDformat(String date) {
		
		int offset = 0;

		if(dictionaryContains(DICTIONARY_TOMORROW,date)){
			offset = NUM_SINGLE_DAY_OFFSET;
			return getModifiedDate(offset);
		}
		DayParser dp = new DayParser();
		offset = dp.getDayIndex(date) - dp.getDayIndex(getCurrentDay());
		if(offset < 0){
			int temp = offset;
			if(temp == -6){
				offset = 1;
			}else if(temp == -5){
				offset = 2;
			}else if(temp == -4){
				offset = 3;
			}else if(temp == -3){
				offset = 4;
			}else if(temp == -2){
				offset = 5;
			}else if(temp == -1){
				offset = 6;
			}
		}
		return getModifiedDate(offset);
	}

	public String convertSDformat(String date) {
		String[] dateArray = date.split("/");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateArray[0]));
		calendar.set(Calendar.MONTH, Integer.parseInt(dateArray[1])- 1);
		
		return dateFormat.format(calendar.getTime());
	}

	public String convertDFSformat(String date) {
		
		MonthParser mp = new MonthParser();
		String[] dateArray = date.split(" ");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateArray[0]));
		calendar.set(Calendar.MONTH, mp.getMonthIndex(dateArray[1]));
		
		return dateFormat.format(calendar.getTime());
	}

	public String convertMFSformat(String date) {
		
		MonthParser mp = new MonthParser();
		String[] dateArray = date.split(" ");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateArray[1]));
		calendar.set(Calendar.MONTH, mp.getMonthIndex(dateArray[0]));
		
		return dateFormat.format(calendar.getTime());
	}

	public String convertNDDformat(String date) {
		
		int offset = 0;
		
		DayParser dp = new DayParser();
		offset = dp.getDayIndex(date.split(" ")[1]) - dp.getDayIndex(getCurrentDay());
		if(offset < 0){
			int temp = offset;
			if(temp == -6){
				offset = 1;
			}else if(temp == -5){
				offset = 2;
			}else if(temp == -4){
				offset = 3;
			}else if(temp == -3){
				offset = 4;
			}else if(temp == -2){
				offset = 5;
			}else if(temp == -1){
				offset = 6;
			}
		}
		offset += 7;
		return getModifiedDate(offset);
	}
	
	private static String getCurrentDay(){
		String currDay;
		SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");

		Calendar calendar = Calendar.getInstance();
		currDay = dayFormat.format(calendar.getTime());
		
		return currDay;
	}
	
	private static String getModifiedDate(int offset){
		String currDate;
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd/MM/YYYY");

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, offset);
		currDate = dayFormat.format(calendar.getTime());
		
		return currDate;
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
