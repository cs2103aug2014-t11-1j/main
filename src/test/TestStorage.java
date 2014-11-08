package test;

import static org.junit.Assert.*;
import java.io.IOException;
import javafx.collections.ObservableList;
import logic.CommandExecutor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.ModelTask;
import storage.Storage;

//@author A0110567L
/**
 * this test case will test Storage.java in the package storage. tests will be
 * conducted to test whether storage can load from the text file and to see if
 * it can save correctly to the text file
 */
public class TestStorage {

	private Storage storage_;
	private CommandExecutor executor_;
	private ObservableList<ModelTask> taskList_;
	private String display_ = "";
	
	@Before
	public void setUp() {
		try {
			storage_ = new Storage("test.txt");
		} catch (IOException e) {
			System.out.println("this is not possible haha");
		}

		taskList_ = storage_.getListFromFile();
		executor_ = new CommandExecutor(taskList_, storage_);
	}

	@Test
	public void testStorageInitialization() {
		// display initial storage
		for (int i = 0; i < taskList_.size(); i++) {
			display_ = taskList_.get(i).toString();
		}
		testOutput("testing original file content when loading", "", display_);
	}
	
	@Test
	public void testStorage() {
		// testing adding and display what has been added to text file
		executor_.executeCommand("add tasknumber1");
		executor_.executeCommand("add tasknumber2 on 12/12/2014");
		taskList_ = executor_.getAllList();
		for (int i = 0; i < taskList_.size(); i++) {
			display_ += taskList_.get(i).toString() + "\n";
		}
		testOutput("testing adding a task and displaying it",
				"tasknumber1;null;null;null;null;null;false;false\n"
				+ "tasknumber2;12/12/2014;null;null;null;12/12/2014;false;false\n", display_);

	}

	@After
	public void tearDown() {
		executor_.executeCommand("clear");
	}

	private void testOutput(String description, String expected, String actual) {
		assertEquals(description, expected, actual);
	}

}
