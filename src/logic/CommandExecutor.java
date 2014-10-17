package logic;

import java.io.IOException;
import java.util.logging.Level;

import com.MyLogger;

import storage.ModelTask;
/**
 * @author Zhang Yongkai
 *
 * CommandExecutor will pass pass the command action and actual task description
 * to Command class for actual processing.
 */

import storage.Storage;
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
	protected static void setFeedBack(String feedBack) {
		CommandExecutor.feedBack = feedBack;
	}

	protected static void setTaskList(ObservableList<ModelTask> taskList) {
		CommandExecutor.taskList = taskList;
		try {
			storage.save(taskList);
		} catch (IOException e) {
			MyLogger.log(Level.WARNING,"cannot save to text file");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected static void setTempList(ObservableList<ModelTask> searchList) {
		CommandExecutor.searchedList = searchList;
		try {
			storage.save(taskList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public void executeCommand(String rawInput) {
		String commandWord = pf.getCommandString(rawInput);
		String actualCommandDescription = pf.getStringWithoutCommand(rawInput);
		Action action = new Action(commandWord);
		CommandType commandType = action.getCommandType();
		System.out.println(commandWord + " " + actualCommandDescription);
		try {
			switch (commandType) {

			case ADD:
				boolean isAdded = false;
				cmdf = new Add(actualCommandDescription, false);
				isAdded = cmdf.isDone();
				
				break;
                            
                        case ADDURGENT:
                                boolean isAddedUrgent = false;
				cmdf = new Add(actualCommandDescription, true);
				isAddedUrgent = cmdf.isDone();
				
				break; 

			case DELETE:
				boolean isDeleted = false;
				cmdf = new Delete(actualCommandDescription);
				isDeleted = cmdf.isDone();
                                
				break;

			case CLEAR:
				boolean isCleared = false;
				cmdf = new Clear(actualCommandDescription);
				isCleared = cmdf.isDone();
				
				break;

			case DISPLAY:
				boolean isDisplayed = false;
                                cmdf = new Display(actualCommandDescription);
                                isDisplayed = cmdf.isDone();

				break;

			case UNDO:
				boolean isUndone = false;
				cmdf = new Undo();
				isUndone = cmdf.isDone();
				
				break;

			case REDO:
				boolean isRedone = false;

				cmdf = new Redo();
				isRedone = cmdf.isDone();
				
				break;

			case EDIT:
				/*
				 * This method takes in command in following format: edit
				 * numToDelete newTaskDescription
				 */
				boolean isEdited = false;
				cmdf = new Edit(actualCommandDescription);
				isEdited = cmdf.isDone();
				
				break;

			case MOVE:
				boolean isMoved = false;
				cmdf = new Move(actualCommandDescription);
				isMoved = cmdf.isDone();
				
				break;

			case SORT:
				boolean isSorted = false;
                                cmdf = new Sort(actualCommandDescription);
				isSorted = cmdf.isDone();
                                
				break;

			case SEARCH:

				/**
				 * This can be changed as you want the display to be different.
				 * Search can return a string for displayString. If so, it can
				 * be more like what display looks like
				 */
				cmdf = new Search(actualCommandDescription);

				break;

			case MARK_DONE:
				cmdf = new MarkDone(actualCommandDescription);
				boolean isMarkedDone = cmdf.isDone();
				
				break;
                        case MARK_UNDONE:
                                cmdf = new MarkUndone(actualCommandDescription);
                                boolean isUnmarkedDone = cmdf.isDone();

                                break;
                        
                        case MARK_URGENT:
				cmdf = new MarkUrgent(actualCommandDescription);
				boolean isMarkedUrgent = cmdf.isDone();
				
				break;
                        case MARK_NOT_URGENT:
                                cmdf = new MarkNotUrgent(actualCommandDescription);
                                boolean isUnmarkedUrgent = cmdf.isDone();

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
			MyLogger.log(Level.WARNING,ErrorMessages.ERROR_ARGUMENT_MESSAGE);
			System.out.println(feedBack);
		}

	}
}
