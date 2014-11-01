package logic;

/**
 * @author Zhang Yongkai This Action class stores the information of the
 *         commandType. i.e : whether this action is ADD, DELETE, CLEAR,
 *         DISPLAY, UNDO, REDO, EDIT, SORT, MOVE, SEARCH, MARK_DONE, HELP,
 *         INVALID, EXIT
 * 
 */

public class Action {

	private static final String[] DICTIONARY_ADD = { "ADD", "A" };
	private static final String[] DICTIONARY_ADD_URGENT = { "ADD!", "A!" };
	private static final String[] DICTIONARY_DELETE = { "DELETE", "DEL", "D" };
	private static final String[] DICTIONARY_CLEAR = { "CLEAR", "CLS", "CLR" };
	private static final String[] DICTIONARY_EDIT = { "EDIT", "E" };
	private static final String[] DICTIONARY_MOVE = { "MOVE", "MV", "M" };
	private static final String[] DICTIONARY_SORT = { "SORT", "SRT" };
	private static final String[] DICTIONARY_SEARCH = { "SEARCH", "?" };
	private static final String[] DICTIONARY_MARK_DONE = { "DONE", "DID" };
	private static final String[] DICTIONARY_MARK_UNDONE = { "UNDONE",
			"NOTDONE", "UNDID", "NOTDID" };
	private static final String[] DICTIONARY_MARK_URGENT = { "URGENT", "UR",
			"URG", "URGE" };
	private static final String[] DICTIONARY_MARK_NOT_URGENT = { "NOTURGENT",
			"NURGENT", "NUR", "NURG", "NURGE" };
	private static final String[] DICTIONARY_DISPLAY = { "DISPLAY", "SHOW" };

	private String commandWord;
	private CommandType commandType;

	// constructor
	public Action() {
	}

	public Action(String commandWord) {
		this.commandWord = commandWord;
		this.setCommandType(commandWord);
	}

	// accessors
	public CommandType getCommandType() {
		return commandType;
	}

	// mutators
	protected void setCommandType(String commandWord) {
		this.commandType = determineCommandType(commandWord);
	}

	private CommandType determineCommandType(String command) {
		if (dictionaryContains(DICTIONARY_ADD, command)) {
			return CommandType.ADD;
		} else if (dictionaryContains(DICTIONARY_ADD_URGENT, command)) {
			return CommandType.ADDURGENT;
		} else if (dictionaryContains(DICTIONARY_DELETE, command)) {
			return CommandType.DELETE;
		} else if (dictionaryContains(DICTIONARY_CLEAR, command)) {
			return CommandType.CLEAR;
		} else if (dictionaryContains(DICTIONARY_DISPLAY, command)) {
			return CommandType.DISPLAY;
		} else if (commandWord.equalsIgnoreCase("UNDO")) {
			return CommandType.UNDO;
		} else if (commandWord.equalsIgnoreCase("REDO")) {
			return CommandType.REDO;
		} else if (dictionaryContains(DICTIONARY_EDIT, command)) {
			return CommandType.EDIT;
		} else if (dictionaryContains(DICTIONARY_MOVE, command)) {
			return CommandType.MOVE;
		} else if (dictionaryContains(DICTIONARY_SORT, command)) {
			return CommandType.SORT;
		} else if (dictionaryContains(DICTIONARY_SEARCH, command)) {
			return CommandType.SEARCH;
		} else if (dictionaryContains(DICTIONARY_MARK_DONE, command)) {
			return CommandType.MARK_DONE;
		} else if (dictionaryContains(DICTIONARY_MARK_UNDONE, command)) {
			return CommandType.MARK_UNDONE;
		} else if (dictionaryContains(DICTIONARY_MARK_URGENT, command)) {
			return CommandType.MARK_URGENT;
		} else if (dictionaryContains(DICTIONARY_MARK_NOT_URGENT, command)) {
			return CommandType.MARK_NOT_URGENT;
		} else if (commandWord.equalsIgnoreCase("TENTATIVE")) {
			return CommandType.TENTATIVE;
		} else if (commandWord.equalsIgnoreCase("HELP")) {
			return CommandType.HELP;
		} else if (commandWord.equalsIgnoreCase("EXIT")) {
			return CommandType.EXIT;
		} else {
			return CommandType.INVALID;
		}
	}

	private boolean dictionaryContains(String[] dictionary, String keyword) {
		boolean isFound = false;
		for (int i = 0; i < dictionary.length; i++) {
			if (dictionary[i].equalsIgnoreCase(keyword)) {
				isFound = true;
				break;
			}
		}
		return isFound;
	}
}