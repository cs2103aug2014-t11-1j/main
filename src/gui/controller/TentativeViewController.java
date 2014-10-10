package gui.controller;

import java.util.Calendar;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class TentativeViewController {
	@FXML
	GridPane monday;
	@FXML
	GridPane tuesday;
	@FXML
	GridPane wednesday;
	@FXML
	GridPane thursday;
	@FXML
	GridPane friday;
	@FXML
	GridPane saturday;
	@FXML
	GridPane sunday;

	final static int PERIOD_AM = 0;
	final static int PERIOD_PM = 1;

	final static int COLOUR_GREEN = 1;
	final static int COLOUR_RED = 2;
	final static int COLOUR_ORANGE = 3;

	@FXML
	private void initialize() {
		for(int i = Calendar.SUNDAY; i <= Calendar.SATURDAY; i++){
			GridPane day = getGridPane(i);
			for(int j = 0; j < 12; j++){
				for(int k=0; k<=1; k++){
					Pane p = new Pane();
					setColour(p, COLOUR_GREEN);
					day.add(p, j, k);
				}
			}
		}

	}

	//this assumes that startTime and endTime have the same date
	//should this not be true, the date set will be startTime
	public void addConfirmedPeriod(Date startTime, Date endTime){
		int colour = COLOUR_ORANGE;

		int day = getDay(startTime);
		int startHour = getHour(startTime);
		int endHour = getHour(endTime);

		GridPane dayToAdd = getGridPane(day);
		int period = PERIOD_AM;

		//setting timings to the 12 hour clock
		if(startHour > 12){
			startHour -= 12;
			endHour -= 12;
			period = PERIOD_PM;
		}

		setPeriod(dayToAdd, period, startHour, endHour, colour);

	}

	private int getDay(Date startTime) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startTime);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	private int getHour(Date time) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	private GridPane getGridPane(int day) {
		switch(day){
		case Calendar.MONDAY:
			return monday;
		case Calendar.TUESDAY:
			return tuesday;
		case Calendar.WEDNESDAY:
			return wednesday;
		case Calendar.THURSDAY:
			return thursday;
		case Calendar.FRIDAY:
			return friday;
		case Calendar.SATURDAY:
			return saturday;
		case Calendar.SUNDAY:
			return sunday;
		default:
			return null;
		}
	}


	private void setPeriod(GridPane dayToAdd, int period, int startHour,
			int endHour, int colour) {

		for(int i = startHour-1; i < endHour-1; i++){
			Pane p;
			if(i <= 12){
				p = getPaneFromGridPane(dayToAdd, i, period);
			}
			else{
				p = getPaneFromGridPane(dayToAdd, i - 12, period + 1);
			}
			setColour(p, colour);
		}

	}

	private Pane getPaneFromGridPane(GridPane gridPane, int col, int row) {
		for (Node node : gridPane.getChildren()) {
	        if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
	            return (Pane) node;
	        }
	    }
	    return null;
	}

	private void setColour(Pane p, int colour) {
		switch(colour){
		case COLOUR_GREEN:
			p.setStyle("-fx-background-color: green");
			break;
		case COLOUR_RED:
			p.setStyle("-fx-background-color: red");
			break;
		case COLOUR_ORANGE:
			p.setStyle("-fx-background-color: orange");
			break;
		default:
			p.setStyle("-fx-background-color: transparent");
		}
	}

}
