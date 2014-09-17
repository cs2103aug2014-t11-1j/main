package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CommandGenerator {

    TaskManager taskManager;

    String tfString;
    String displayString;

    enum CommandType {

        ADD, DELETE, CLEAR, DISPLAY, UNDO, REDO,
        EDIT, SORT, SEARCH, MARK_DONE,
        HELP, INVALID, EXIT
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
        CommandType commandType = determineCommandType(commandTypeString);

        try {
            switch (commandType) {

                case ADD:
                    boolean isAdded = false;
                    String task = "";

                    if (inputIsValid(input)) {
                        task = input.substring(input.indexOf(' ') + 1);
                        isAdded = taskManager.add(task);
                        displayString = taskManager.display();
                    }

                    if (isAdded) {
                        tfString = "\"" + task + "\"" + " ADDED TO LIST!";
                    } else {
                        tfString = "Error with adding.";
                    }
                    break;

                case DELETE:
                    boolean isDeleted = false;
                    if (inputIsValid(input)) {
                        int lineNum = Integer.parseInt(input.substring(input
                                .indexOf(' ') + 1));
                        isDeleted = taskManager.delete(lineNum);
                        displayString = taskManager.display();
                    }

                    if (isDeleted) {
                        tfString = "DELETE SUCCESSFUL!";
                    } else {
                        tfString = "Delete unsuccessful";
                    }
                    break;

                case CLEAR:
                    boolean isCleared = taskManager.clear();
                    displayString = taskManager.display();

                    if (isCleared) {
                        tfString = "LIST CLEARED!";
                    } else {
                        tfString = "Error with clearing";
                    }
                    break;

                case DISPLAY:
                    displayString = taskManager.display();
                    tfString = "";
                    break;

                case UNDO:
                    boolean isUndone = taskManager.undo();
                    displayString = taskManager.display();

                    if (isUndone) {
                        tfString = "Action Undone!";
                    } else {
                        tfString = "Action cannot be undone";
                    }
                    break;

                case REDO:
                    boolean isRedone = taskManager.redo();
                    displayString = taskManager.display();

                    if (isRedone) {
                        tfString = "Action Redone!";
                    } else {
                        tfString = "No action to redo";
                    }
                    break;

                case EDIT:
                    /*
                     * This method takes in command in following format:
                     *   edit numToDelete newTaskDescription
                     */
                    boolean isEdited = false;
                    if (inputIsValid(input)) {
                        String content = input.trim().substring(
                                input.indexOf(" ") + 1);
                        if (inputIsValid(content)) {
                            int lineIndex = Integer.parseInt(content.trim()
                                    .substring(0, content.indexOf(" ")));
                            String newDescription = content.trim().substring(
                                    content.indexOf(' ') + 1);
                            isEdited = taskManager.edit(lineIndex - 1,
                                    newDescription);
                            displayString = taskManager.display();
                        }
                    }

                    if (isEdited) {
                        tfString = "Task Edited!";
                    } else {
                        tfString = "Cannot edit task";
                    }
                    break;

                case SORT:
                    boolean isSorted = taskManager.sort();
                    displayString = taskManager.display();

                    if (isSorted) {
                        tfString = "Sorted!";
                    } else {
                        tfString = "Cannot sort";
                    }
                    break;

                case SEARCH:

                    /*
                     * This can be changed as you want the display to be different.
                     * Search can return a string for displayString.
                     * If so, it can be more like what display looks like
                     */
                    String searchTerm = input.substring(input.indexOf(' ') + 1);
                    displayString = taskManager.search(searchTerm);

                    break;
                case MARK_DONE:
    				int lineNum = Integer.parseInt(input.substring(input.indexOf(' ') + 1));
    				boolean isMarkedDone = taskManager.markDone(lineNum -1);
    				displayString = taskManager.display();
    				
    				if(isMarkedDone){
    					tfString = "Task done";
    				}
    				else{
    					tfString = "Cannot mark done";
    				}
    				break;

                case HELP:
                    displayString = "add <task>  [DEADLINE]  [START TIME]  [END TIME]" + "\n" + "delete <line number>" + "\n" + "clear" + "\n" + "exit";
                    tfString = "";
                    break;

                case INVALID:
                    tfString = "INVALID COMMAND!";
                    break;

                case EXIT:
                    System.exit(0);

                default:
                    //throw an error if the command is not recognized
                    throw new Error("Unrecognized command type");
            }
        } catch (IOException e) {
            e.printStackTrace();
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
        } else if (commandTypeString.equalsIgnoreCase("SORT")) {
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
        String[] tokens = input.split(" ");
        return tokens[0];
    }

    private boolean inputIsValid(String input) {
        if (input.trim().contains(" ")) {
            return true;
        }
        return false;
    }
}
