package logic;

import java.io.IOException;

/**
 * @author zhang 
 * facade class to receive command word from the parser
 */

public class CommandExecutor {

	private String commandWord;
	private String actualCommandDescription;
	private String feedBack;
	private String displayString;
	private Command cmd;
	private List<Task> todayList;
	private List<Task> searchList;
	private List<Task> allList;

	public CommandExecutor() {
	}

	public CommandExecutor(String commandWord, String actualCommandDescription) {
		this.commandWord = commandWord;
		this.actualCommandDescription = actualCommandDescription;
		cmd.getInstance();
	}

	public String getFeedBack() {
		return feedBack;
	}

	public String getDisplayString() {
		return displayString;
	}

	public ArrayList<Task> getDisplayString() {
		return displayString;
	}

	public ArrayList<Task> getTodayList() {
		return todayList;
	}

	public ArrayList<Task> getAllList() {
		return allList;
	}

	public ArrayList<Task> getSearchList() {
		return searchList;
	}

	enum CommandType {
		ADD, DELETE, CLEAR, DISPLAY, UNDO, REDO, EDIT, SORT, MOVE, SEARCH, MARK_DONE, HELP, INVALID, EXIT
	};

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
		} else if (commandWord.equalsIgnoreCase("HELP")) {
			return CommandType.HELP;
		} else if (commandWord.equalsIgnoreCase("EXIT")) {
			return CommandType.EXIT;
		} else {
			return CommandType.INVALID;
		}
	}

	protected void excecuteCommand(String command) {

		CommandType commandType = determineCommandType(commandWord);

		try {
			switch (commandType) {

			case ADD:
				boolean isAdded = false;
				isAdded = cmd.add(actualCommandDescription);
				displayString = cmd.display();

				if (isAdded) {
					feedBack = "\"" + actualCommandDescription + "\""
							+ " ADDED TO LIST!";
				} else {
					feedBack = "Error with adding.";
				}
				break;

			case DELETE:
				boolean isDeleted = false;
				isDeleted = cmd.delete(actualCommandDescription);
				displayString = cmd.display();

				if (isDeleted) {
					feedBack = "DELETE SUCCESSFUL!";
				} else {
					feedBack = "Delete unsuccessful";
				}
				break;

			case CLEAR:
				boolean isCleared = false;
				isCleared = cmd.clear();
				displayString = cmd.display();

				if (isCleared) {
					feedBack = "LIST CLEARED!";
				} else {
					feedBack = "Error with clearing";
				}
				break;

			case DISPLAY:

				displayString = cmd.display();
				feedBack = "";

				break;

			case UNDO:
				boolean isUndone = false;

				isUndone = cmd.undo();
				displayString = cmd.display();

				if (isUndone) {
					feedBack = "Action Undone!";
				} else {
					feedBack = "Action cannot be undone";
				}
				break;

			case REDO:
				boolean isRedone = false;

				isRedone = cmd.redo();
				displayString = cmd.display();

				if (isRedone) {
					feedBack = "Action Redone!";
				} else {
					feedBack = "No action to redo";
				}
				break;

			case EDIT:
				/*
				 * This method takes in command in following format: edit
				 * numToDelete newTaskDescription
				 */
				boolean isEdited = false;
				isEdited = cmd.edit(actualCommandDescription);
				displayString = cmd.display();

				if (isEdited) {
					feedBack = "Task Edited!";
				} else {
					feedBack = "Cannot edit task";
				}
				break;

			case MOVE:
				boolean isMoved = false;
				isMoved = cmd.move(actualCommandDescription);
				displayString = cmd.display();

				if (isMoved) {
					feedBack = "Task Moved!";
				} else {
					feedBack = "Cannot move task";
				}
				break;

			case SORT:
				boolean isSorted = false;

				isSorted = cmd.sort();
				displayString = cmd.display();

				if (isSorted) {
					feedBack = "Sorted!";
				} else {
					feedBack = "Cannot sort";
				}
				break;

			case SEARCH:

				/*
				 * This can be changed as you want the display to be different.
				 * Search can return a string for displayString. If so, it can
				 * be more like what display looks like
				 */

				displayString = cmd.search(actualCommandDescription);
				feedBack = "Search result: ";

				break;

			case MARK_DONE:

				boolean isMarkedDone = cmd
						.markDone(actualCommandDescription);
				displayString = cmd.display();

				if (isMarkedDone) {
					feedBack = "Task done";
				} else {
					feedBack = "Cannot mark done";
				}
				break;

			case HELP:
					displayString = "add <task>  [DEADLINE]  [START TIME]  [END TIME]"
							+ "\n"
							+ "delete <line number>"
							+ "\n"
							+ "clear"
							+ "\n" + "exit";
					feedBack = "";
				
				break;

			case INVALID:
				feedBack = "INVALID COMMAND!";
				break;

			case EXIT:
				System.exit(0);

			default:
				// throw an error if the command is not recognized
				throw new Error("Unrecognized command type");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			feedBack = "invalid argument";
			System.out.println(feedBack);
		}

	}
}
