package gui.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import logic.LogicFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import com.ModelTask;

//@author A0110567L
/**
 * this class is used to display popup reminder for events which have a start
 * date. it should show a display pop up at the bottom right of the screen 5 min
 * before the event start.
 */

public class Reminder {
	private final String REMINDER_MESSAGE = "Reminder: Task is due in 5 minutes!";
	private final String REMINDER_ERROR = "Error!!!";
	private static Reminder reminder = new Reminder();
	private LogicFacade logicFacade_ = LogicFacade.getInstance();
	private String oldTime_ = "";
	private String newTime_ = "";
	private ObservableList<ModelTask> taskList_ = FXCollections.observableArrayList();
	private SimpleDateFormat dateFormatter_ = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat timeFormatter_ = new SimpleDateFormat("HHmm");

	private Reminder() {
	}

	public static Reminder getInstance() {
		return reminder;
	}

	public void startReminder() {
		newTime_ = getNewTime();
		taskList_ = logicFacade_.getAllList();
		Date eventDate = new Date(), eventTime = new Date();
		int indexOfTask;
		for (indexOfTask = 0; indexOfTask < taskList_.size(); indexOfTask++) {
			if (taskList_.get(indexOfTask).getStartDate() != null) {
				eventDate = taskList_.get(indexOfTask).getStartDate();
			}
			if (taskList_.get(indexOfTask).getStartTime() != null) {
				eventTime = taskList_.get(indexOfTask).getStartTime();
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

				if (dateFormatter_.format(eventDate).equals(
						dateFormatter_.format(currentDateTime))) {
					todayDate.add(Calendar.MINUTE, 5);
					Date timeToStartReminder = todayDate.getTime();
					if (timeFormatter_.format(eventTime).equals(
							timeFormatter_.format(timeToStartReminder))) {
						// ensure reminder only activate one time each minte
						if (!oldTime_.equals(newTime_)) {
							String eventDescription = taskList_.get(indexOfTask)
									.getEvent();
							Notifications.create().title(REMINDER_MESSAGE)
									.text(eventDescription)
									.hideAfter(Duration.seconds(8))
									.showWarning();
							play();
							oldTime_ = newTime_;
						}
					}
				}

			} catch (Exception ex) {
				System.out.println(REMINDER_ERROR);
				ex.printStackTrace();
			}
		}
	}

	private String getNewTime() {
		Calendar c = Calendar.getInstance();
		return timeFormatter_.format(c.getTime());
	}

	// play a reminder sound when reminder is activated
	private void play() {
		final URL resource = getClass().getResource("reminder.mp3");
		final Media media = new Media(resource.toString());
		final MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
	}
}
