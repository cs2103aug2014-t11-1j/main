package test;
import static org.junit.Assert.assertEquals;
import logic.Action;
import logic.CommandType;
import org.junit.Test;

//@author A0110567L
/**
* this test case will test Action.java in the package logic. 
*/

public class TestLogicAction {

	String commandWord = "";
	Action action;
	CommandType commandType;

	@Test
	public void test() {

		// testing add
		action = new Action("add");
		setCommandType(CommandType.ADD);
		execute(commandType, action.getCommandType());

		// testing display
		action = new Action("display");
		setCommandType(CommandType.DISPLAY);
		execute(commandType, action.getCommandType());

		// testing delete
		action = new Action("delete");
		setCommandType(CommandType.DELETE);
		execute(commandType, action.getCommandType());

		// testing clear
		action = new Action("clear");
		setCommandType(CommandType.CLEAR);
		execute(commandType, action.getCommandType());

		// testing undo
		action = new Action("undo");
		setCommandType(CommandType.UNDO);
		execute(commandType, action.getCommandType());

		// testing redo
		action = new Action("redo");
		setCommandType(CommandType.REDO);
		execute(commandType, action.getCommandType());

		// testing edit
		action = new Action("edit");
		setCommandType(CommandType.EDIT);
		execute(commandType, action.getCommandType());

		// testing sort
		action = new Action("sort");
		setCommandType(CommandType.SORT);
		execute(commandType, action.getCommandType());

		// testing move
		action = new Action("move");
		setCommandType(CommandType.MOVE);
		execute(commandType, action.getCommandType());

		// testing search
		action = new Action("search");
		setCommandType(CommandType.SEARCH);
		execute(commandType, action.getCommandType());

		// testing mark_done
		action = new Action("did");
		setCommandType(CommandType.MARK_DONE);
		execute(commandType, action.getCommandType());

		// testing help
		action = new Action("help");
		setCommandType(CommandType.HELP);
		execute(commandType, action.getCommandType());

		// testing invalid
		action = new Action("invalid");
		setCommandType(CommandType.INVALID);
		execute(commandType, action.getCommandType());

		// testing exit
		action = new Action("exit");
		setCommandType(CommandType.EXIT);
		execute(commandType, action.getCommandType());

	}

	public void execute(CommandType expected, CommandType actual) {
		assertEquals(expected, actual);
	}

	private void setCommandType(CommandType type) {
		commandType = type;
	}

}
