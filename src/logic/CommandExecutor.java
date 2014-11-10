package logic;

import java.io.IOException;
import java.util.logging.Level;
import com.ModelTask;
import com.util.MyLogger;
import storage.Storage;
import javafx.collections.ObservableList;
import parser.ParserFacade;

//@author A0110567L
/**
 * CommandExecutor will pass pass the command action and actual task description
 * to Command class for actual processing.
 */

public class CommandExecutor {
	private String commandWord_;
	private String actualCommandDescription_;
	private static CommandFactory cmdf;
	private static ParserFacade pf;
	private static String userFeedBack;
	private static int guiFeedBack;
	private static ObservableList<ModelTask> taskList;
	private static ObservableList<ModelTask> searchedList;
	private static Storage storage;

	// constructor
	public CommandExecutor(ObservableList<ModelTask> list, Storage storage) {
		taskList = list;
		CommandFactory.list.setList(taskList);
		CommandFactory.updateUndoAndRedoStacks();
		pf = ParserFacade.getInstance();
		CommandExecutor.storage = storage;
	}

	// mutator
	protected static void setUserFeedBack(String feedBack) {
		CommandExecutor.userFeedBack = feedBack;
	}

	protected static void setGuiFeedBack(int feedBack) {
		if (isGuiFeedbackValid(feedBack)) {
			CommandExecutor.guiFeedBack = feedBack;
		} else {
			CommandExecutor.guiFeedBack = 0;
		}
	}

	protected static void setTaskList(ObservableList<ModelTask> taskList) {
		CommandExecutor.taskList = taskList;
		try {
			storage.save(taskList);
		} catch (IOException e) {
			MyLogger.log(Level.WARNING, FeedbackMessages.ERROR_SAVING_MESSAGE);
			e.printStackTrace();
		}
	}

	protected static void setTempList(ObservableList<ModelTask> searchList) {
		CommandExecutor.searchedList = searchList;
		try {
			storage.save(taskList);
		} catch (IOException e) {
			MyLogger.log(Level.WARNING, FeedbackMessages.ERROR_SAVING_MESSAGE);
			e.printStackTrace();
		}
	}

	// accessors
	public String getCommandWord() {
		return commandWord_;
	}

	public String getActualCommandDescription() {
		return actualCommandDescription_;
	}

	public String getUserFeedBack() {
		return userFeedBack;
	}

	public int getGuiFeedBack() {
		return guiFeedBack;
	}

	public ObservableList<ModelTask> getAllList() {
		return taskList;
	}

	public ObservableList<ModelTask> getSearchedList() {
		return searchedList;
	}

	public void executeCommand(String rawInput) {
		String commandWord = pf.getCommandString(rawInput);
		String actualCommandDescription = pf.getStringWithoutCommand(rawInput);
		Action action = new Action(commandWord);
		CommandType commandType = action.getCommandType();
		System.out.println(commandWord + " " + actualCommandDescription);
		try {
			switch (commandType) {

			case ADD:
				@SuppressWarnings("unused")
				boolean isAdded = false;
				cmdf = new Add(actualCommandDescription, false);
				isAdded = cmdf.isDone();

				break;

			case ADDURGENT:
				@SuppressWarnings("unused")
				boolean isAddedUrgent = false;
				cmdf = new Add(actualCommandDescription, true);
				isAddedUrgent = cmdf.isDone();

				break;

			case DELETE:
				@SuppressWarnings("unused")
				boolean isDeleted = false;
				cmdf = new Delete(actualCommandDescription);
				isDeleted = cmdf.isDone();

				break;

			case CLEAR:
				@SuppressWarnings("unused")
				boolean isCleared = false;
				cmdf = new Clear(actualCommandDescription);
				isCleared = cmdf.isDone();

				break;

			case DISPLAY:
				@SuppressWarnings("unused")
				boolean isDisplayed = false;
				cmdf = new Display(actualCommandDescription);
				isDisplayed = cmdf.isDone();

				break;

			case UNDO:
				@SuppressWarnings("unused")
				boolean isUndone = false;
				cmdf = new Undo();
				isUndone = cmdf.isDone();

				break;

			case REDO:
				@SuppressWarnings("unused")
				boolean isRedone = false;

				cmdf = new Redo();
				isRedone = cmdf.isDone();

				break;

			case EDIT:
				@SuppressWarnings("unused")
				boolean isEdited = false;
				cmdf = new Edit(actualCommandDescription);
				isEdited = cmdf.isDone();

				break;

			case MOVE:
				@SuppressWarnings("unused")
				boolean isMoved = false;
				cmdf = new Move(actualCommandDescription);
				isMoved = cmdf.isDone();

				break;

			case SORT:
				@SuppressWarnings("unused")
				boolean isSorted = false;
				cmdf = new Sort(actualCommandDescription);
				isSorted = cmdf.isDone();

				break;

			case SEARCH:

				cmdf = new Search(actualCommandDescription);

				break;

			case MARK_DONE:
				cmdf = new MarkDone(actualCommandDescription);
				@SuppressWarnings("unused")
				boolean isMarkedDone = cmdf.isDone();

				break;
			case MARK_UNDONE:
				cmdf = new MarkUndone(actualCommandDescription);
				@SuppressWarnings("unused")
				boolean isUnmarkedDone = cmdf.isDone();

				break;

			case MARK_URGENT:
				cmdf = new MarkUrgent(actualCommandDescription);
				@SuppressWarnings("unused")
				boolean isMarkedUrgent = cmdf.isDone();

				break;
			case MARK_NOT_URGENT:
				cmdf = new MarkNotUrgent(actualCommandDescription);
				@SuppressWarnings("unused")
				boolean isUnmarkedUrgent = cmdf.isDone();

				break;

			case TENTATIVE:
				cmdf = new BlockTentative(actualCommandDescription);
				@SuppressWarnings("unused")
				boolean isTentativeBlocked = cmdf.isDone();

				break;

			case HELP:
				// unimplemented
				break;

			case INVALID:
				setUserFeedBack(FeedbackMessages.ERROR_INVALID_MESSAGE);
				break;

			case EXIT:
				MyLogger.closeLogger();
				System.exit(0);

			default:
				// throw an error if the command is not recognized
				throw new Error(FeedbackMessages.ERROR_COMMAND_TYPE);
			}
		} catch (NumberFormatException e) {
			setUserFeedBack(FeedbackMessages.ERROR_ARGUMENT_MESSAGE);
			MyLogger.log(Level.WARNING, FeedbackMessages.ERROR_ARGUMENT_MESSAGE);
			System.out.println(userFeedBack);
		}

	}

	// @author A0111370Y
	protected static boolean isGuiFeedbackValid(int feedback) {
		if (feedback >= 0 && feedback <= 3) {
			return true;
		} else {
			return false;
		}
	}
}
