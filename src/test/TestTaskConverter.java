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
	private TaskConverter taskConverter_;
	private Task testTask_;
	private ModelTask task_;

	private Date date_;
	private Calendar cal_ = Calendar.getInstance();

	//inputs in standard formats for parser
	private String startDate_ = "24/09/2014";
	private String endDate_ = "25/09/2014";
	private String startTime_ = "1200";
	private String endTime_ = "1300";

	//Calendars for checking the dates
	private Calendar dateStart_;
	private Calendar dateEnd_;

	@Before
	public void setUp() {
		taskConverter_ = TaskConverter.getInstance();

		dateStart_ = Calendar.getInstance();
		dateStart_.set(Calendar.YEAR, 2014);
		dateStart_.set(Calendar.MONTH, Calendar.SEPTEMBER);
		dateStart_.set(Calendar.DAY_OF_MONTH, 24);
		dateStart_.set(Calendar.HOUR_OF_DAY, 12);

		dateEnd_ = Calendar.getInstance();
		dateEnd_.set(Calendar.YEAR, 2014);
		dateEnd_.set(Calendar.MONTH, Calendar.SEPTEMBER);
		dateEnd_.set(Calendar.DAY_OF_MONTH, 25);
		dateEnd_.set(Calendar.HOUR_OF_DAY, 13);
	}

	@Test
	public void testConvertBasic() {
		//task with only the task description  
		testTask_ = new Task("test basic", null, null, null, null, null);
		task_ = taskConverter_.convert(testTask_, 1);
		assertEquals("task description should be the same",  "test basic", task_.getEvent());
		assertEquals("task position should be the given", 1, task_.getPosition());
	}

	@Test 
	public void testConvertDate(){
		//task with one date at startDate
		testTask_ = new Task("test startDate", startDate_, null, null, null, null);
		task_ = taskConverter_.convert(testTask_, 1);

		assertNotEquals("startDate should be set", null, task_.getStartDate());
		assertEquals("no endDate should be set", null, task_.getEndDate());
		assertEquals("no startTime should be set", null, task_.getStartTime());
		assertEquals("no endTime should be set", null, task_.getEndTime());

		checkStartDate();
	}


	@Test
	public void testConvertTime(){
		//task with startDate at endDate
		//task with time period
		testTask_ = new Task("test time", null, startTime_, startDate_, null, null);
		task_ = taskConverter_.convert(testTask_, 1);

		assertNotEquals("startDate should be set", null, task_.getStartDate());
		assertEquals("no endDate should be set",  null, task_.getEndDate());
		assertNotEquals("startTime should be set", null, task_.getStartTime());
		assertEquals("no endTime should be set", null, task_.getEndTime());

		checkStartDate();
		checkStartTime();
	}

	@Test
	public void testConvertTimePeriod(){
		//task with startDate at deadline
		//task with time period
		testTask_ = new Task("test timePeriod", null, startTime_, null, endTime_, startDate_);
		task_ = taskConverter_.convert(testTask_, 1);

		assertNotEquals("startDate should be set", null, task_.getStartDate());
		assertEquals("no endDate should be set",  null, task_.getEndDate());
		assertNotEquals("startTime should be set", null, task_.getStartTime());
		assertNotEquals("endTime should be set", null, task_.getEndTime());

		checkStartDate();
		checkStartTime();
		checkEndTime();
	}

	@Test
	public void testConvertTwoDates(){
		//task with endDate at endDate
		//task with time period
		testTask_ = new Task("test twoDates", startDate_, startTime_, endDate_, endTime_, null);
		task_ = taskConverter_.convert(testTask_, 1);

		assertNotEquals("startDate should be set", null, task_.getStartDate());
		assertNotEquals("endDate should be set",  null, task_.getEndDate());
		assertNotEquals("startTime should be set", null, task_.getStartTime());
		assertNotEquals("endTime should be set", null, task_.getEndTime());

		checkStartDate();
		checkEndDate();
		checkStartTime();
		checkEndTime();
	}
	
	private void checkStartDate() {
		date_ = task_.getStartDate();
		cal_.setTime(date_);

		assertEquals("startDate should be correct", 
				dateStart_.get(Calendar.DATE), cal_.get(Calendar.DATE));
		assertEquals("startDate should be correct", 
				dateStart_.get(Calendar.MONTH), cal_.get(Calendar.MONTH));
		assertEquals("startDate should be correct", 
				dateStart_.get(Calendar.YEAR), cal_.get(Calendar.YEAR));
	}
	
	private void checkEndDate() {
		date_ = task_.getEndDate();
		cal_.setTime(date_);
		
		assertEquals("endDate should be correct", 
				dateEnd_.get(Calendar.DATE), cal_.get(Calendar.DATE));
		assertEquals("endDate should be correct", 
				dateEnd_.get(Calendar.MONTH), cal_.get(Calendar.MONTH));
		assertEquals("endDate should be correct", 
				dateEnd_.get(Calendar.YEAR), cal_.get(Calendar.YEAR));
	}
	
	private void checkStartTime() {
		date_ = task_.getStartTime();
		cal_.setTime(date_);

		assertEquals("startTime should be correct", 
				dateStart_.get(Calendar.HOUR_OF_DAY), cal_.get(Calendar.HOUR_OF_DAY));
	}
	
	private void checkEndTime() {
		date_ = task_.getEndTime();
		cal_.setTime(date_);

		assertEquals("endTime should be correct", 
				dateEnd_.get(Calendar.HOUR_OF_DAY), cal_.get(Calendar.HOUR_OF_DAY));
	}
}
