package test;
/**
 * @author A0116018R
 * 
 * This is the unit testing for TaskConverter class
 * This will test the various formats for tasks that might come from the Parser
 */
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.ModelTask;
import com.Task;
import com.TaskConverter;

public class TestTaskConverter {

	//common variables used in testing
	private TaskConverter taskConverter;
	private Task testTask;
	private ModelTask task;

	private Date date;
	private Calendar cal = Calendar.getInstance();

	//inputs in standard formats for parser
	private String startDate = "24/09/2014";
	private String endDate = "25/09/2014";
	private String startTime = "1200";
	private String endTime = "1300";

	//Calendars for checking the dates
	private Calendar dateStart;
	private Calendar dateEnd;

	@Before
	public void setUp() {
		taskConverter = TaskConverter.getInstance();

		dateStart = Calendar.getInstance();
		dateStart.set(Calendar.YEAR, 2014);
		dateStart.set(Calendar.MONTH, Calendar.SEPTEMBER);
		dateStart.set(Calendar.DAY_OF_MONTH, 24);
		dateStart.set(Calendar.HOUR_OF_DAY, 12);

		dateEnd = Calendar.getInstance();
		dateEnd.set(Calendar.YEAR, 2014);
		dateEnd.set(Calendar.MONTH, Calendar.SEPTEMBER);
		dateEnd.set(Calendar.DAY_OF_MONTH, 25);
		dateEnd.set(Calendar.HOUR_OF_DAY, 13);
	}

	@Test
	public void testConvertBasic() {
		//task with only the task description  
		testTask = new Task("test basic", null, null, null, null, null);
		task = taskConverter.convert(testTask, 1);
		assertEquals("task description should be the same",  "test basic", task.getEvent());
		assertEquals("task position should be the given", 1, task.getPosition());
	}

	@Test 
	public void testConvertDate(){
		//task with one date at startDate
		testTask = new Task("test startDate", startDate, null, null, null, null);
		task = taskConverter.convert(testTask, 1);

		assertNotEquals("startDate should be set", null, task.getStartDate());
		assertEquals("no endDate should be set", null, task.getEndDate());
		assertEquals("no startTime should be set", null, task.getStartTime());
		assertEquals("no endTime should be set", null, task.getEndTime());

		checkStartDate();
	}


	@Test
	public void testConvertTime(){
		//task with startDate at endDate
		//task with time period
		testTask = new Task("test time", null, startTime, startDate, null, null);
		task = taskConverter.convert(testTask, 1);

		assertNotEquals("startDate should be set", null, task.getStartDate());
		assertEquals("no endDate should be set",  null, task.getEndDate());
		assertNotEquals("startTime should be set", null, task.getStartTime());
		assertEquals("no endTime should be set", null, task.getEndTime());

		checkStartDate();
		checkStartTime();
	}

	@Test
	public void testConvertTimePeriod(){
		//task with startDate at deadline
		//task with time period
		testTask = new Task("test timePeriod", null, startTime, null, endTime, startDate);
		task = taskConverter.convert(testTask, 1);

		assertNotEquals("startDate should be set", null, task.getStartDate());
		assertEquals("no endDate should be set",  null, task.getEndDate());
		assertNotEquals("startTime should be set", null, task.getStartTime());
		assertNotEquals("endTime should be set", null, task.getEndTime());

		checkStartDate();
		checkStartTime();
		checkEndTime();
	}

	@Test
	public void testConvertTwoDates(){
		//task with endDate at endDate
		//task with time period
		testTask = new Task("test twoDates", startDate, startTime, endDate, endTime, null);
		task = taskConverter.convert(testTask, 1);

		assertNotEquals("startDate should be set", null, task.getStartDate());
		assertNotEquals("endDate should be set",  null, task.getEndDate());
		assertNotEquals("startTime should be set", null, task.getStartTime());
		assertNotEquals("endTime should be set", null, task.getEndTime());

		checkStartDate();
		checkEndDate();
		checkStartTime();
		checkEndTime();
	}
	
	private void checkStartDate() {
		date = task.getStartDate();
		cal.setTime(date);

		assertEquals("startDate should be correct", 
				dateStart.get(Calendar.DATE), cal.get(Calendar.DATE));
		assertEquals("startDate should be correct", 
				dateStart.get(Calendar.MONTH), cal.get(Calendar.MONTH));
		assertEquals("startDate should be correct", 
				dateStart.get(Calendar.YEAR), cal.get(Calendar.YEAR));
	}
	
	private void checkEndDate() {
		date = task.getEndDate();
		cal.setTime(date);
		
		assertEquals("endDate should be correct", 
				dateEnd.get(Calendar.DATE), cal.get(Calendar.DATE));
		assertEquals("endDate should be correct", 
				dateEnd.get(Calendar.MONTH), cal.get(Calendar.MONTH));
		assertEquals("endDate should be correct", 
				dateEnd.get(Calendar.YEAR), cal.get(Calendar.YEAR));
	}
	
	private void checkStartTime() {
		date = task.getStartTime();
		cal.setTime(date);

		assertEquals("startTime should be correct", 
				dateStart.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.HOUR_OF_DAY));
	}
	
	private void checkEndTime() {
		date = task.getEndTime();
		cal.setTime(date);

		assertEquals("endTime should be correct", 
				dateEnd.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.HOUR_OF_DAY));
	}
}
