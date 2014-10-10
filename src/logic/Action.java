package logic;

/**
 * @author Zhang Yongkai
 * this Action class stores the information of the commandType. i.e : whether this action is
 * ADD, DELETE, CLEAR, DISPLAY, UNDO, REDO, EDIT, SORT, MOVE, SEARCH, MARK_DONE, HELP, INVALID, EXIT
 * 
 */

public class Action {

	private String commandWord;
	private CommandType commandType;
	
	//constructor
	public Action() {
	}

	public Action(String commandWord) {
		this.commandWord = commandWord;
		this.setCommandType(commandWord);
	}
	
	//accessors
	public CommandType getCommandType() {
		return commandType;
	}

	//mutators 
	protected void setCommandType(String commandWord) {
		this.commandType = determineCommandType(commandWord);
	}
		
	private CommandType determineCommandType(String command) {
		if (commandWord.equalsIgnoreCase("ADD")) {
			return CommandType.ADD;
		} else if (commandWord.equalsIgnoreCase("DELETE")) {
			return CommandType.DELETE;
		} else if (commandWord.equalsIgnoreCase("CLEAR")) {
			return CommandType.CLEAR;
		} else if (commandWord.equalsIgnoreCase("DISPLAY")) {
			return CommandType.DISPLAY;
		} else if (commandWord.equalsIgnoreCase("UNDO")) {
			return CommandType.UNDO;
		} else if (commandWord.equalsIgnoreCase("REDO")) {
			return CommandType.REDO;
		} else if (commandWord.equalsIgnoreCase("EDIT")) {
			return CommandType.EDIT;
		} else if (commandWord.equalsIgnoreCase("MOVE")) {
			return CommandType.MOVE;
		} else if (commandWord.equalsIgnoreCase("SORT")) {
			return CommandType.SORT;
		} else if (commandWord.equalsIgnoreCase("SEARCH")) {
			return CommandType.SEARCH;
		} else if (commandWord.equalsIgnoreCase("DID")) {
			return CommandType.MARK_DONE;
		} else if (commandWord.equalsIgnoreCase("UNDID")) {
			return CommandType.MARK_UNDONE;
                } else if (commandWord.equalsIgnoreCase("CLEARDONE")) {
                        return CommandType.CLEAR_DONE;
                } else if (commandWord.equalsIgnoreCase("HELP")) {
			return CommandType.HELP;
		} else if (commandWord.equalsIgnoreCase("EXIT")) {
			return CommandType.EXIT;
		} else {
			return CommandType.INVALID;
		}
	}
}