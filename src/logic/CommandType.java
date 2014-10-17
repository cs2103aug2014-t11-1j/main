package logic;

/**
 * @author Zhang Yongkai
 * this class declares the CommandType which can be used by other classes
 * 
 */

public enum CommandType {
		ADD, ADDURGENT, DELETE, CLEAR, DISPLAY, UNDO, REDO, EDIT, SORT, 
                MOVE, SEARCH, MARK_DONE, MARK_UNDONE, 
                MARK_URGENT, MARK_NOT_URGENT, HELP, INVALID, EXIT
	};

