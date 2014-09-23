package gui.controller;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class DateStringComparator implements Comparator<String>{

	static private SimpleDateFormat dateGetter = new SimpleDateFormat("EEE, MMM d");

	@Override
	public int compare(String str1, String str2) {
		Date date1 = dateGetter.parse(str1, new ParsePosition(0));
		Date date2 = dateGetter.parse(str2, new ParsePosition(0));

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

}
