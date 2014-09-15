package controller;

import java.io.IOException;
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
        testAddTrue("adding text", "hello");
        testAddFalse("adding null", null);
        testAddFalse("adding empty string", "");
    }

    public void testAddTrue(String description, String command) throws IOException {
        Assert.assertTrue(description, tm.add(command));
    }

    public void testAddFalse(String description, String command) throws IOException {
        Assert.assertFalse(description, tm.add(command));
    }

    @Before
    public void setUp() throws IOException {
        cg = new CommandGenerator();
        tm = new TaskManager("text.txt");
    }
}
