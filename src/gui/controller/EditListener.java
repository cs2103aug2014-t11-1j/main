package gui.controller;

/**
 *
 * @author: Zhang Yongkai
 * this listener will listen to change in the input textfield and display the
 * the corresponding description of the task when the task number is entered
 * for example:
 * if you type "edit 2", it will display the 2nd task.
 * to use it, create a CommandLine object, eg: comandLine, then:
 *
 *		EditListener editListener = new EditListener(logicFacade.getAllList(),commandLine);
 *		commandLine.textProperty().addListener(editListener);
 *
 * note: currently, it only listen to "edit num"
 * and it will not work if the task is not retrieved from a text box. see
 * method getTaskDescription.
 *
 */

import storage.ModelTask;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

public class EditListener implements ChangeListener<String> {

	private TextField commandLine;
	private ObservableList<ModelTask> list;

	public EditListener(ObservableList<ModelTask> list, TextField commandLine) {
		this.list = list;
		this.commandLine = commandLine;
	}

	@Override
	public void changed(ObservableValue<? extends String> observable,
			String oldValue, String newValue) {
		String[] subStrings = newValue.trim().split(" ");
		if (subStrings.length == 2) {
			if (subStrings[0].equalsIgnoreCase("edit")
					&& isInteger(subStrings[1])) {
				if (!getTaskDescription(Integer.parseInt(subStrings[1]))
						.equals("")) {
					commandLine.setText(newValue
							+ " "
							+ getTaskDescription(Integer
									.parseInt(subStrings[1])));
				}
			}
		}
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
	 * the task description for now is retrieved from a
	 * commandGenerator.getDisplayString. in the future, if ObservableList is
	 * implemented, this method must be modified to get task from the
	 * observablelist instead.
	 *
	 */

	private String getTaskDescription(int position) {
		ModelTask currTask = list.get(position - 1);
		return currTask.getEvent() + " " + currTask.getStartDate()
				+ " " + currTask.getEndDate() + " " + currTask.getStartTime()
				+ " " + currTask.getEndTime();
	}

}
