package test;

import static org.junit.Assert.*;
import javafx.collections.ObservableList;
import logic.LogicFacade;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import storage.ModelTask;

public class TestLogicFacade {

	private static final String TASKSTRINGFORMAT = "event: " + "%1$s" + "\n"
			+ "start date: " + "%2$s" + "\n" + "end date: " + "%3$s" + "\n"
			+ "start time: " + "%4$s" + "\n" + "end time: " + "%5$s" + "\n"
			+ "is done: " + "%6$s" + "\n" + "is urgent: " + "%7$s" + "\n";

	private LogicFacade logic;

	@Before
	public void initializa() throws Exception {
		logic = LogicFacade.getInstance();
		// clear the list in the logic before testing
		logic.executeCommand("clear");
	}

	@Test
	public void testLogicOperation() throws Exception {

		// boundary case: user types nothing, then program should display error
		testLogicFeedBack("INVALID COMMAND!", "");
		testListSize(0);
		
		
		
		/**
		 * testing add function
		 */
		// adding tasks without dates and times
		testLogicFeedBack("\"go to school\"  added", "add go to school");
		testListSize(1);
		// checking if the task is the correct task
		testLogicOutput(String.format(TASKSTRINGFORMAT, "go to school", "null",
				"null", "null", "null", "false", "false"), 1);

		
		
		/**
		 * testing delete function
		 */
		// testing delete when list contains 1 task
		testLogicFeedBack("Delete successful", "delete 1");
		testListSize(0);

		// boundary case: testing delete when list contains no task
		testLogicFeedBack("Delete unsuccessful", "delete 1");
		testListSize(0);

		// boundary case: testing delete an item that is not found in the list
		// sample case: delete task 3 when the list only has 2 tasks
		logic.executeCommand("add task1");
		logic.executeCommand("add task2");
		testLogicFeedBack("Delete unsuccessful", "delete 3");
		testListSize(2);
	}

	
	
	// checking whether the feedback message is correct
	public void testLogicFeedBack(String expected, String userInput)
			throws Exception {
		assertEquals(expected, logic.executeCommand(userInput));
	}

	// checking whether the size of the list is correct after performing various
	// operations
	public void testListSize(int expectedSize) {
		ObservableList<ModelTask> result = logic.getAllList();
		assertEquals(expectedSize, result.size());
	}

	// checking whether the task is correct after every operation
	private void testLogicOutput(String expected, int taskNumber) {
		if (taskNumber > 0) {
			assertEquals(expected, TaskStringRepresentation(logic.getAllList()
					.get(taskNumber - 1)));
		}
	}

	//return a string representation of the Task
	private String TaskStringRepresentation(ModelTask task) {
		return String.format(TASKSTRINGFORMAT, task.getEvent(),
				task.getStartDateString(), task.getEndDateString(),
				task.getStartTimeString(), task.getEndTimeString(),
				task.isDone(), task.isUrgent());
	}

	
	@After
	public void tearDown() throws Exception {
		logic.executeCommand("clear");
	}

}
