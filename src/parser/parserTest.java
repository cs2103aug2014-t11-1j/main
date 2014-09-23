package parser;

/**
 * This class is used to test
 * parsed date and time.
 */

import java.util.Date;
import java.util.Scanner;

import storage.Task;

public class parserTest {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		ParserFacade pf = ParserFacade.getInstance();
		while(true){
			System.out.print("line: ");
			String input = sc.nextLine();
			Task task = pf.getTask(input);
			System.out.println("command: " + pf.getCommandString(input));
			System.out.println("event: " + task.getTaskDescription());
			System.out.println("deadline: " + task.getDeadLine());
			System.out.println("start: " + task.getStartTime() + " " + task.getStartDate());
			System.out.println("end: " + task.getEndTime() + " " + task.getStartDate());
		}

	}
}

