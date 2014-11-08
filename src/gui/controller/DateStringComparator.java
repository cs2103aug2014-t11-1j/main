package gui.controller;
/**
 * Depreciated class; table doesn't allow visual sorting
 * by clicking anymore
 * 
 * Comparator to compare the dateStrings in table
 * the dates are in strings with the below formats:
 * One day:
 * EEE, MMM d
 * Two days in the same month:
 * d - d MMM
 * Two days in different months:
 * d MMM - d MMM
 * 
 * @author A0116018R
 */

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.regex.Pattern;

public class DateStringComparator implements Comparator<String>{

	static private SimpleDateFormat ONE_DATE_GETTER = new SimpleDateFormat("EEE, MMM d");
	static private SimpleDateFormat TWO_DATE_GETTER = new SimpleDateFormat("d MMM");
	static private Pattern ONE_MONTH_PATTERN = Pattern.compile("\\d{1,2}\\p{Blank}-\\p{Blank}\\d{1,2}\\p{Blank}\\w*");

	@Override
	public int compare(String str1, String str2) {
		Date date1; 
		Date date2;
		
		//if one or more of the inputs are null
		if(str1.equals("") && !str2.equals("")){
			return -1;
		}
		else if(!str1.equals("") && str2.equals("")){
			return 1;
		}
		else if(str1.equals("") && str2.equals("")){
			return 0;
		}
		
		System.out.println("checking: " + str1);
		if(isTwoDates(str1)){
			if(isOneMonth(str1)){
				str1 = cutOutOneMonthDate(str1);
			}
			else{
				str1 = cutOutTwoMonthDate(str1);
			}
			date1 = TWO_DATE_GETTER.parse(str1, new ParsePosition(0));
		}
		else{
			System.out.println("one date");
			date1 = ONE_DATE_GETTER.parse(str1, new ParsePosition(0));
		}
		
		if(isTwoDates(str2)){
			if(isOneMonth(str2)){
				str2 = cutOutOneMonthDate(str2);
			}
			else{
				str2 = cutOutTwoMonthDate(str2);
			}
			date2 = TWO_DATE_GETTER.parse(str2, new ParsePosition(0));
		}
		else{
			date2 = ONE_DATE_GETTER.parse(str2, new ParsePosition(0));
		}
		
		//compare the parsed dates
		if(date1.compareTo(date2) < 0){
			return -1;
		}
		else if(date1.compareTo(date2) == 0){
			return 0;
		}
		else{
			return 1;
		}
	}

	private boolean isTwoDates(String str) {
		return str.contains("-");
	}

	//d - d MMM
	private boolean isOneMonth(String str) {
		return ONE_MONTH_PATTERN.matcher(str).matches();
	}

	//d - d MMM
	private String cutOutOneMonthDate(String str) {
		String day = str.substring(0, str.indexOf('-'));
		str = str.substring(str.indexOf(' ') + 1);
		str = str.substring(str.indexOf(' ') + 1);
		String month = str.substring(str.indexOf(' ') + 1);
		return day + month;
	}
	
	//d MMM - d MMM
	private String cutOutTwoMonthDate(String str) {
		str = str.substring(0, str.indexOf('-') -1);
		return str;
	}

}
