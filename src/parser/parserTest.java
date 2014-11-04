package parser;

/**
 * This class is used to test
 * parsed date and time.
 * 
 * * Author: smallson
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import com.Task;
import com.TentativeTask;
import com.TimePeriod;

public class parserTest {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		ParserFacade pf = ParserFacade.getInstance();
		while(true){
			System.out.print("line: ");
			String input = sc.nextLine();
			TentativeTask task = pf.getTentative(input);
			ArrayList<TimePeriod> list = task.getTimePeriods();
			
			System.out.println(task.getEvent());
			System.out.println(list.get(0).getStartDateString());
			System.out.println(list.get(0).getStartTimeString());
			System.out.println(list.get(0).getEndDateString());
			System.out.println(list.get(0).getEndTimeString());
			if(list.size() > 1){
				System.out.println(list.get(1).getStartDateString());
				System.out.println(list.get(1).getStartTimeString());
				System.out.println(list.get(1).getEndDateString());
				System.out.println(list.get(1).getEndTimeString());
			}
//			System.out.println("command: " + pf.getCommandString(input));
//			System.out.println("event: " + task.getTaskDescription());
//			System.out.println("deadline: " + task.getDeadLine());
//			System.out.println("start: " + task.getStartTime() + " " + task.getStartDate());
//			System.out.println("end: " + task.getEndTime() + " " + task.getEndDate());
			
//			System.out.println(getCurrentDay());
//			System.out.println(getCurrentMonth());
//			System.out.println(getCurrentYear());
//			System.out.println(getCurrentDate());
		}
	}
	
	private static String getCurrentDate(){
		String currDate;
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd/MM/YYYY");

		Calendar calendar = Calendar.getInstance();
		calendar.add(calendar.DAY_OF_WEEK, 7);
		currDate = dayFormat.format(calendar.getTime());
		
		return currDate;
	}

	
	private static String getCurrentDay(){
		String currDay;
		SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");

		Calendar calendar = Calendar.getInstance();
		currDay = dayFormat.format(calendar.getTime());
		
		return currDay;
	}
	
	private static String getCurrentMonth(){
		String currMonth;
		SimpleDateFormat dayFormat = new SimpleDateFormat("MM");

		Calendar calendar = Calendar.getInstance();
		currMonth = dayFormat.format(calendar.getTime());
		
		return currMonth;
	}
	
	private static String getCurrentYear(){
		
			String currYear;
			SimpleDateFormat dayFormat = new SimpleDateFormat("YYYY");

			Calendar calendar = Calendar.getInstance();
			currYear = dayFormat.format(calendar.getTime());
			
			return currYear;
		
	}
	
}

