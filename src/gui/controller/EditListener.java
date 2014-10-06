package gui.controller;

/**
 *
 * @author: Zhang Yongkai
 * this is the editlistener for a text field in javafx
 * listener will listen to change in the input textfield and display the
 * the corresponding description of the task when the task number is entered
 * for example:
 * if you type "edit 2", it will display the 2nd task.
 * if you type "edit?" it will display what to input
 * if you type "add?" it will display what to input
 * 
 *
 * note: currently, it only listen to "edit num" and "add"
 *
 */

import storage.ModelTask;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
//import javafx.scene.input.KeyCode;
//import javafx.scene.input.KeyEvent;
import logic.LogicFacade;

public class EditListener implements ChangeListener<String> {

	// private static final String STRING_FORMAT =
	// "[%1$s] [%2$s] [%3$s] [%4$s] [%5$s]";
	private TextField commandLine;
	private LogicFacade logicFacade;
	private ObservableList<ModelTask> list;

	// private KeyEvent keyPress;

	public EditListener(TextField commandLine) {
		this.logicFacade = LogicFacade.getInstance();
		this.list = logicFacade.getAllList();
		this.commandLine = commandLine;
	}

	@Override
	public void changed(ObservableValue<? extends String> observable,
			String oldValue, String newValue) {

	

		if (newValue.trim().equalsIgnoreCase("add?")) {
			commandLine.setText("add taskdecription");
		}

		if (newValue.trim().equalsIgnoreCase("edit?")) {
			commandLine.setText("edit tasknumber");
		}

		String[] subStrings = newValue.trim().split(" ");

		if (subStrings.length == 2) {
			if (subStrings[0].equalsIgnoreCase("edit")
					&& isInteger(subStrings[1])
					&& !PhantomController.hasOccured) {

				if (!getTaskDescription(Integer.parseInt(subStrings[1])).trim()
						.equals("")) {
					commandLine.setText(newValue
							+ " "
							+ getTaskDescription(Integer
									.parseInt(subStrings[1])));
				}

				PhantomController.hasOccured = true;
			}

		}
		
		// for getting search list view
		 //if(keyPress.getCode()==KeyCode.ENTER){
		// if(getFirstWord(newValue).equalsIgnoreCase("search")){
		// list=logicFacade.getSearchedList();
		// }else{
		list= logicFacade.getAllList();
		// }
		// }
		//
		// }

	}

	private boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}

	/**
	 * the task for now is retrieved from the allList in logic facade
	 *
	 */

	private String getTaskDescription(int position) {

		ModelTask currTask = new ModelTask();
		Boolean positionIsFound = false;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPosition() == position) {
				currTask = list.get(i);
				positionIsFound = true;
			}
		}

		// return (String.format(STRING_FORMAT,currTask.getEvent(),
		// currTask.getStartDateString(),
		// currTask.getEndDateString(),
		// currTask.getStartTimeString(),currTask.getEndTimeString()));
		if (positionIsFound) {
			return currTask.getEvent() + " " + currTask.getStartDateString()
					+ " " + currTask.getEndDateString() + " "
					+ currTask.getStartTimeString() + " "
					+ currTask.getEndTimeString();
		}
		
		return "";
	}

	// private String getFirstWord(String input) {
	// String firstWord = "";
	// if (input != null) {
	// String[] token = input.trim().split(" ");
	// firstWord = token[0];
	// }
	// return firstWord;
	// }

}
