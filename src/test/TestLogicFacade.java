/**
 * 
 * @author zhang
 * 
 * this Junit test will test the LogicFacade class. 
 * 
 * note: the list is cleared before and after the whole test
 * 
 * 
 * for each test, we will check:
 * 1. if feedback message is correct
 * 2. the the list size is correct
 * 3. if the tasks are correct (i.e: if dates, time, event are added or modified correctly)
 * after every method is checked, the list is cleared before testing the next method
 */

package test;

import static org.junit.Assert.*;
import javafx.collections.ObservableList;
import logic.LogicFacade;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ModelTask;

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
		 * testing clear function
		 */
		// clear list when the task has 2 tasks
		logic.executeCommand("add task1");
		logic.executeCommand("add task2");
		testLogicFeedBack("List cleared", "clear");
		testListSize(0);
		// boundary case: clear task when list is empty
		testLogicFeedBack("List cleared", "clear");
		testListSize(0);

		logic.executeCommand("clear");

		/**
		 * testing add function
		 */
		// adding tasks without dates and times
		testLogicFeedBack("\"go to school\"  added", "add go to school");
		testListSize(1);
		testLogicOutput(String.format(TASKSTRINGFORMAT, "go to school", "null",
				"null", "null", "null", "false", "false"), 1);
		// adding tasks with 1 date
		// boundary case: using keyword "by"
		testLogicFeedBack("\"do homework\"  added",
				"add do homework by 11/11/2014");
		testListSize(2);
		testLogicOutput(String.format(TASKSTRINGFORMAT, "do homework",
				"11/11/2014", "null", "2359", "null", "false", "false"), 2);

		// adding task with 2 dates
		// using keyword "from" and "to"
		testLogicFeedBack("\"go to zoo\"  added",
				"add go to zoo from 11/11/2014 to 12/12/2014");
		testListSize(3);
		testLogicOutput(String.format(TASKSTRINGFORMAT, "go to zoo",
				"11/11/2014", "12/12/2014", "null", "null", "false", "false"),
				3);

		// adding task with a time
		// boundary : adding a date using keyword "on" and "at"
		testLogicFeedBack("\"watch ipman with girlfriend\"  added",
				"add watch ipman with girlfriend on 11/11/2014 at 8am");
		testListSize(4);
		testLogicOutput(String.format(TASKSTRINGFORMAT,
				"watch ipman with girlfriend", "11/11/2014", "null", "0800",
				"null", "false", "false"), 4);

		// adding task with 2 dates and 2 time using keyword "from" and "to"
		testLogicFeedBack("\"practice kungfu fighting\"  added",
				"add practice kungfu fighting from 12/11/2014 to 13/11/2014"
						+ " from 8am to 9am");
		testListSize(5);
		testLogicOutput(String.format(TASKSTRINGFORMAT,
				"practice kungfu fighting", "12/11/2014", "13/11/2014", "0800",
				"0900", "false", "false"), 5);

		logic.executeCommand("clear");

		/**
		 * testing delete function
		 */
		// testing delete when list contains 1 task
		logic.executeCommand("add go to school");
		testLogicFeedBack("Delete successful", "delete 1");
		testListSize(0);

		// boundary case: testing delete when list contains no task/empty
		testLogicFeedBack("Delete unsuccessful", "delete 1");
		testListSize(0);

		// boundary case: testing delete an item that is not found in the list
		// sample case eg: delete task 3 when the list only has 2 tasks
		logic.executeCommand("add task1");
		logic.executeCommand("add task2");
		testLogicFeedBack("Delete unsuccessful", "delete 3");
		testListSize(2);
		logic.executeCommand("clear");

		/**
		 * testing edit function
		 */

		logic.executeCommand("add go to school");
		// edit tasks without dates and times
		testLogicFeedBack("Task edited", "edit 1 do not go to school");
		testListSize(1);
		testLogicOutput(String.format(TASKSTRINGFORMAT, "do not go to school",
				"null", "null", "null", "null", "false", "false"), 1);

		// edit tasks with one date
		// boundary case: edit the date from dd/mm/yyyy format to dd MMM YYYY
		// format
		logic.executeCommand("add Ironman is destroying the city on 17/12/2015");
		testLogicFeedBack("Task edited",
				"edit 2 Ironman is destroying the city on 18 dec 2015");
		testListSize(2);
		testLogicOutput(String.format(TASKSTRINGFORMAT,
				"Ironman is destroying the city", "18/12/2015", "null", "2359",
				"null", "false", "false"), 2);

		// boundary case: edit task by expanding time or dates
		testLogicFeedBack("Task edited",
				"edit 2 Ironman is destroying the city from 22/05/2015 to 29/06/2015");
		testListSize(2);
		testLogicOutput(String.format(TASKSTRINGFORMAT,
				"Ironman is destroying the city", "22/05/2015", "29/06/2015",
				"null", "null", "false", "false"), 2);

		// boundary case: edit task by removing time and dates
		testLogicFeedBack("Task edited",
				"edit 2 Ironman is destroying the city");
		testListSize(2);
		testLogicOutput(String.format(TASKSTRINGFORMAT,
				"Ironman is destroying the city", "null", "null", "null",
				"null", "false", "false"), 2);
		logic.executeCommand("clear");

		/**
		 * insert other function here for testing
		 */

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

	// return a string representation of the Task
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