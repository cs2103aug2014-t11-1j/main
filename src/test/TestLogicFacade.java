package test;

import static org.junit.Assert.*;
import javafx.collections.ObservableList;
import logic.LogicFacade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.ModelTask;

//@author A0110567L
/**
 * this Junit test will test the LogicFacade class. note: the list is cleared
 * before and after the whole test
 */
public class TestLogicFacade {

	private static final String TASKSTRINGFORMAT = "event: " + "%1$s" + "\n"
			+ "start date: " + "%2$s" + "\n" + "end date: " + "%3$s" + "\n"
			+ "start time: " + "%4$s" + "\n" + "end time: " + "%5$s" + "\n"
			+ "is done: " + "%6$s" + "\n" + "is urgent: " + "%7$s" + "\n";

	private LogicFacade logic_;

	@Before
	public void initialize() throws Exception {
		logic_ = LogicFacade.getInstance();
		// clear the list in the logic_ before testing
		logic_.executeCommand("clear");
	}

	@Test
	public void testBasic() throws Exception {

		// boundary case: user types nothing, then program should display error
		testLogicFeedBack("INVALID COMMAND!", "");
		testListSize(0);
	}

	@Test
	public void testClear() throws Exception {
		// clear list when the task has 2 tasks
		logic_.executeCommand("add task1");
		logic_.executeCommand("add task2");
		testLogicFeedBack("List cleared", "clear");
		testListSize(0);
		// boundary case: clear task when list is empty
		testLogicFeedBack("List cleared", "clear");
		testListSize(0);

		logic_.executeCommand("clear");
	}

	@Test
	public void testAddFloatingTask() throws Exception {

		// adding tasks without dates and times
		testLogicFeedBack("\"go to school\" added", "add go to school");
		testListSize(1);
		testLogicOutput(String.format(TASKSTRINGFORMAT, "go to school", "null",
				"null", "null", "null", "false", "false"), 1);
		logic_.executeCommand("clear");
	}

	@Test
	public void testAddTimedTaskOneDate() throws Exception {
		// adding tasks with 1 date
		// boundary case: using keyword "by"
		testLogicFeedBack("\"do homework\" added",
				"add do homework by 11/11/2014");
		testListSize(1);
		testLogicOutput(String.format(TASKSTRINGFORMAT, "do homework",
				"11/11/2014", "null", "23:59", "null", "false", "false"), 1);
		logic_.executeCommand("clear");
	}

	@Test
	public void testAddTimedTaskTwoDate() throws Exception {
		// adding task with 2 dates
		// using keyword "from" and "to"
		testLogicFeedBack("\"go to zoo\" added",
				"add go to zoo from 11/11/2014 to 12/12/2014");
		testListSize(1);
		testLogicOutput(String.format(TASKSTRINGFORMAT, "go to zoo",
				"11/11/2014", "12/12/2014", "null", "null", "false", "false"),
				1);
		logic_.executeCommand("clear");
	}

	@Test
	public void testAddTimedTaskWithTime() throws Exception {
		// adding task with a time
		// boundary : adding a date using keyword "on" and "at"
		testLogicFeedBack("\"watch ipman with girlfriend\" added",
				"add watch ipman with girlfriend on 11/11/2014 at 8am");
		testListSize(1);
		testLogicOutput(String.format(TASKSTRINGFORMAT,
				"watch ipman with girlfriend", "11/11/2014", "null", "08:00",
				"null", "false", "false"), 1);
		logic_.executeCommand("clear");
	}

	@Test
	public void testAddTimedWithTwoDateTwoTime() throws Exception {
		// adding task with 2 dates and 2 time using keyword "from" and "to"
		testLogicFeedBack("\"practice kungfu fighting\" added",
				"add practice kungfu fighting from 12/11/2014 to 13/11/2014"
						+ " from 8am to 9am");
		testListSize(1);
		testLogicOutput(String.format(TASKSTRINGFORMAT,
				"practice kungfu fighting", "12/11/2014", "13/11/2014", "08:00",
				"09:00", "false", "false"), 1);

		logic_.executeCommand("clear");
	}

