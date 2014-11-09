package test;

import static org.junit.Assert.*;
import org.junit.Test;
import com.Task;
import parser.ParserFacade;

//@author A0116211B
public class TestParserFacade {

	private static final String TASKSTRINGFORMAT = "event: " + "%1$s" + "\n"
			+ "start date: " + "%2$s" + "\n" + "end date: " + "%3$s" + "\n"
			+ "start time: " + "%4$s" + "\n" + "end time: " + "%5$s" + "\n"
			+ "deadLine: " + "%6$s" + "\n"
			+ "is done: " + "%7$s" + "\n" + "is urgent: " + "%8$s" + "\n";
	ParserFacade parser = ParserFacade.getInstance(); 
	private Task task_;
	private static final String STRING_QUOTE = "\"";
	
	@Test
	public void test() {
		//normal condition: adding task_ without date or time
		task_ = parser.getTask("add go to school");
		testParserOutput(String.format(TASKSTRINGFORMAT, "go to school", "null",
				"null", "null", "null","null", "false", "false"), taskStringRepresentation(task_));
		
		//adding nothing at all
		task_ = parser.getTask("");
		testParserOutput(String.format(TASKSTRINGFORMAT, "", "null",
				"null", "null", "null","null", "false", "false"), taskStringRepresentation(task_));
		
		//testing using "by" keyword for date
		task_ = parser.getTask("add do homework by 21/12/2014");
		testParserOutput(String.format(TASKSTRINGFORMAT, "do homework", "null",
				"null", "null", "null", "21/12/2014", "false", "false"), taskStringRepresentation(task_));
		
		task_ = parser.getTask("add do homework by 21 dec 2014");
		testParserOutput(String.format(TASKSTRINGFORMAT, "do homework", "null",
				"null", "null", "null", "21/12/2014", "false", "false"), taskStringRepresentation(task_));
		
		task_ = parser.getTask("add do homework by 21 december 2014");
		testParserOutput(String.format(TASKSTRINGFORMAT, "do homework", "null",
				"null", "null", "null", "21/12/2014", "false", "false"), taskStringRepresentation(task_));
		
		task_ = parser.getTask("add do homework by 21st dec 2014");
		testParserOutput(String.format(TASKSTRINGFORMAT, "do homework", "null",
				"null", "null", "null", "21/12/2014", "false", "false"), taskStringRepresentation(task_));
		
		//testing using "by" keyword for time
		task_ = parser.getTask("add do homework by 21/12/2014 2pm");
		testParserOutput(String.format(TASKSTRINGFORMAT, "do homework", "null",
				"null", "1400", "null","21/12/2014", "false", "false"), taskStringRepresentation(task_));
		
		task_ = parser.getTask("add do homework by 21/12/2014 2am");
		testParserOutput(String.format(TASKSTRINGFORMAT, "do homework", "null",
				"null", "0200", "null","21/12/2014", "false", "false"), taskStringRepresentation(task_));
		
		task_ = parser.getTask("add do homework by 21/12/2014 12am");
		testParserOutput(String.format(TASKSTRINGFORMAT, "do homework", "null",
				"null", "0000", "null","21/12/2014", "false", "false"), taskStringRepresentation(task_));
		
		task_ = parser.getTask("add do homework by 21/12/2014 12pm");
		testParserOutput(String.format(TASKSTRINGFORMAT, "do homework", "null",
				"null", "1200", "null","21/12/2014", "false", "false"), taskStringRepresentation(task_));
		
		//testing "from" and "to" for 2 date interval
		task_ = parser.getTask("add do homework from 21/12/2014 to 22/12/2014");
		testParserOutput(String.format(TASKSTRINGFORMAT, "do homework", "21/12/2014",
				"22/12/2014", "null", "null","null", "false", "false"), taskStringRepresentation(task_));
		
		task_ = parser.getTask("add do homework from 21 dec to 22/12/2014");
		testParserOutput(String.format(TASKSTRINGFORMAT, "do homework", "21/12/2014",
				"22/12/2014", "null", "null","null", "false", "false"), taskStringRepresentation(task_));
		
		task_ = parser.getTask("add do homework from 21/12/2014 to 22 dec");
		testParserOutput(String.format(TASKSTRINGFORMAT, "do homework", "21/12/2014",
				"22/12/2014", "null", "null","null", "false", "false"), taskStringRepresentation(task_));
		
		//testing "from" and "to" for 2 time interval
		task_ = parser.getTask("add do homework from 21/12/2014 2pm to 22/12/2014 3pm");
		testParserOutput(String.format(TASKSTRINGFORMAT, "do homework", "21/12/2014",
				"22/12/2014", "1400", "1500","null", "false", "false"), taskStringRepresentation(task_));
		
		task_ = parser.getTask("add do homework from 21 dec 2am to 22 dec 3am");
		testParserOutput(String.format(TASKSTRINGFORMAT, "do homework", "21/12/2014",
				"22/12/2014", "0200", "0300","null", "false", "false"), taskStringRepresentation(task_));
		
		task_ = parser.getTask("add do homework from 21 dec 2pm to 22 dec 3pm");
		testParserOutput(String.format(TASKSTRINGFORMAT, "do homework", "21/12/2014",
				"22/12/2014", "1400", "1500","null", "false", "false"), taskStringRepresentation(task_));
		
		task_ = parser.getTask("add do homework from 21st dec 2pm to 22nd dec 3pm");
		testParserOutput(String.format(TASKSTRINGFORMAT, "do homework", "21/12/2014",
				"22/12/2014", "1400", "1500","null", "false", "false"), taskStringRepresentation(task_));
		
		task_ = parser.getTask("add do homework from 21/12/2014 2pm to 22 dec 3pm");
		testParserOutput(String.format(TASKSTRINGFORMAT, "do homework", "21/12/2014",
				"22/12/2014", "1400", "1500","null", "false", "false"), taskStringRepresentation(task_));
		
		task_ = parser.getTask("add do homework from 21 dec 2pm to 22/12/2014 3pm");
		testParserOutput(String.format(TASKSTRINGFORMAT, "do homework", "21/12/2014",
				"22/12/2014", "1400", "1500","null", "false", "false"), taskStringRepresentation(task_));
		
		task_ = parser.getTask("add do homework from 21/12/2014 2pm to 22nd dec 3pm");
		testParserOutput(String.format(TASKSTRINGFORMAT, "do homework", "21/12/2014",
				"22/12/2014", "1400", "1500","null", "false", "false"), taskStringRepresentation(task_));
		
		task_ = parser.getTask("add do homework from 21st dec 2pm to 22/12/2014 3pm");
		testParserOutput(String.format(TASKSTRINGFORMAT, "do homework", "21/12/2014",
				"22/12/2014", "1400", "1500","null", "false", "false"), taskStringRepresentation(task_));
		
		//testing special cases	
		task_ = parser.getTask("add watch day after" + STRING_QUOTE + "tmr" + STRING_QUOTE + "by 21/12/2014");
		testParserOutput(String.format(TASKSTRINGFORMAT, "watch day after" + STRING_QUOTE + "tmr" + STRING_QUOTE, "null",
				"null", "null", "null","21/12/2014", "false", "false"), taskStringRepresentation(task_));
		
		task_ = parser.getTask("add" + STRING_QUOTE + " watch day after tmr" + STRING_QUOTE + "by 21/12/2014");
		testParserOutput(String.format(TASKSTRINGFORMAT, STRING_QUOTE + "watch day after tmr" + STRING_QUOTE, "null",
				"null", "null", "null","21/12/2014", "false", "false"), taskStringRepresentation(task_));
		
	}
	
	private void testParserOutput(String expected, String actual){
		assertEquals(expected, actual);
	}
	
	// return a string representation of the task_
	private String taskStringRepresentation(Task task_) {
		return String.format(TASKSTRINGFORMAT, task_.getTaskDescription(),
				task_.getStartDate(), task_.getEndDate(),
				task_.getStartTime(), task_.getEndTime(),task_.getDeadLine(),
				task_.isDone(), task_.isUrgent());
	}
}
