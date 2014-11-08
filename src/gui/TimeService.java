package gui;

import java.text.SimpleDateFormat;
import gui.controller.Reminder;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

//@author A0110567L
/**
 * this time service class contains Reminder service that checks for reminder every 5 sec,
 */

public final class TimeService {
	private static TimeService timeService = new TimeService();
	SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat timeFormatter = new SimpleDateFormat("HHmm");
	
	//constructors
	private TimeService() {
	}

	public static TimeService getInstance() {
		return timeService;
	}

	//for starting reminder pop ups
	public void startReminderService() {
		Timeline reminderService = new Timeline(new KeyFrame(
				Duration.seconds(5), new EventHandler<ActionEvent>() {
					Reminder popUpReminder = Reminder.getInstance();

					@Override
					public void handle(ActionEvent event) {
						popUpReminder.startReminder();
					}
				}));
		reminderService.setCycleCount(Timeline.INDEFINITE);
		reminderService.play();
	}
}