	@Test
	public void testAddMultiple() throws Exception {
		// adding task with 2 dates and 2 time using keyword "from" and "to"
		testLogicFeedBack("\"a\" added", "add a");
		testLogicFeedBack("\"b\" added", "add b");
		testLogicFeedBack("\"c\" added", "add c");
		testLogicFeedBack("\"d\" added", "add d");
		testLogicFeedBack("\"e\" added", "add e");
		testLogicFeedBack("\"f\" added", "add f");
		testLogicFeedBack("\"g\" added", "add g");
		testLogicFeedBack("\"h\" added", "add h");
		testLogicFeedBack("\"i\" added", "add i");
		testLogicFeedBack("\"j\" added", "add j");
		testLogicFeedBack("\"k\" added", "add k");
		testListSize(11);
		testLogicOutput(String.format(TASKSTRINGFORMAT, "a", "null", "null",
				"null", "null", "false", "false"), 1);
		testLogicOutput(String.format(TASKSTRINGFORMAT, "b", "null", "null",
				"null", "null", "false", "false"), 2);
		testLogicOutput(String.format(TASKSTRINGFORMAT, "c", "null", "null",
				"null", "null", "false", "false"), 3);
		testLogicOutput(String.format(TASKSTRINGFORMAT, "d", "null", "null",
				"null", "null", "false", "false"), 4);
		testLogicOutput(String.format(TASKSTRINGFORMAT, "e", "null", "null",
				"null", "null", "false", "false"), 5);
		testLogicOutput(String.format(TASKSTRINGFORMAT, "f", "null", "null",
				"null", "null", "false", "false"), 6);
		testLogicOutput(String.format(TASKSTRINGFORMAT, "g", "null", "null",
				"null", "null", "false", "false"), 7);
		testLogicOutput(String.format(TASKSTRINGFORMAT, "h", "null", "null",
				"null", "null", "false", "false"), 8);
		testLogicOutput(String.format(TASKSTRINGFORMAT, "i", "null", "null",
				"null", "null", "false", "false"), 9);
		testLogicOutput(String.format(TASKSTRINGFORMAT, "j", "null", "null",
				"null", "null", "false", "false"), 10);
		testLogicOutput(String.format(TASKSTRINGFORMAT, "k", "null", "null",
				"null", "null", "false", "false"), 11);

		logic_.executeCommand("clear");
	}

	@Test
	public void testSortAlphabetically() throws Exception {
		// bondary case: tasks start with capital or small letter and numbers
		logic_.executeCommand("add zelDa");
		logic_.executeCommand("add apple");
		logic_.executeCommand("add G-Dragon");
		logic_.executeCommand("add Apple");
		logic_.executeCommand("add bear");
		logic_.executeCommand("add Boss");
		logic_.executeCommand("add xxx");
		logic_.executeCommand("add 12233");
		logic_.executeCommand("add 230");
		testListSize(9);
		logic_.executeCommand("sort alpha");
		testLogicOutput(String.format(TASKSTRINGFORMAT, "12233", "null",
				"null", "null", "null", "false", "false"), 8);
		testLogicOutput(String.format(TASKSTRINGFORMAT, "230", "null", "null",
				"null", "null", "false", "false"), 9);
		testLogicOutput(String.format(TASKSTRINGFORMAT, "Apple", "null",
				"null", "null", "null", "false", "false"), 4);
		testLogicOutput(String.format(TASKSTRINGFORMAT, "apple", "null",
				"null", "null", "null", "false", "false"), 2);
		testLogicOutput(String.format(TASKSTRINGFORMAT, "Boss", "null", "null",
				"null", "null", "false", "false"), 6);
		testLogicOutput(String.format(TASKSTRINGFORMAT, "bear", "null", "null",
				"null", "null", "false", "false"), 5);
		testLogicOutput(String.format(TASKSTRINGFORMAT, "G-Dragon", "null",
				"null", "null", "null", "false", "false"), 3);
		testLogicOutput(String.format(TASKSTRINGFORMAT, "xxx", "null", "null",
				"null", "null", "false", "false"), 7);
		testLogicOutput(String.format(TASKSTRINGFORMAT, "zelDa", "null",
				"null", "null", "null", "false", "false"), 1);
		logic_.executeCommand("clear");
	}

	@Test
	public void testAddUrgentTask() throws Exception {

		testLogicFeedBack("\"task1\" added", "add! task1");
		testListSize(1);
		logic_.executeCommand("clear");
	}

	@Test
	public void testDelete() throws Exception {
		// testing delete when list contains 1 task
		logic_.executeCommand("add go to school");
		testLogicFeedBack("Delete successful", "delete 1");
		testListSize(0);

		// boundary case: testing delete when list contains no task/empty
		testLogicFeedBack("Delete unsuccessful", "delete 1");
		testListSize(0);

		// boundary case: testing delete an item that is not found in the list
		// sample case eg: delete task 3 when the list only has 2 tasks
		logic_.executeCommand("add task1");
		logic_.executeCommand("add task2");
		testLogicFeedBack("Delete unsuccessful", "delete 3");
		testListSize(2);
		logic_.executeCommand("clear");
	}

