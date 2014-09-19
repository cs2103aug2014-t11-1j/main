package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class TaskManager {

    private ArrayList<Task> list;
    private File inputFile;
    private String fileName;

    private Stack<ArrayList<Task>> undoStack;
    private Stack<ArrayList<Task>> redoStack;

    //constructor
    public TaskManager(String inputFileName) throws IOException {
        initialize(inputFileName);
    }

    private void initialize(String inputFileName) throws IOException {

        inputFile = new File(inputFileName);
        if (!inputFile.exists()) {
            inputFile.createNewFile();
        }
        fileName = inputFileName;

        initializeArrayList();

        undoStack = new Stack<ArrayList<Task>>();
        redoStack = new Stack<ArrayList<Task>>();

        ArrayList<Task> originalList = new ArrayList<Task>();
        copyArrayList(list, originalList);
        undoStack.push(originalList);
    }

    //writes existing contents of file to ArrayList if any
    private void initializeArrayList() throws FileNotFoundException {

        list = new ArrayList<Task>();
        Scanner sc = new Scanner(inputFile);
        copyContentsOfTextFile(sc);
        sc.close();
    }

    private void copyContentsOfTextFile(Scanner sc) {

		while(sc.hasNext()){

			String str = sc.nextLine();
			Task e = new Task(str, true);
			list.add(e);
		}	
	}

    //add task to to-do list
    protected boolean add(String task) throws IOException {
        if (task == null || task.equals("")) {
            return false;
        }

        Task e = new Task(task);
        list.add(e);
        save();
        updateUndoAndRedoStacks();

        printMessage("added to " + fileName + ":" + "\"" + task + "\"");
        return true;
    }

    //delete task from to-do list
    protected boolean delete(int num) throws IOException {
        boolean isDeleted = false;
        int index = num-1;
        if (isValidLineNumber(index)) {
            String task = list.get(index).getEvent();
            list.remove(index);
            save();
            updateUndoAndRedoStacks();

            printMessage("deleted from  " + fileName + ":" + "\"" + task + "\"");

            isDeleted = true;
        } else {
            printMessage("Please enter a valid line number.");

        }

        return isDeleted;
    }

    private boolean isValidLineNumber(int num) {

        return num < list.size() && num >= 0;
    }

    //delete all tasks from To-Do List
    protected boolean clear() throws IOException {
        list.clear();
        save();
        updateUndoAndRedoStacks();

        printMessage("all content deleted from list");

        return true;
    }

    //writes contents of ArrayList to text file
    private void save() throws IOException {

        FileWriter fileWriter = new FileWriter(inputFile, false);
        BufferedWriter buffer = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(buffer);

        for (int i = 0; i < list.size(); i++) {
            printWriter.println(list.get(i).toString());
        }

        printWriter.close();
        buffer.close();
        fileWriter.close();
    }

	//display contents of To-Do List
	protected String display() throws FileNotFoundException{

		String text = "";

		if(list.isEmpty())
		{
			printMessage(fileName + " is empty");
			text = "List is empty";
		}
		else{
			printContentsOfList(1);	
			
			int lineNum = 1;
			for(int i=0;i<list.size();i++){
				text = text + lineNum + ". " + list.get(i).toString() + "\n";
				lineNum++;
			}
		}
		return text;
	}

	private void printContentsOfList(int lineNum) {
		Task temp;
		for(int i=0;i<list.size();i++)
		{
			temp = list.get(i);
			printMessage(lineNum + ". " + temp.toString() + " " + temp.getIsDone());
			lineNum++;
		}
	}

    private void printMessage(String message) {

        System.out.println(message);
    }

    /**
     * This method updates Undo and Redo Stacks from class variable list
     * for undo and redo to work.
     */
    private void updateUndoAndRedoStacks() {
        ArrayList<Task> temp = new ArrayList<Task>();
        copyArrayList(list, temp);
        undoStack.push(temp);
        redoStack.clear();
    }

    protected boolean undo() throws IOException {

        boolean isUndone = false;

        if (!undoStack.isEmpty()) {
            ArrayList<Task> popped = undoStack.pop();

            if (!undoStack.isEmpty()) {
                redoStack.push(popped);

                popped = undoStack.peek();
                ArrayList<Task> temp = new ArrayList<Task>();
                copyArrayList(popped, temp);
                list = temp;

                isUndone = true;
                System.out.println("Action undone");
            } else {
                ArrayList<Task> temp = new ArrayList<Task>();
                copyArrayList(popped, temp);
                undoStack.push(temp);

                System.out.println("Action cannot be undone; original state");
            }
        } else {
            System.out.println("Error, empty stack");
        }
        save();
        return isUndone;
    }

    protected boolean redo() throws IOException {
        boolean isRedone = false;
        if (redoStack.isEmpty()) {
            System.out.println("No action to redo");
        } else {
            list = redoStack.pop();
            ArrayList<Task> temp = new ArrayList<Task>();
            copyArrayList(list, temp);
            undoStack.push(temp);
            isRedone = true;
        }
        save();
        return isRedone;
    }

    private void copyArrayList(ArrayList<Task> oldArrayList, ArrayList<Task> newArrayList) {

        if (!newArrayList.isEmpty()) {
            newArrayList.clear();
        }
        Iterator<Task> itr = oldArrayList.iterator();

        while (itr.hasNext()) {
            newArrayList.add(itr.next());
        }
    }

    //for debugging, prints a stack of arraylist of events to console
    @SuppressWarnings("unused")
    private void printStack(Stack<ArrayList<Task>> stack) {
        System.out.print("Stack : ");
        if (stack.isEmpty()) {
            System.out.print("null");
        }
        Iterator<ArrayList<Task>> itr = stack.iterator();
        while (itr.hasNext()) {
            System.out.print(itr.next().toString() + ", ");
        }
        System.out.print('\n');
    }

    protected boolean edit(int num, String newTaskDescription) throws IOException {
        boolean isEdited = false;
        int index = num-1;

        if (isValidLineNumber(index) && newTaskDescription != null && !newTaskDescription.equals("")) {
            String oldTaskDescription = list.get(index).toString().trim();
            Task modified = new Task(newTaskDescription);
            list.remove(index); // remove it in list
            list.add(index, modified);
            updateUndoAndRedoStacks();
            save(); // save the updated list to file

            printMessage(oldTaskDescription + " has been changed to "
                    + newTaskDescription);
            isEdited = true;
        } else {
            printMessage("Please enter a valid line number. ");
        }
        return isEdited;
    }

    protected boolean sort() {
        // TODO Auto-generated method stub
        boolean isSorted = false;

        //updateUndoAndRedoStacks();
        //save();
        return isSorted;
    }
    
    protected boolean markDone(int num) throws IOException {
		// TODO Auto-generated method stub
		boolean isMarkedDone = false;
		if(isValidLineNumber(num)){
			Task task = list.get(num);
			task.setDone(true);
			updateUndoAndRedoStacks();
			save();
			printMessage(num + "marked done");

			isMarkedDone = true;
		}
		else {
			printMessage("Please enter a valid line number.");

		}

		return isMarkedDone;
	}
    
    protected String search(String searchTerm) {

        Iterator<Task> iterator = list.iterator();
        String foundString = "";
        int index = 0;

        while (iterator.hasNext()) {
            String currentString = iterator.next().getEvent();
            if (currentString.contains(searchTerm)) {
                foundString = foundString.concat(String.format("%d. %s\n", index + 1, currentString));
            }
            index++;
        }
        if (!foundString.isEmpty()) {
            return "Text found:\n" + foundString.concat("\n");
        } else {
            return "Text not found.\n\n";
        }
    }
}
