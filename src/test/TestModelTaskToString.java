package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.ModelTask;
import com.TaskConverter;
import parser.ParserFacade;

//@author A0110567L
/**
 * this test case will test ModelTaskToSaveStringConverter.java in the package
 * storage. note: the conversion string 7 parameters arranged in the following
 * format: event;start date;end date;start time;end
 * time;deadline;isdone;isurgent;
 * 
 */
public class TestModelTaskToString {

	private TaskConverter taskConverter_;
	private ParserFacade pf_ = ParserFacade.getInstance();

	@Before
	public void setUp() {
		taskConverter_ = TaskConverter.getInstance();
	}

	@Test
	public void test() {
		// testing converting to a string from a modelTask object
		ModelTask newTask = taskConverter_.convert(pf_.getTask("add "
				+ "this is crazy"), 1);
		testTaskOutputString("testing task conversion",
				"this is crazy;null;null;null;null;null;false;false",
				newTask.toString());

		// testing task with one date
		// using formate dd/mm/yyyy
		ModelTask newTask2 = taskConverter_.convert(pf_.getTask("add "
				+ "go to school on 13/12/2014"), 1);
		testTaskOutputString(
				"testing task conversion",
				"go to school;13/12/2014;null;null;null;13/12/2014;false;false",
				newTask2.toString());
	}

	private void testTaskOutputString(String description, String expected,
			String actual) {
		assertEquals(description, expected, actual);
	}

}
