/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import parser.ParserFacade;
import storage.TaskList;
import storage.Task;
import storage.UndoRedoStack;

/**
 *
 * @author Jireh
 */
public class Command {

    private ParserFacade pf = ParserFacade.getInstance();
    private TaskList list = new TaskList();
    private UndoRedoStack undoStack = new UndoRedoStack();
    private UndoRedoStack redoStack = new UndoRedoStack();
    private static Command cmd = new Command();

    private Command() {
    }

    private static Command getInstance() {
        return cmd;
    }

    protected boolean add(String taskDetails) {
        list.add(pf.getTask(taskDetails));
        return true;
    }

    protected boolean delete(String input) {
        int index = Integer.parseInt(input);
        list.remove(index);
        return true;
    }

    protected boolean clear() {
        list.clear();
        return true;
    }

    protected boolean edit(int index, String newTaskDetails) {
        list.remove(index);
        list.add(pf.getTask(newTaskDetails), index);
        return true;
    }

    protected String display() {
        String text = "";

        if (list.getList().isEmpty()) {
            printMessage("*filename* is empty");
            text = "List is empty";
        } else {
            printContentsOfList(1);

            int lineNum = 1;
            for (int i = 0; i < list.getListSize(); i++) {
                text = text + lineNum + ". " + list.get(i).toString() + "\n";
                lineNum++;
            }
        }
        return text;
    }

    protected boolean sort() {

    }

    protected String search(String searchTerm) {
        Iterator<Task> iterator = list.getIterator();
        String foundString = "";
        int index = 0;

        while (iterator.hasNext()) {
            String currentString = iterator.next().getTaskDescription();
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

    protected boolean markDone(int index) {
        boolean isMarkedDone = false;
        if (isValidLineNumber(index)) {
            Task task = list.get(index);
            task.setIsDone(true);
            updateUndoAndRedoStacks();
            isMarkedDone = true;
        }
        return isMarkedDone;
    }

    protected boolean undo() throws IOException {

        boolean isUndone = false;

        if (!undoStack.getStack().isEmpty()) {
            ArrayList<Task> popped = undoStack.pop();

            if (!undoStack.getStack().isEmpty()) {
                redoStack.push(popped);

                popped = undoStack.peek();
                ArrayList<Task> temp = new ArrayList<Task>();
                copyArrayList(popped, temp);
                list.setList(temp);

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
        return isUndone;
    }

    protected boolean redo() throws IOException {
        boolean isRedone = false;
        if (redoStack.getStack().isEmpty()) {
            System.out.println("No action to redo");
        } else {
            list.setList(redoStack.pop());
            ArrayList<Task> temp = new ArrayList<Task>();
            copyArrayList(list.getList(), temp);
            undoStack.push(temp);
            isRedone = true;
        }
        return isRedone;
    }

    protected boolean move(int num1, int num2) throws IOException {
        boolean isMoved = false;
        int index1 = num1 - 1, index2 = num2 - 1;

        if (isValidLineNumber(index1) && isValidLineNumber(index2) && index1 != index2) {
            Task temp1 = list.get(index1);
            Task temp2 = list.get(index2);
            list.set(index1, temp2);
            list.set(index2, temp1);

            printMessage("Task " + num1 + "has been moved to " + num2);
            isMoved = true;
        } else {
            printMessage("Please enter 2 valid numbers");
        }
        return isMoved;
    }

    private boolean isValidLineNumber(int num) {
        return num < list.getListSize() && num >= 0;
    }

    private void printContentsOfList(int lineNum) {
        Task temp;
        for (int i = 0; i < list.getListSize(); i++) {
            temp = list.get(i);
            printMessage(lineNum + ". " + temp.toString() + " " + temp.isDone());
            lineNum++;
        }
    }

    private void printMessage(String message) {
        System.out.println(message);
    }

    private void updateUndoAndRedoStacks() {
        ArrayList<Task> temp = new ArrayList<Task>();
        copyArrayList(list.getList(), temp);
        undoStack.push(temp);
        redoStack.clear();
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
}
