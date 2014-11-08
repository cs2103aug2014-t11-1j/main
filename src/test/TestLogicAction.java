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

	
	private Action action_;
	private CommandType commandType_;

	@Test
	public void test() {

		// testing add
		action_ = new Action("add");
		setCommandType(CommandType.ADD);
		execute(commandType_, action_.getCommandType());

		// testing display
		action_ = new Action("display");
		setCommandType(CommandType.DISPLAY);
		execute(commandType_, action_.getCommandType());

		// testing delete
		action_ = new Action("delete");
		setCommandType(CommandType.DELETE);
		execute(commandType_, action_.getCommandType());

		// testing clear
		action_ = new Action("clear");
		setCommandType(CommandType.CLEAR);
		execute(commandType_, action_.getCommandType());

		// testing undo
		action_ = new Action("undo");
		setCommandType(CommandType.UNDO);
		execute(commandType_, action_.getCommandType());

		// testing redo
		action_ = new Action("redo");
		setCommandType(CommandType.REDO);
		execute(commandType_, action_.getCommandType());

		// testing edit
		action_ = new Action("edit");
		setCommandType(CommandType.EDIT);
		execute(commandType_, action_.getCommandType());

		// testing sort
		action_ = new Action("sort");
		setCommandType(CommandType.SORT);
		execute(commandType_, action_.getCommandType());

		// testing move
		action_ = new Action("move");
		setCommandType(CommandType.MOVE);
		execute(commandType_, action_.getCommandType());

		// testing search
		action_ = new Action("search");
		setCommandType(CommandType.SEARCH);
		execute(commandType_, action_.getCommandType());

		// testing mark_done
		action_ = new Action("did");
		setCommandType(CommandType.MARK_DONE);
		execute(commandType_, action_.getCommandType());

		// testing help
		action_ = new Action("help");
		setCommandType(CommandType.HELP);
		execute(commandType_, action_.getCommandType());

		// testing invalid
		action_ = new Action("invalid");
		setCommandType(CommandType.INVALID);
		execute(commandType_, action_.getCommandType());

		// testing exit
		action_ = new Action("exit");
		setCommandType(CommandType.EXIT);
		execute(commandType_, action_.getCommandType());

	}

	public void execute(CommandType expected, CommandType actual) {
		assertEquals(expected, actual);
	}

	private void setCommandType(CommandType type) {
		commandType_ = type;
	}

}
