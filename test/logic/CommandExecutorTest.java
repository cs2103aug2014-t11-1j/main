//@author A0111370Y
/**
 * JUnit test for CommandExecutor.
 */
package logic;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import com.ModelTask;
import org.junit.Assert;
import storage.Storage;

public class CommandExecutorTest {

    Storage tempStorage;
    CommandExecutor ex;
    ObservableList<ModelTask> tempObservableList;

    public CommandExecutorTest() {
    }

    @Before
    public void setUp() {

        try {
            tempObservableList = FXCollections.observableArrayList();
            tempStorage = new Storage("test.txt");
            ex = new CommandExecutor(tempObservableList, tempStorage);
        } catch (IOException ex) {
            fail("Storage IOexception");
        }

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setUserFeedBack method, of class CommandExecutor.
     */
    @Test
    public void testSetUserFeedBack() {
        System.out.println("setUserFeedBack");

        String feedBack = "";
        CommandExecutor.setUserFeedBack(feedBack);
        assertEquals(ex.getUserFeedBack(), "");

        feedBack = "setting test feedback";
        CommandExecutor.setUserFeedBack(feedBack);
        assertEquals(ex.getUserFeedBack(), "setting test feedback");

        feedBack = "-123";
        CommandExecutor.setUserFeedBack(feedBack);
        assertEquals(ex.getUserFeedBack(), "-123");
    }

    /**
     * Test of setGuiFeedBack method, of class CommandExecutor.
     */
    @Test
    public void testSetGuiFeedBack() {
        System.out.println("setGuiFeedBack");
        int feedBack = 0;

        CommandExecutor.setGuiFeedBack(feedBack);
        assertEquals(ex.getGuiFeedBack(), 0);

        feedBack = 3;
        CommandExecutor.setGuiFeedBack(feedBack);
        assertEquals(ex.getGuiFeedBack(), 3);

        feedBack = 10;
        CommandExecutor.setGuiFeedBack(feedBack);
        assertEquals(ex.getGuiFeedBack(), 0);

        feedBack = -10;
        CommandExecutor.setGuiFeedBack(feedBack);
        assertEquals(ex.getGuiFeedBack(), 0);
    }

    /**
     * Test of setTaskList method, of class CommandExecutor.
     */
    @Test
    public void testSetTaskList() {
        System.out.println("setTaskList");
        ObservableList<ModelTask> taskList = FXCollections.observableArrayList();

        CommandExecutor.setTaskList(taskList);
        assertEquals(ex.getAllList(), taskList);
    }

    /**
     * Test of setTempList method, of class CommandExecutor.
     */
    @Test
    public void testSetTempList() {
        System.out.println("setTempList");
        ObservableList<ModelTask> searchList = FXCollections.observableArrayList();

        CommandExecutor.setTempList(searchList);
        assertEquals(ex.getSearchedList(), searchList);
    }

}
