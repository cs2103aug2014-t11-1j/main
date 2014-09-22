package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CommandGenerator {

	TaskManager taskManager;

	String tfString;
	String displayString;

	enum CommandType {

		ADD, DELETE, CLEAR, DISPLAY, UNDO, REDO, EDIT, SORT,MOVE, SEARCH, MARK_DONE, HELP, INVALID, EXIT
	};

	public CommandGenerator() {
		try {
			taskManager = new TaskManager("text.txt");
			tfString = "";
			displayString = taskManager.display();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	protected String getTfString() {
		return tfString;
	}

	protected String getDisplayString() {
		return displayString;
	}

	protected void excecuteCommand(String input) {

		String commandTypeString = getFirstWord(input);
		String taskDescription = getActualRequest(commandTypeString, input);
		CommandType commandType = determineCommandType(commandTypeString);

		try {
			switch (commandType) {

			case ADD:
				boolean isAdded = false;

				isAdded = taskManager.add(taskDescription);
				displayString = taskManager.display();

				if (isAdded) {
					tfString = "\"" + taskDescription + "\""
							+ " ADDED TO LIST!";
				} else {
					tfString = "Error with adding.";
				}
				break;

			case DELETE:
				boolean isDeleted = false;
				int index = Integer.parseInt(taskDescription);
				isDeleted = taskManager.delete(index);
				displayString = taskManager.display();

				if (isDeleted) {
					tfString = "DELETE SUCCESSFUL!";
				} else {
					tfString = "Delete unsuccessful";
				}
				break;

			case CLEAR:
				boolean isCleared = false;
				if (!containsUnnecessaryWords(taskDescription)) {
					isCleared = taskManager.clear();
					displayString = taskManager.display();
				}
				if (isCleared) {
					tfString = "LIST CLEARED!";
				} else {
					tfString = "Error with clearing";
				}
				break;

			case DISPLAY:
				if (!containsUnnecessaryWords(taskDescription)) {
					displayString = taskManager.display();
					tfString = "";
				} else {
					tfString = "Error with displaying";
				}
				break;

			case UNDO:
				boolean isUndone = false;
				if (!containsUnnecessaryWords(taskDescription)) {
					isUndone = taskManager.undo();
					displayString = taskManager.display();
				}
				if (isUndone) {
					tfString = "Action Undone!";
				} else {
					tfString = "Action cannot be undone";
				}
				break;

			case REDO:
				boolean isRedone = false;
				if (!containsUnnecessaryWords(taskDescription)) {
					isRedone = taskManager.redo();
					displayString = taskManager.display();
				}
				if (isRedone) {
					tfString = "Action Redone!";
				} else {
					tfString = "No action to redo";
				}
				break;

			case EDIT:
				/*
				 * This method takes in command in following format: edit
				 * numToDelete newTaskDescription
				 */
				boolean isEdited = false;
				String firstWord = getFirstWord(taskDescription);
				int lineIndex = Integer.parseInt(firstWord);
				String newDescription = getActualRequest(firstWord,taskDescription);
				isEdited = taskManager.edit(lineIndex, newDescription);
				displayString = taskManager.display();

				if (isEdited) {
					tfString = "Task Edited!";
				} else {
					tfString = "Cannot edit task";
				}
				break;
				
			case MOVE:
				boolean isMoved = false;
				String firstNumber = getFirstWord(taskDescription);
				int num1  = Integer.parseInt(firstNumber);
				String secondNumber = getActualRequest(firstNumber,taskDescription);
				int num2 = Integer.parseInt(secondNumber);
				isMoved = taskManager.move(num1, num2);
				displayString = taskManager.display();
				
				if (isMoved) {
					tfString = "Task Moved!";
				} else {
					tfString = "Cannot move task";
				}
				break;

			case SORT:
				boolean isSorted = false;
				if (!containsUnnecessaryWords(taskDescription)) {
				isSorted = taskManager.sort();
				displayString = taskManager.display();
				}
				if (isSorted) {
					tfString = "Sorted!";
				} else {
					tfString = "Cannot sort";
				}
				break;

			case SEARCH:

				/*
				 * This can be changed as you want the display to be different.
				 * Search can return a string for displayString. If so, it can
				 * be more like what display looks like
				 */
			    if(taskDescription.equals("") || taskDescription ==null){
			    	tfString = "nothing to search";
			    	System.out.println(tfString);
			    }else{
			    	displayString = taskManager.search(taskDescription);
					tfString = "Search result: ";
			    }
				break;
				
			case MARK_DONE:
				int lineNum = Integer.parseInt(taskDescription);
				boolean isMarkedDone = taskManager.markDone(lineNum - 1);
				displayString = taskManager.display();

				if (isMarkedDone) {
					tfString = "Task done";
				} else {
					tfString = "Cannot mark done";
				}
				break;

			case HELP:
				if (!containsUnnecessaryWords(taskDescription)) {
				displayString = "add <task>  [DEADLINE]  [START TIME]  [END TIME]"
						+ "\n"
						+ "delete <line number>"
						+ "\n"
						+ "clear"
						+ "\n"
						+ "exit";
				tfString = "";
				}else{
					tfString = "Error with displaying Help";
				}
				break;

			case INVALID:
				tfString = "INVALID COMMAND!";
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
			tfString = "invalid argument";
			System.out.println(tfString);
		}

	}

	private CommandType determineCommandType(String commandTypeString) {

		if (commandTypeString.equalsIgnoreCase("ADD")) {
			return CommandType.ADD;
		} else if (commandTypeString.equalsIgnoreCase("DELETE")) {
			return CommandType.DELETE;
		} else if (commandTypeString.equalsIgnoreCase("CLEAR")) {
			return CommandType.CLEAR;
		} else if (commandTypeString.equalsIgnoreCase("DISPLAY")) {
			return CommandType.DISPLAY;
		} else if (commandTypeString.equalsIgnoreCase("UNDO")) {
			return CommandType.UNDO;
		} else if (commandTypeString.equalsIgnoreCase("REDO")) {
			return CommandType.REDO;
		} else if (commandTypeString.equalsIgnoreCase("EDIT")) {
			return CommandType.EDIT;
		}else if (commandTypeString.equalsIgnoreCase("MOVE")) {
			return CommandType.MOVE;		
		}else if (commandTypeString.equalsIgnoreCase("SORT")) {
			return CommandType.SORT;
		} else if (commandTypeString.equalsIgnoreCase("SEARCH")) {
			return CommandType.SEARCH;
		} else if (commandTypeString.equalsIgnoreCase("DID")) {
			return CommandType.MARK_DONE;
		} else if (commandTypeString.equalsIgnoreCase("HELP")) {
			return CommandType.HELP;
		} else if (commandTypeString.equalsIgnoreCase("EXIT")) {
			return CommandType.EXIT;
		} else {
			return CommandType.INVALID;
		}
	}

	private String getFirstWord(String input) {
		String[] tokens = input.trim().split(" ");
		return tokens[0];
	}

	private String getActualRequest(String firstWord, String input) {
		return input.replaceFirst(firstWord, "").trim();
	}

	private boolean containsUnnecessaryWords(String input) {
		return (!input.isEmpty());
	}
}
