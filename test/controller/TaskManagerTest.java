package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jireh
 */
public class TaskManagerTest {

    CommandGenerator cg;
    TaskManager tm;

    @Test
    public void test() throws IOException {

        //testing add
        testAddTrue("adding single word", "hello");
        testAddTrue("adding multiple words", "hello there how are you doing today? the weather is lovely isn't it?");
        testAddFalse("adding null", null);
        testAddFalse("adding empty string", "");
        testDisplay("1. hello\n2. hello there how are you doing today? the weather is lovely isn't it?\n");

        //testing clear
        testClearTrue("clearing all tasks");
        testDisplay("List is empty");

        //testing delete
        tm.add("apple");
        tm.add("orange");
        tm.add("banana");
        tm.add("kiwi");
        tm.add("watermelon");
        testDeleteTrue("deleting item at index 3", 3);
        testDisplay("1. apple\n2. orange\n3. kiwi\n4. watermelon\n");
        testDeleteFalse("deleting from bad index 0", 0);
        testDeleteFalse("deleting from bad index outside available tasks", 10);

        //testing edit
        testEditTrue("editing apple to pineapple", 1, "pineapple");
        testDisplay("1. pineapple\n2. orange\n3. kiwi\n4. watermelon\n");
        testEditFalse("editing from bad index", 0, "this is a bad edit");
        testEditFalse("editing from bad index", 10, "this is a bad edit");
        
        //testing mark done
    }

    public void testAddTrue(String description, String command) throws IOException {
        Assert.assertTrue(description, tm.add(command));
    }

    public void testAddFalse(String description, String command) throws IOException {
        Assert.assertFalse(description, tm.add(command));
    }

    public void testClearTrue(String description) throws IOException {
        Assert.assertTrue(description, tm.clear());
    }

    public void testDeleteTrue(String description, int num) throws IOException {
        Assert.assertTrue(description, tm.delete(num));
    }

    public void testDeleteFalse(String descripion, int num) throws IOException {
        Assert.assertFalse(descripion, tm.delete(num));
    }

    public void testEditTrue(String description, int num, String newTaskDescription) throws IOException {
        Assert.assertTrue(description, tm.edit(num, newTaskDescription));
    }

    public void testEditFalse(String description, int num, String newTaskDescription) throws IOException {
        Assert.assertFalse(description, tm.edit(num, newTaskDescription));
    }

    public void testMarkDoneTrue(String description, int num) throws IOException {
        Assert.assertTrue(description, tm.markDone(num));
    }

    public void testDisplay(String expected) throws FileNotFoundException {
        Assert.assertEquals(expected, tm.display());
    }

    @Before
    public void setUp() throws IOException {
        cg = new CommandGenerator();
        tm = new TaskManager("text.txt");
    }

    @After
    public void tearDown() throws IOException {
        tm.clear();
    }
}
