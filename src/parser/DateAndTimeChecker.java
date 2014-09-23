package parser;

/**
 * This class is used to verify 
 * valid time and date formats.
 */

import java.util.Scanner;

public class DateAndTimeChecker {
	
	private static DateAndTimeChecker checker = new DateAndTimeChecker();
	
	private DateAndTimeChecker(){
	}
	
	protected static DateAndTimeChecker getInstance(){
		return checker;
	}
	
	
	/**
	 * Time Verification
	 */
	public boolean isValidDefaultTimeFormat(String string) {
		
		try{
			Scanner sc = new Scanner(string).useDelimiter("[^0-9]+");		
			int integer = sc.nextInt();
			if(integer == 12){
				if(!string.replace("12", "").equalsIgnoreCase("NN") && !string.replace("12", "").equalsIgnoreCase("MN")){
					System.out.println("invalid time");
					return false;
				}
			}
			else if(integer>12 || integer <= 0){
				System.out.println("invalid time");
				return false;
			}
			
		} catch(Exception e){
			System.out.println("exception");
			return false;
		}
		System.out.println(string);

		return true;
	}

	public boolean isValidMilitaryTimeFormat(String string) {

		try{
			String firstTwoDigits = string.substring(0,2);
			String lastTwoDigits = string.substring(2);

			if(Integer.parseInt(firstTwoDigits) > 23 || Integer.parseInt(lastTwoDigits) > 59){
				System.out.println("invalid military time format");
				return false;
			}

		}catch(Exception e){
			System.out.println("military time format exception");
			return false;
		}
		return true;
	}

	/**
	 * Date Verification
	 */

	public boolean isValidSlashDateFormat(String string){

		String [] num = string.split("/");

		if(num.length < 2 || num.length > 3)
			return false;
		try{
			if(Integer.parseInt(num[0]) > 31 || Integer.parseInt(num[0]) <= 0){
				System.out.println("invalid day");
				return false;
			}

		} catch (Exception e){
			System.out.println("day exception");
			return false;
		}

		try{
			if(Integer.parseInt(num[1]) > 12 || Integer.parseInt(num[1]) <= 0){
				System.out.println("invalid month");
				return false;
			}

		} catch (Exception e){
			System.out.println("month exception");
			return false;
		}

		if(num.length == 3){
			try{
				if(Integer.parseInt(num[2]) < 0){
					System.out.println("invalid year");
					return false;
				}

			} catch (Exception e){
				System.out.println("year exception");
				return false;
			}
		}
		return true;
	}

	public boolean isValidMonthFirstStringDateFormat(String[] words, int index) {

		try{
			words[index + 1] = words[index + 1].replaceAll("st","");
			words[index + 1] = words[index + 1].replaceAll("nd","");
			words[index + 1] = words[index + 1].replaceAll("rd","");
			words[index + 1] = words[index + 1].replaceAll("th","");
			if(Integer.parseInt(words[index + 1]) > 31 || Integer.parseInt(words[index + 1]) <= 0){
				System.out.println("invalid string day");
				return false;
			}
		} catch(Exception e){
			System.out.println("month first string day exception");
			return false;
		}

		return true;
	}

	public boolean isValidDayFirstStringDateFormat(String[] words, int index) {

		try{
			words[index - 1] = words[index - 1].replaceAll("st","");
			words[index - 1] = words[index - 1].replaceAll("nd","");
			words[index - 1] = words[index - 1].replaceAll("rd","");
			words[index - 1] = words[index - 1].replaceAll("th","");
			if(Integer.parseInt(words[index-1]) > 31 || Integer.parseInt(words[index-1]) <= 0){
				System.out.println("invalid string day");
				return false;
			}
		} catch(Exception e){
			System.out.println("day first string day exception");
			return false;
		}

		return true;
	}



}
