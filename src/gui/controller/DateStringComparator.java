package gui.controller;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class DateStringComparator implements Comparator<String>{

	static private SimpleDateFormat oneDateGetter = new SimpleDateFormat("EEE, MMM d");
	static private SimpleDateFormat twoDateGetter = new SimpleDateFormat("d MMM");

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
			str1 = cutOutDate(str1);
			date1 = twoDateGetter.parse(str1, new ParsePosition(0));
		}
		else{
			date1 = oneDateGetter.parse(str1, new ParsePosition(0));
		}
		
		if(str2.contains("-")){
			str2 = cutOutDate(str2);
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
	private String cutOutDate(String str) {
		str = str.trim();
		String day = str.substring(0, str.indexOf('-'));
		str = str.substring(str.indexOf(' '));
		str = str.trim();
		String month = str.substring(str.indexOf(' '));
		return day + month;
	}

}
