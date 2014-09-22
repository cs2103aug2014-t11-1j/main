package controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import javafx.animation.*;
import javafx.event.*;

import java.util.Calendar;

public class MyController implements Initializable{
	
	
	private CommandGenerator commandGenerator;

	@FXML
	private TextArea display;

	@FXML
	private TextField commandLine;

	@FXML
	private Button enterButton;

	@FXML
	private Label timeLabel;

	@FXML
	private Label tfOutput;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		display.setEditable(false);
		bindToTime();
		commandGenerator = new CommandGenerator();
		commandGenerator.excecuteCommand("display");
		display.setText(commandGenerator.getDisplayString());
		
	}

	public void changeText(ActionEvent e){
		display.appendText(commandLine.getText());
		display.appendText("\n");
		commandLine.setText("");
	}

	public void handleKeyPressed(KeyEvent e){
		/*
		 * if getcode isalphanum,
		 * powersearch
		 * 
		 * when press enter, go to commandgenerator, instead of invalid, display every searched item
		 */
		
		/*
		 * this changeListener is meant for the edit function. When user type edit num, the 
		 * program will append the task corresponding to the num in the input box.
		 */
		EditListener editListener = new EditListener(commandGenerator,commandLine);
		commandLine.textProperty().addListener(editListener);
		
	
		
		if(e.getCode() == KeyCode.ENTER){
			String input = commandLine.getText();	
			commandGenerator.excecuteCommand(input);
			display.setText(commandGenerator.getDisplayString());
			tfOutput.setText(commandGenerator.getTfString());
			commandLine.setText("");
			labelFade(e);
		}
					
	}
	
	
	public void labelFade(KeyEvent e){
		FadeTransition fadeTransition
		= new FadeTransition(Duration.millis(3000), tfOutput);
		fadeTransition.setFromValue(1.0);
		fadeTransition.setToValue(0.0);
		fadeTransition.play();
	}
	
	public static String getCurrentTimeStamp() {
	    SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");//dd/MM/yyyy
	    Date now = new Date();
	    String strDate = sdfDate.format(now);
	    return strDate;
	}
	
	
	 // the digital clock updates once a second.
	  private void bindToTime() {
	    Timeline timeline = new Timeline(
	      new KeyFrame(Duration.seconds(0),
	        new EventHandler<ActionEvent>() {
	          @Override public void handle(ActionEvent actionEvent) {
	            Calendar time = Calendar.getInstance();
	            String hourString = StringUtilities.pad(2, ' ', time.get(Calendar.HOUR) == 0 ? "12" : time.get(Calendar.HOUR) + "");
	            String minuteString = StringUtilities.pad(2, '0', time.get(Calendar.MINUTE) + "");
	            String secondString = StringUtilities.pad(2, '0', time.get(Calendar.SECOND) + "");
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
	}

	class StringUtilities {
	  /**
	   * Creates a string left padded to the specified width with the supplied padding character.
	   * @param fieldWidth the length of the resultant padded string.
	   * @param padChar a character to use for padding the string.
	   * @param s the string to be padded.
	   * @return the padded string.
	   */
	  public static String pad(int fieldWidth, char padChar, String s) {
	    StringBuilder sb = new StringBuilder();
	    for (int i = s.length(); i < fieldWidth; i++) {
	      sb.append(padChar);
	    }
	    sb.append(s);

	    return sb.toString();
	  }

}