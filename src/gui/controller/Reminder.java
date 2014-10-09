package gui.controller;


/**
 * @author zhang
 * this class is used to display popup reminder for events which have a start date. 
 * it should show a display pop up at the bottom right of the screen.
 */


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import logic.LogicFacade;
import javafx.collections.ObservableList;

import org.controlsfx.control.Notifications;

import storage.ModelTask;

public class Reminder {

	private static Reminder reminder = new Reminder();
	private LogicFacade logicFacade = LogicFacade.getInstance();
	
	private Reminder(){
	}
	
	public static Reminder getInstance(){
		return reminder;
	}


	public void startReminder(){
		
		//testing if reminder pop up for a specific date
		
				ObservableList<ModelTask> list1 = logicFacade.getAllList();
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

				Date eventDay = new Date();
				for (int i = 0; i < list1.size(); i++) {
					if(list1.get(i).getStartDate()!=null){
						eventDay = list1.get(i).getStartDate();
						}	
					
					try {
						Date date2 = formatter.parse("09/10/2014");
						if (eventDay.equals(date2)) {
							Notifications.create().title("Reminder")
									.text("Hey, Go do your coding dumblydore!")
									.showWarning();

						}

					} catch (ParseException ex) {

						ex.printStackTrace();
					}

				}

				


				// testing popup functionality using controlsfx with timer
				// Platform.runLater(new Runnable(){
				// @Override
				// public void run(){
				//
				// Timer timer = new Timer();
				// timer.schedule(new TimerTask() {
				// public void run() {
				// Notifications.create().title("Reminder")
				// .text("Hey, this is notification pop up Yay!").showWarning();
				//
				// }
				// },100,100);
				// }
				//
				// });
				
				
				
				// ObservableList<ModelTask> allList= logicFacade.getAllList();

				// Calendar c = Calendar.getInstance();
				// Date todate = c.getTime();

				// Platform.runLater(new Runnable(){
				// @Override
				// public void run(){
				//
				// Timer timer = new Timer();
				//
				// timer.schedule(new TimerTask() {
				// public void run() {
				// Notifications.create().title("Reminder")
				// .text("Hey, this is notification pop up Yay!").showWarning();
				//
				// }
				// },100,100);
				// }
				//
				// });
			
				
				
				
	}
}
