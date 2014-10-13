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

	private TextField commandLine;
	private LogicFacade logicFacade;
	private ObservableList<ModelTask> list;

	public EditListener(TextField commandLine) {
		this.logicFacade = LogicFacade.getInstance();
		this.list = logicFacade.getAllList();
		this.commandLine = commandLine;
	}

	@Override
	public void changed(ObservableValue<? extends String> observable,
			String oldValue, String newValue) {

		if (newValue.trim().equalsIgnoreCase("add?")) {
			commandLine
					.setText("add taskdecription startdate enddate starttime endtime");
		}

		if (newValue.trim().equalsIgnoreCase("edit?")) {
			commandLine
					.setText("edit tasknumber taskdecription startdate enddate starttime endtime");
		}

		if (containsTwoSpace(newValue)) {
			String[] subStrings = newValue.trim().split(" ");
			if (subStrings[0].equalsIgnoreCase("edit")
					&& isInteger(subStrings[1])
					&& !PhantomController.hasOccured) {

				commandLine.setText(newValue
						+ getTaskDescription(Integer.parseInt(subStrings[1])));
				PhantomController.hasOccured = true;
			}

		}

		list = logicFacade.getAllList();
	}

	private boolean containsTwoSpace(String input) {
		int spaces = input.length() - input.replace(" ", "").length();

		if (spaces == 2) {
			return true;
		}
		return false;
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
		String feedBack = "";
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPosition() == position) {
				currTask = list.get(i);
				positionIsFound = true;
			}
		}

		if (positionIsFound) {
			String event = currTask.getEvent();
			String startDate = currTask.getStartDateString();
			String endDate = currTask.getEndDateString();
			String startTime = currTask.getStartTimeString();
			String endTime = currTask.getEndTimeString();
			String dateString = "", timeString = "";

			System.out.println(startDate + endDate);
			if (startDate == null) {
				startDate = "";
			}
			if (endDate == null) {
				endDate = "";
			}
			if (startTime == null) {
				startTime = "";
			}
			if (endTime == null) {
				endTime = "";
			}

			if (!startDate.equals("") && !endDate.equals("")) {
				dateString = "from " + startDate + " to " + endDate;
			} else if (startDate.equals("") && endDate.equals("")) {
				dateString = startDate + endDate;
			} else {
				dateString = "by " + startDate + endDate;
			}

			if (!startTime.equals("") && !endTime.equals("")) {
				timeString = "from " + startTime + " to " + endTime;
			} else if (startTime.equals("") && endTime.equals("")) {
				timeString = startTime + endTime;
			} else {
				timeString = "by " + startTime + endTime;
			}
			feedBack = event + " " + dateString + " " + timeString;
			return feedBack;
		}

		return feedBack;
	}

}