	@Test
	public void testUndoRedo() throws Exception {
		// undo and redo for adding one task
		logic_.executeCommand("add go to school");
		testLogicFeedBack("Action undone", "undo");
		testListSize(0);
		testLogicOutput(String.format(TASKSTRINGFORMAT, "", "null", "null",
				"null", "null", "false", "false"), 0);
		testLogicFeedBack("Action redone", "redo");
		testListSize(1);
		testLogicOutput(String.format(TASKSTRINGFORMAT, "go to school", "null",
				"null", "null", "null", "false", "false"), 1);

		logic_.executeCommand("clear");
	}

	@Test
	public void testMarkDone() throws Exception {
		// mark the first task as done
		logic_.executeCommand("add go to school");
		testLogicFeedBack("Task done", "did 1");
		testListSize(1);
		testLogicOutput(String.format(TASKSTRINGFORMAT, "go to school", "null",
				"null", "null", "null", "true", "false"), 1);

		logic_.executeCommand("clear");
	}

	@Test
	public void testUnMarkDone() throws Exception {
		// unmark the first task as done
		logic_.executeCommand("add go to school");
		testLogicFeedBack("Task done", "did 1");
		testListSize(1);
		testLogicFeedBack("Task undone", "undid 1");
		testLogicOutput(String.format(TASKSTRINGFORMAT, "go to school", "null",
				"null", "null", "null", "false", "false"), 1);

		logic_.executeCommand("clear");
	}

	@Test
	public void testSimpleSearch() throws Exception {
		// search when nothing is found
		logic_.executeCommand("add apple");
		logic_.executeCommand("add pear");
		logic_.executeCommand("add pineapple");
		testLogicFeedBack("Search unsuccessful", "search 1");

		// search is found
		logic_.executeCommand("add apple");
		testSearchOutput(String.format(TASKSTRINGFORMAT, "apple", "null",
				"null", "null", "null", "false", "false"), 1);
		testSearchOutput(String.format(TASKSTRINGFORMAT, "pineapple", "null",
				"null", "null", "null", "false", "false"), 3);

		logic_.executeCommand("clear");
	}

	@Test
	public void testEdit() throws Exception {
		logic_.executeCommand("add go to school");
		// edit tasks without dates and times
		testLogicFeedBack("Task edited", "edit 1 do not go to school");
		testListSize(1);
		testLogicOutput(String.format(TASKSTRINGFORMAT, "do not go to school",
				"null", "null", "null", "null", "false", "false"), 1);

		// edit tasks with one date
		// boundary case: edit the date from dd/mm/yyyy format to dd MMM YYYY
		// format
		logic_.executeCommand("add Ironman is destroying the city on 17/12/2015");
		testLogicFeedBack("Task edited",
				"edit 2 Ironman is destroying the city on 18 dec 2015");
		testListSize(2);
		testLogicOutput(String.format(TASKSTRINGFORMAT,
				"Ironman is destroying the city", "18/12/2015", "null", "null",
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
		logic_.executeCommand("clear");

	}

	/**
	 * insert other function here for testing
	 */

	// checking whether the feedback message is correct
	public void testLogicFeedBack(String expected, String userInput)
			throws Exception {
		logic_.executeCommand(userInput);
		assertEquals(expected, logic_.getUserFeedBack());
	}

	// checking whether the size of the list is correct after performing various
	// operations
	public void testListSize(int expectedSize) {
		ObservableList<ModelTask> result = logic_.getAllList();
		assertEquals(expectedSize, result.size());
	}

	// checking whether the task is correct after every operation
	private void testLogicOutput(String expected, int taskNumber) {
		if (taskNumber > 0) {
			ObservableList<ModelTask> allList = logic_.getAllList();
			for (int i = 0; i < allList.size(); i++) {
				ModelTask currTask = allList.get(i);
				if (taskNumberIsCorrect(taskNumber, currTask.getPosition())) {
					assertEquals(expected, TaskStringRepresentation(currTask));
				}
			}
		}
	}

	// checking whether the task is correct after every operation
	private void testSearchOutput(String expected, int taskNumber) {
		if (taskNumber > 0) {
			ObservableList<ModelTask> allList = logic_.getSearchedList();
			for (int i = 0; i < allList.size(); i++) {
				ModelTask currTask = allList.get(i);
				if (taskNumberIsCorrect(taskNumber, currTask.getPosition())) {
					assertEquals(expected, TaskStringRepresentation(currTask));
				}
			}
		}
	}

	private boolean taskNumberIsCorrect(int taskIndex, int listIndex) {
		return (taskIndex == listIndex);
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
		logic_.executeCommand("clear");
	}

}
