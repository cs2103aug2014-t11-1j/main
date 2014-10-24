package test;

import static org.junit.Assert.*;

import org.junit.Test;

import parser.ParserFacade;
import storage.Task;

public class TestParserFacade {

	private static final String TASKSTRINGFORMAT = "event: " + "%1$s" + "\n"
			+ "start date: " + "%2$s" + "\n" + "end date: " + "%3$s" + "\n"
			+ "start time: " + "%4$s" + "\n" + "end time: " + "%5$s" + "\n"
			+ "deadLine: " + "%6$s" + "\n"
			+ "is done: " + "%7$s" + "\n" + "is urgent: " + "%8$s" + "\n";
	ParserFacade parser = ParserFacade.getInstance(); 
	private Task task;
	@Test
	public void test() {
		//normal condition: adding task without date or time
		task = parser.getTask("add go to school");
		testParserOutput(String.format(TASKSTRINGFORMAT, "go to school", "null",
				"null", "null", "null","null", "false", "false"), taskStringRepresentation(task));
		
		//adding nothing at all
		task = parser.getTask("");
		testParserOutput(String.format(TASKSTRINGFORMAT, "", "null",
				"null", "null", "null","null", "false", "false"), taskStringRepresentation(task));
		
		//testing using "by" keyword for date
		
		//testing using "by" keyword for time
		
		//testing "from" and "to" for 2 date interval
		
		//testing "from" and "to" for 2 time interval
		
		//testing "from" and "to" for both dates and time
		
		
	}
	
	private void testParserOutput(String expected, String actual){
		assertEquals(expected, actual);
	}
	
	// return a string representation of the Task
	private String taskStringRepresentation(Task task) {
		return String.format(TASKSTRINGFORMAT, task.getTaskDescription(),
				task.getStartDate(), task.getEndDate(),
				task.getStartTime(), task.getEndTime(),task.getDeadLine(),
				task.isDone(), task.isUrgent());
	}

}
