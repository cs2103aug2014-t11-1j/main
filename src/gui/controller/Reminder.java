package gui.controller;

/**
 * @author zhang
 * this class is used to display popup reminder for events which have a start date. 
 * it should show a display pop up at the bottom right of the screen.
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import logic.LogicFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.controlsfx.control.Notifications;

import storage.ModelTask;

public class Reminder {

	private static Reminder reminder = new Reminder();
	private LogicFacade logicFacade = LogicFacade.getInstance();
	ObservableList<ModelTask> taskList = FXCollections.observableArrayList();
	private String oldTime = "";
	private String newTime = "";

	SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat timeFormatter = new SimpleDateFormat("HHmm");

	private Reminder() {
	}

	public static Reminder getInstance() {
		return reminder;
	}

	public void startReminder() {
		newTime = getNewTime();
		// testing if reminder pop up for a specific date
		taskList = logicFacade.getAllList();

		Date eventDate = new Date(), eventTime = new Date();
		int indexOfTask;
		for (indexOfTask = 0; indexOfTask < taskList.size(); indexOfTask++) {
			if (taskList.get(indexOfTask).getStartDate() != null) {
				eventDate = taskList.get(indexOfTask).getStartDate();
				// System.out.println(eventDay);
			}
			if (taskList.get(indexOfTask).getStartTime() != null) {
				eventTime = taskList.get(indexOfTask).getStartTime();
			}

			try {
				// get current system time
				Calendar todayDate = Calendar.getInstance();
				Date currentDateTime = todayDate.getTime();

				/**
				 * check if the date is the same if date is same check time
				 * inteval is within allowed reminder range. currently it's set
				 * to 5 min before the task is activated
				 */

				if (dateFormatter.format(eventDate).equals(
						dateFormatter.format(currentDateTime))) {

					todayDate.add(Calendar.MINUTE, 5);
					Date timeToStartReminder = todayDate.getTime();
					if (timeFormatter.format(eventTime).equals(
							timeFormatter.format(timeToStartReminder))) {
						//ensure reminder only activate one time for within a minute
						if (!oldTime.equals(newTime)) {
							String eventDescription = taskList.get(indexOfTask)
									.getEvent();
							Notifications.create().title("Task Reminder")
									.text(eventDescription).showWarning();
						}

					}

				}

			} catch (Exception ex) {
				System.out.println("holy cow, error!!!!");
				ex.printStackTrace();
			}

		}
		oldTime = newTime;
	}

	private String getNewTime() {
		Calendar c = Calendar.getInstance();
		return timeFormatter.format(c.getTime());
	}
}
