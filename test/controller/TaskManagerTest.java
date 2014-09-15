package controller;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Jireh
 */
public class TaskManagerTest {

    public void test() {

    }

    @Test
    private void testCommand(String description, String command) {
        Assert.assertEquals(description, command, this);
    }
}
