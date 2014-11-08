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

	private Storage storage;
	private CommandExecutor executor;
	private ObservableList<ModelTask> taskList;

	@Before
	public void setUp() {
		try {
			storage = new Storage("test.txt");
		} catch (IOException e) {
			System.out.println("this is not possible haha");
		}

		taskList = storage.getListFromFile();
		executor = new CommandExecutor(taskList, storage);
	}

	@Test
	public void test() {
		// display initial storage
		String display = "";
		for (int i = 0; i < taskList.size(); i++) {
			display = taskList.get(i).toString();
		}
		oneTest("testing original file content when loading", "", display);

		// testing adding and display what has been added to text file
		executor.executeCommand("add tasknumber1");
		taskList = executor.getAllList();
		for (int i = 0; i < taskList.size(); i++) {
			display += taskList.get(i).toString() + "\n";
		}
		oneTest("testing adding a task and displaying it",
				"tasknumber1;null;null;null;null;null;false;false\n", display);

	}

	@After
	public void tearDown() {
		executor.executeCommand("clear");
	}

	private void oneTest(String description, String expected, String actual) {
		assertEquals(description, expected, actual);
	}

}
