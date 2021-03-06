package logic;

//@author A0110567L
public class FeedbackMessages {

	public static final int NORMAL_STATE = 0;
	public static final int UPDATE_ALL_LIST = 1;
	public static final int SWITCH_TO_TEMP = 2;
	public static final int SWITCH_TO_TEMP_DELETE = 3;

	public static final String ERROR_ADDING_MESSAGE = "Error with adding";
	public static final String SUCCESS_ADDING_MESSAGE = " added";
	public static final String ERROR_DELETE_MESSAGE = "Delete unsuccessful";
	public static final String SUCCESS_DELETE_MESSAGE = "Delete successful";
	public static final String WAIT_DELETE_MESSAGE = "Please choose which task to delete";
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
	public static final String SUCCESS_MARKURGENT_MESSAGE = "Task marked as urgent";
	public static final String ERROR_MARKURGENT_MESSAGE = "Task cannot be marked as urgent";
	public static final String SUCCESS_MARKNOTURGENT_MESSAGE = "Task marked as not urgent";
	public static final String ERROR_MARKNOTURGENT_MESSAGE = "Task cannot be marked as not urgent";
	public static final String SUCCESS_CLEARURGENT_MESSAGE = "Urgent tasks cleared";
	public static final String ERROR_CLEARURGENT_MESSAGE = "Cannot clear urgent tasks";
	public static final String ERROR_DISPLAY_MESSAGE = "No such display type";
	public static final String SUCCESS_TENTATIVE_MESSAGE = "Tentative task blocked";
	public static final String ERROR_TENTATIVE_MESSAGE = "Cannot block tentative task";
	public static final String SUCCESS_CLEARNORMAL_MESSAGE = "Normal tasks cleared";
	public static final String ERROR_SAVING_MESSAGE = "cannot save to text file";
}
