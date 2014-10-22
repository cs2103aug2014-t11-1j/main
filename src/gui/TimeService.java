package gui;
/**
 * @author zhang
 * 
 * this time service class can be used for other timer services.
 * currently it has Reminder service that checks for reminder every 5 sec
 * 
 * Note: this is a public static class, simple call the timer with 
 * TimeService.startReminderService();
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
	// //for loading of reminder pop ups
	
	public static void startReminderService(){
	Timeline reminderService = new Timeline(new KeyFrame(
			Duration.seconds(5), new EventHandler<ActionEvent>() {
				Reminder popUpReminder = Reminder.getInstance();

				@Override
				public void handle(ActionEvent event) {
					popUpReminder.startReminder();
		//			System.out.println("check reminder 5 seconds");
				}
			}));
	reminderService.setCycleCount(Timeline.INDEFINITE);
	reminderService.play();
	}
	
	public static void startNextDayChecker(){
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		
		// get current system time
		Calendar todayDate = Calendar.getInstance();
		Date currentDateTime = todayDate.getTime();
		String currentDate = dateFormatter.format(currentDateTime);
		
		Timeline reminderService = new Timeline(new KeyFrame(
				Duration.seconds(5), new EventHandler<ActionEvent>() {
					
					//insert event 
					
					@Override
					public void handle(ActionEvent event) {
					//do something insert code here	
					//System.out.println("check reminder 5 seconds");
					}
				}));
		reminderService.setCycleCount(Timeline.INDEFINITE);
		reminderService.play();
		}
	}


