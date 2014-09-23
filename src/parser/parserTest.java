package parser;

/**
 * This class is used to test
 * parsed date and time.
 */

import java.util.Date;
import java.util.Scanner;

public class parserTest {
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		InputParser ip = InputParser.getInstance();
		while(true){
			System.out.print("line: ");
			String input = sc.nextLine();
			ip.parseInput(input);
			System.out.println("event: " + ip.getTaskDescription());
			System.out.println("deadline: " + ip.getDeadLine());
			System.out.println("start: " + ip.getStartTime() + " " + ip.getStartDate());
			System.out.println("end: " + ip.getEndTime() + " " + ip.getStartDate());
		}
	}

}
