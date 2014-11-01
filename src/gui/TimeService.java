package gui;

/**
 * @author Zhang Yongkai
 * 
 * this time service class can be used for other timer services.
 * currently it has Reminder service that checks for reminder every 5 sec,
 * it also check if the next day is reached
 *
 */
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import gui.controller.Reminder;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public final class TimeService {

	private static TimeService timeService = new TimeService();
	SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat timeFormatter = new SimpleDateFormat("HHmm");

	// String tomorrow = getTomorrowDate();
	// String today = getTodayDate();

	private TimeService() {
	}

	public static TimeService getInstance() {
		return timeService;
	}

	// //for starting reminder pop ups
	public void startReminderService() {
		Timeline reminderService = new Timeline(new KeyFrame(
				Duration.seconds(5), new EventHandler<ActionEvent>() {
					Reminder popUpReminder = Reminder.getInstance();

					@Override
					public void handle(ActionEvent event) {
						popUpReminder.startReminder();
						// System.out.println("check reminder 5 seconds");
					}
				}));
		reminderService.setCycleCount(Timeline.INDEFINITE);
		reminderService.play();
	}

	// check if the next day is reached
	// public void startNextDayChecker() {
	//
	// Timeline reminderService = new Timeline(new KeyFrame(
	// Duration.seconds(5), new EventHandler<ActionEvent>() {
	//
	// // insert event
	//
	// @Override
	// public void handle(ActionEvent event) {
	// // do something insert code here
	// if (today.equals(tomorrow)) {
	// System.out.println("today is tomorrow");
	// today = getTodayDate();
	// tomorrow = getTomorrowDate();
	// } else {
	// today = getTodayDate();
	// System.out.println("today is not tomorrow");
	// }
	// }
	// }));
	// reminderService.setCycleCount(Timeline.INDEFINITE);
	// reminderService.play();
	// }

	// private String getTomorrowDate() {
	// Date date = new Date();
	// Calendar calendar = Calendar.getInstance();
	// calendar.setTime(date);
	// calendar.add(Calendar.DATE, 1);
	// date = calendar.getTime();
	// return dateFormatter.format(date);
	// }
	//
	// private String getTodayDate() {
	// Date today = new Date();
	// Calendar calendar = Calendar.getInstance();
	// today = calendar.getTime();
	// return dateFormatter.format(today);
	// }

}
