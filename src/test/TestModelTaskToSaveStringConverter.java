package test;

/**
 * @author Zhang Yongkai
 * this test case will test ModelTaskToSaveStringConverter.java in the package storage. 
 * 
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import parser.ParserFacade;
import storage.ModelTask;
import storage.ModelTaskToSaveStringConverter;
import storage.TaskConverter;

public class TestModelTaskToSaveStringConverter {

	public ModelTaskToSaveStringConverter converter;
	TaskConverter taskConverter;
	ParserFacade pf = ParserFacade.getInstance();

	@Before
	public void setUp() {
		converter = new ModelTaskToSaveStringConverter();
		taskConverter = TaskConverter.getInstance();
	}

	@Test
	public void test() {
		// testing converting to a string from a modelTask object
		ModelTask newTask = taskConverter.convert(pf.getTask("add "
				+ "this is crazy"), 1);
		oneTest("testing task conversion",
				"this is crazy;null;null;null;null;null;false",
				converter.toSave(newTask));
	}

	private void oneTest(String description, String expected, String actual) {
		assertEquals(description, expected, actual);
	}

}
