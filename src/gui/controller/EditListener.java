package gui.controller;

import gui.controller.view.PhantomController;
import com.ModelTask;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import logic.LogicFacade;

//@author A0110567L
/**
 * this is the editlistener for a text field in javafx listener will listen to
 * change in the input text field and display the the corresponding description
 * of the task when the task number is entered for example: if you type
 * "edit 2", it will display the 2nd task in the text field itself if you type
 * "edit?" it will display what to input if you type "add?" it will display what
 * to input
 *
 * note: the edit listener only work once per "enter".
 */

public class EditListener implements ChangeListener<String> {

	private TextField commandLine_;
	private LogicFacade logicFacade_;
	private ObservableList<ModelTask> list_;

	public EditListener(TextField commandLine) {
		logicFacade_ = LogicFacade.getInstance();
		list_ = logicFacade_.getAllList();
		commandLine_ = commandLine;
	}

	@Override
	public void changed(ObservableValue<? extends String> observable,
			String oldValue, String newValue) {

		if (newValue.trim().equalsIgnoreCase("add?")) {
			commandLine_
					.setText("add taskdecription startdate enddate starttime endtime");
		}

		if (newValue.trim().equalsIgnoreCase("edit?")) {
			commandLine_
					.setText("edit tasknumber taskdecription startdate enddate starttime endtime");
		}

		if (containsTwoSpace(newValue) && !PhantomController.isHasOccured()) {
			String[] subStrings = newValue.trim().split("\\s+");
			commandLine_.setText(newValue
					+ getTaskDescription(Integer.parseInt(subStrings[1])));
		}

		list_ = logicFacade_.getAllList();
	}

	private boolean containsTwoSpace(String input) {
		if ((input != null) && ((input.replaceAll("\\s+", "")) != "")) {

			if (input.length() > 0
					&& input.substring(input.length() - 1).equals(" ")) {

				String[] subStrings = input.trim().split("\\s+");
				if (subStrings.length >= 2) {
					if (subStrings[0].equalsIgnoreCase("edit")
							&& isInteger(subStrings[1])) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	private String getTaskDescription(int position) {
		PhantomController.setHasOccured(true);
		ModelTask currTask = new ModelTask();
		Boolean positionIsFound = false;
		String feedBack = "";
		for (int i = 0; i < list_.size(); i++) {
			if (list_.get(i).getPosition() == position) {
				currTask = list_.get(i);
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
