package logic;
/**
 * @author Zhang Yongkai
 * this class declares the error messages which can be used by other classes.
 * 
 */

public class ErrorMessages {

	public static final String ERROR_ADDING_MESSAGE = "Error with adding";
        public static final String SUCCESS_ADDING_MESSAGE = " added";
	public static final String ERROR_DELETE_MESSAGE = "Delete unsuccessful";
	public static final String SUCCESS_DELETE_MESSAGE = "Delete successful";
	public static final String SUCCESS_CLEAR_MESSAGE = "List cleared";
	public static final String ERROR_CLEAR_MESSAGE = "Error with clearing";
	public static final String ERROR_UNDONE_MESSAGE = "Action cannot be undone. "
                                                            + "At original state";
	public static final String SUCCESS_UNDONE_MESSAGE = "Action undone";
	public static final String ERROR_REDONE_MESSAGE = "No action to redo";
	public static final String SUCCESS_REDONE_MESSAGE = "Action redone";
	public static final String ERROR_EDIT_MESSAGE = "Cannot edit task";
	public static final String SUCCESS_EDIT_MESSAGE = "Task edited";
	public static final String ERROR_MOVE_MESSAGE = "Cannot move task";
	public static final String SUCCESS_MOVE_MESSAGE = "Task moved";
	public static final String ERROR_SORTED_MESSAGE = "Cannot sort tasks";
	public static final String SUCCESS_SORTED_MESSAGE = "Tasks sorted";
	public static final String ERROR_MARKDONE_MESSAGE = "Cannot mark done";
	public static final String SUCCESS_MARKDONE_MESSAGE = "Task done";
        public static final String ERROR_MARKUNDONE_MESSAGE = "Cannot mark undone";
	public static final String SUCCESS_MARKUNDONE_MESSAGE = "Task undone";
	public static final String ERROR_INVALID_MESSAGE = "INVALID COMMAND!";
	public static final String ERROR_ARGUMENT_MESSAGE = "INVALID ARGUMENT!";
	public static final String ERROR_COMMAND_TYPE = "Unrecognized command type";
        public static final String SUCCESS_SEARCH_MESSAGE = "Search successful";
        public static final String ERROR_SEARCH_MESSAGE = "Search unsuccessful";
        public static final String SUCCESS_CLEARDONE_MESSAGE = "Done tasks cleared";
        public static final String ERROR_CLEARDONE_MESSAGE = "Cannot clear done tasks";
}
