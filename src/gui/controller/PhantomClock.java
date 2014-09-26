package gui.controller;

import java.util.Calendar;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * This class is used to initialize
 * the clock in the Phantom GUI.
 * 
 * * Author: smallson
 */

public class PhantomClock {
	

	private static PhantomClock pc = new PhantomClock();

	private PhantomClock(){
	}

	public static PhantomClock getInstance(){
		return pc;
	}
	
	public void setClock(Label timeLabel){
		bindToTime(timeLabel);
	}

	private void bindToTime(Label timeLabel) {
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.seconds(0),
						new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent actionEvent) {
						Calendar time = Calendar.getInstance();
						String hourString = pad(2, ' ', time.get(Calendar.HOUR) == 0 ? "12" : time.get(Calendar.HOUR) + "");
						String minuteString = pad(2, '0', time.get(Calendar.MINUTE) + "");
						String secondString = pad(2, '0', time.get(Calendar.SECOND) + "");
						String ampmString = time.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
						timeLabel.setText(hourString + ":" + minuteString + ":" + secondString + " " + ampmString);
					}
				}
						),
						new KeyFrame(Duration.seconds(1))
				);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}


	public static String pad(int fieldWidth, char padChar, String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = s.length(); i < fieldWidth; i++) {
			sb.append(padChar);
		}
		sb.append(s);

		return sb.toString();
	}	

}
