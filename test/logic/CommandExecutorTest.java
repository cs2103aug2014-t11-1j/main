/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import storage.ModelTask;
import storage.Storage;

/**
 *
 * @author Jireh
 */
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
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTaskList method, of class CommandExecutor.
     */
    @Test
    public void testSetTaskList() {
        System.out.println("setTaskList");
        ObservableList<ModelTask> taskList = null;
        CommandExecutor.setTaskList(taskList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTempList method, of class CommandExecutor.
     */
    @Test
    public void testSetTempList() {
        System.out.println("setTempList");
        ObservableList<ModelTask> searchList = null;
        CommandExecutor.setTempList(searchList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCommandWord method, of class CommandExecutor.
     */
    @Test
    public void testGetCommandWord() {
        System.out.println("getCommandWord");
        CommandExecutor instance = null;
        String expResult = "";
        String result = instance.getCommandWord();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActualCommandDescription method, of class CommandExecutor.
     */
    @Test
    public void testGetActualCommandDescription() {
        System.out.println("getActualCommandDescription");
        CommandExecutor instance = null;
        String expResult = "";
        String result = instance.getActualCommandDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserFeedBack method, of class CommandExecutor.
     */
    @Test
    public void testGetUserFeedBack() {
        System.out.println("getUserFeedBack");
        CommandExecutor instance = null;
        String expResult = "";
        String result = instance.getUserFeedBack();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGuiFeedBack method, of class CommandExecutor.
     */
    @Test
    public void testGetGuiFeedBack() {
        System.out.println("getGuiFeedBack");
        CommandExecutor instance = null;
        int expResult = 0;
        int result = instance.getGuiFeedBack();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllList method, of class CommandExecutor.
     */
    @Test
    public void testGetAllList() {
        System.out.println("getAllList");
        CommandExecutor instance = null;
        ObservableList<ModelTask> expResult = null;
        ObservableList<ModelTask> result = instance.getAllList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSearchedList method, of class CommandExecutor.
     */
    @Test
    public void testGetSearchedList() {
        System.out.println("getSearchedList");
        CommandExecutor instance = null;
        ObservableList<ModelTask> expResult = null;
        ObservableList<ModelTask> result = instance.getSearchedList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of executeCommand method, of class CommandExecutor.
     */
    @Test
    public void testExecuteCommand() {
        System.out.println("executeCommand");
        String rawInput = "";
        CommandExecutor instance = null;
        instance.executeCommand(rawInput);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
