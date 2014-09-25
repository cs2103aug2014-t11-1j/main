package logic;

import storage.ModelTask;
/**
 * @author Zhang Yongkai
 *
 * CommandExecutor will pass pass the command action and actual task description
 * to Command class for actual processing.
 */

import javafx.collections.ObservableList;
import parser.ParserFacade;

public class CommandExecutor {

	private static CommandFactory cmdf;
	private static ParserFacade pf;
	private String commandWord;
	private String actualCommandDescription;
	private static String feedBack;
	private static ObservableList<ModelTask> taskList;
	private static ObservableList<ModelTask> searchedList;

	// constructor
	public CommandExecutor(ObservableList<ModelTask> list) {
		taskList = list;
		CommandFactory.list.setList(taskList);
                CommandFactory.searchList.setList(searchedList);
		pf = ParserFacade.getInstance();
	}

	// mutator
	protected static void setFeedBack(String feedBack) {
		CommandExecutor.feedBack = feedBack;
	}
        
        protected static void setTaskList(ObservableList<ModelTask> taskList){
            CommandExecutor.taskList = taskList;
        }
        
        protected static void setSearchList(ObservableList<ModelTask> searchList){
            CommandExecutor.searchedList = searchList;
        } 

	// accessors
	public String getCommandWord() {
		return commandWord;
	}

	public String getActualCommandDescription() {
		return actualCommandDescription;
	}

	public String getFeedBack() {
		return feedBack;
	}

	public ObservableList<ModelTask> getAllList() {
		return taskList;
	}

	public ObservableList<ModelTask> getSearchedList() {
		return searchedList;
	}

	protected void excecuteCommand(String rawInput) {
		String commandWord = pf.getCommandString(rawInput);
		String actualCommandDescription = pf.getStringWithoutCommand(rawInput);
		Action action = new Action(commandWord);
		CommandType commandType = action.getCommandType();
		System.out.println(commandWord + actualCommandDescription);
		try {
			switch (commandType) {

			case ADD:
				boolean isAdded = false;
				cmdf = new Add(actualCommandDescription);
				isAdded = cmdf.isDone();
				// setTaskList(cmd.getTaskList());

				if (isAdded) {
					setFeedBack("\"" + actualCommandDescription + "\""
							+ " ADDED TO LIST!");
				} else {
					setFeedBack(ErrorMessages.ERROR_ADDING_MESSAGE);
				}
				break;

			case DELETE:
				boolean isDeleted = false;
				cmdf = new Delete(actualCommandDescription);
				isDeleted = cmdf.isDone();
				// setTaskList(cmd.getTaskList());

				if (isDeleted) {
					setFeedBack(ErrorMessages.SUCCESS_DELETE_MESSAGE);
				} else {
					setFeedBack(ErrorMessages.ERROR_DELETE_MESSAGE);
				}
				break;

			case CLEAR:
				boolean isCleared = false;
				cmdf = new Clear();
				isCleared = cmdf.isDone();
				// setTaskList(cmd.getTaskList());

				if (isCleared) {
					setFeedBack(ErrorMessages.SUCCESS_CLEAR_MESSAGE);
				} else {
					setFeedBack(ErrorMessages.ERROR_CLEAR_MESSAGE);
				}
				break;

			case DISPLAY:
				// cmd.display();
				// setTaskList(cmd.getTaskList());
				setFeedBack("");

				break;

			case UNDO:
				boolean isUndone = false;
				cmdf = new Undo();
				isUndone = cmdf.isDone();
				// setTaskList(cmd.getTaskList());

				if (isUndone) {
					setFeedBack(ErrorMessages.SUCCESS_UNDONE_MESSAGE);
				} else {
					setFeedBack(ErrorMessages.ERROR_UNDONE_MESSAGE);
				}
				break;

			case REDO:
				boolean isRedone = false;

				cmdf = new Redo();
				isRedone = cmdf.isDone();
				// setTaskList(cmd.getTaskList());

				if (isRedone) {
					setFeedBack(ErrorMessages.SUCCESS_REDONE_MESSAGE);
				} else {
					setFeedBack(ErrorMessages.ERROR_REDONE_MESSAGE);
				}
				break;

			case EDIT:
				/*
				 * This method takes in command in following format: edit
				 * numToDelete newTaskDescription
				 */
				boolean isEdited = false;
				cmdf = new Edit(actualCommandDescription);
				isEdited = cmdf.isDone();
				// setTaskList(cmd.getTaskList());

				if (isEdited) {
					setFeedBack(ErrorMessages.SUCCESS_EDIT_MESSAGE);
				} else {
					setFeedBack(ErrorMessages.ERROR_EDIT_MESSAGE);
				}
				break;

			case MOVE:
				boolean isMoved = false;
				cmdf = new Move(actualCommandDescription);
				isMoved = cmdf.isDone();
				// setTaskList(cmd.getTaskList());

				if (isMoved) {
					setFeedBack(ErrorMessages.SUCCESS_MOVE_MESSAGE);
				} else {
					setFeedBack(ErrorMessages.ERROR_MOVE_MESSAGE);
				}
				break;

			case SORT:
				boolean isSorted = false;

				// isSorted = cmd.sort();
				// setTaskList(cmd.getTaskList());

				if (isSorted) {
					setFeedBack(ErrorMessages.SUCCESS_SORTED_MESSAGE);
				} else {
					setFeedBack(ErrorMessages.ERROR_SORTED_MESSAGE);
				}
				break;

			case SEARCH:

				/**
				 * This can be changed as you want the display to be different.
				 * Search can return a string for displayString. If so, it can
				 * be more like what display looks like
				 */
				cmdf = new Search(actualCommandDescription);
				
				// setFeedBack(cmd.search(actualCommandDescription));
				// setSearchedList(cmd.getTaskList());

				break;

			case MARK_DONE:
				cmdf = new MarkDone(actualCommandDescription);
				boolean isMarkedDone = cmdf.isDone();
				// setTaskList(cmd.getTaskList());

				if (isMarkedDone) {
					setFeedBack(ErrorMessages.SUCCESS_MARKDONE_MESSAGE);
				} else {
					setFeedBack(ErrorMessages.ERROR_MARKDONE_MESSAGE);
				}
				break;

			case HELP:
				/*
				 * this part need to consult Krystal because this cannot be
				 * passed back as a Tasklist
				 * 
				 * displayString =
				 * "add <task>  [DEADLINE]  [START TIME]  [END TIME]" + "\n" +
				 * "delete <line number>" + "\n" + "clear" + "\n" + "exit";
				 * feedBack = "";
				 */
				break;

			case INVALID:
				setFeedBack(ErrorMessages.ERROR_INVALID_MESSAGE);
				break;

			case EXIT:
				System.exit(0);

			default:
				// throw an error if the command is not recognized
				throw new Error(ErrorMessages.ERROR_COMMAND_TYPE);
			}
		} catch (NumberFormatException e) {
			setFeedBack(ErrorMessages.ERROR_ARGUMENT_MESSAGE);
			System.out.println(feedBack);
		}

	}
}
