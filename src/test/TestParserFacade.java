package test;

import static org.junit.Assert.*;

import org.junit.Test;

import parser.ParserFacade;
import storage.ModelTask;
import storage.Task;

public class TestParserFacade {

	private static final String TASKSTRINGFORMAT = "event: " + "%1$s" + "\n"
			+ "start date: " + "%2$s" + "\n" + "end date: " + "%3$s" + "\n"
			+ "start time: " + "%4$s" + "\n" + "end time: " + "%5$s" + "\n"
			+ "is done: " + "%6$s" + "\n" + "is urgent: " + "%7$s" + "\n";
	ParserFacade parser = ParserFacade.getInstance(); 

	@Test
	public void test() {
		//adding task
		parser.getTask("add go to school");
		
		
	}
	
	private void testParser(String expected, String actual){
		
	}
	
	// return a string representation of the Task
	private String taskStringRepresentation(Task task) {
		return String.format(TASKSTRINGFORMAT, task.getTaskDescription(),
				task.getStartDate(), task.getEndDate(),
				task.getStartTime(), task.getEndTime(),
				task.isDone(), task.isUrgent());
	}

}
