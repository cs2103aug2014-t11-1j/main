package gui.controller;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.regex.Pattern;

public class DateStringComparator implements Comparator<String>{

	static private SimpleDateFormat oneDateGetter = new SimpleDateFormat("EEE, MMM d");
	static private SimpleDateFormat twoDateGetter = new SimpleDateFormat("d MMM");
	static private Pattern oneMonthPattern = Pattern.compile("\\d{1,2}\\p{Blank}-\\p{Blank}\\d{1,2}\\p{Blank}\\w*");

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
		else if(str1.equals("") && !str2.equals("")){
			return 0;
		}
		
		
		if(str1.contains("-")){
			if(isOneMonth(str1)){
				str1 = cutOutOneMonthDate(str1);
			}
			else{
				str1 = cutOutTwoMonthDate(str1);
			}
			date1 = twoDateGetter.parse(str1, new ParsePosition(0));
		}
		else{
			date1 = oneDateGetter.parse(str1, new ParsePosition(0));
		}
		
		if(str2.contains("-")){
			if(isOneMonth(str2)){
				str2 = cutOutOneMonthDate(str2);
			}
			else{
				str2 = cutOutTwoMonthDate(str2);
			}
			date2 = twoDateGetter.parse(str2, new ParsePosition(0));
		}
		else{
			date2 = oneDateGetter.parse(str2, new ParsePosition(0));
		}
		
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
	


	//d - d MMM
	private boolean isOneMonth(String str) {
		return oneMonthPattern.matcher(str).matches();
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
		System.out.println(str);
		return str;
	}

}
