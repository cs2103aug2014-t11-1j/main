package gui.controller;

import java.util.Calendar;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class TimelineViewController {
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

	private final static int LAST_COL = 23;
	private final static int FIRST_COL = 0;

	private final static int MAX_HOUR = 24;
	
	private final static int MAX_HOUR_PERIOD = 12;
	
	private final static int AT_HOUR = 0;
	private final static int QUARTER_HOUR = 15;
	private final static int HALF_HOUR = 30;
	private final static int THREE_FOURTHS_HOUR = 45;
	private final static int FULL_HOUR = 60;

	final static int COLOUR_GREEN = 1;
	final static int COLOUR_RED = 2;
	final static int COLOUR_ORANGE = 3;

	@FXML
	private void initialize() {
		initilizeEveryDay();
	}

	private void initilizeEveryDay() {
		for(int i = Calendar.SUNDAY; i <= Calendar.SATURDAY; i++){
			GridPane day = getGridPane(i);
			initilizeDayWithGreen(day);
		}
	}

	private void initilizeDayWithGreen(GridPane day) {
		for(int col = FIRST_COL; col <= LAST_COL; col++){
			for(int row = Calendar.AM; row <= Calendar.PM; row++){
				Pane p = new Pane();
				setColour(p, COLOUR_GREEN);
				day.add(p, col, row);
			}
		}
	}

	//this assumes that startTime and endTime have the same date
	//should this not be true, the date set will be startTime
	public void addConfirmedPeriod(Date startTime, Date endTime){
		int colour = COLOUR_ORANGE;

		int day = getDay(startTime);
		int startCol = getStartCol(startTime);
		int period = findPeriod(startTime);
		int numCol = getTotalCol(startCol, endTime, period);
		
		
		System.out.println(startTime.toString());
		System.out.println(endTime.toString());
		
		System.out.println(numCol);
		GridPane dayToAdd = getGridPane(day);

		setPeriod(dayToAdd, period, startCol, numCol, colour);
	}
	
	private int getStartCol(Date startTime) {
		int startHour = getHour(startTime);
		int startMin = getRoundedMin(startTime);
		int startCol;
		
		if(startHour > MAX_HOUR_PERIOD){
			startHour -= MAX_HOUR_PERIOD;
		}
		
		startCol = (startHour - 1) * 2;
		
		if(startMin == HALF_HOUR){
			startCol++;
		}
		else if(startMin == FULL_HOUR){
			startCol += 2;
		}
		
		return startCol;
	}

	private int findPeriod(Date startTime) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startTime);
		return cal.get(Calendar.AM_PM);
	}

	private int getTotalCol(int startCol, Date endTime, int startPeriod) {
		int endHour = getHour(endTime);
		int endMin = getRoundedMin(endTime);
		int endPeriod = findPeriod(endTime);
		
		if(endHour > MAX_HOUR_PERIOD){
			endHour -= MAX_HOUR_PERIOD;
		}
		
		int numCol = (endHour - 1) * 2 - startCol;

		if(endPeriod == Calendar.PM && startPeriod == Calendar.AM){
			numCol += MAX_HOUR;
		}
		
		if(endMin == HALF_HOUR){
			numCol ++;
		}
		else if(endMin == FULL_HOUR){
			numCol += 2;
		}
		
		return numCol;
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
	
	private int getRoundedMin(Date time) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		int min = cal.get(Calendar.MINUTE);
		
		if(min <= QUARTER_HOUR){
			return AT_HOUR;
		}
		else if(min <= THREE_FOURTHS_HOUR){
			return HALF_HOUR;
		}
		else{
			return FULL_HOUR;
		}
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


	private void setPeriod(GridPane dayToAdd, int period, int startCol,
			int numCol, int colour) {

		for(int col = startCol; col < startCol + numCol; col++){
			Pane p;
			if(col <= LAST_COL){
				p = getPaneFromGridPane(dayToAdd, col, period);
			}
			else{
				p = getPaneFromGridPane(dayToAdd, col - MAX_HOUR, period + 1);
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
